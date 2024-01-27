package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.config.ApplicationConfig;
import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.*;
import com.eduSolution.eduSolution.exception.ErrorObject;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

    private ApplicationConfig applicationConfig;


    @Autowired
    public UserService(UserRepository userRepository, ClassGroupRepository classGroupRepository, ApplicationConfig applicationConfig) {
        this.userRepository = userRepository;
        this.classGroupRepository = classGroupRepository;
        this.applicationConfig = applicationConfig;
    }

    public  User getUserById (int id){
        return userRepository.findById(id).orElse(null);
    }

    public  List<User> getUsers (){
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail (String email){
        return userRepository.findByEmail(email);
    }

    public Optional <User> getUserByUsername (String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findUsersByClassGroupId(int classGroupId) {
        return userRepository.findUsersByClassGroupId(classGroupId);
    }

    public DeleteResponseDTO deleteUser(int id){
        //        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        User user = userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return user != null ? new DeleteResponseDTO(user.getId(), user.getLastName()) : null;
    }
    public User updateUser (User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
            existingUser.setPassword(applicationConfig.passwordEncoder().encode(user.getPassword()));
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
//            existingUser.setCity(user.getCity());
//            existingUser.setCountry(user.getCountry());
            existingUser.setAddress(user.getAddress());
//            existingUser.setPost(user.getPost());
//            existingUser.setPostCode(user.getPostCode());
            existingUser.setRole(user.getRole());
            existingUser.setPhoneNr(user.getPhoneNr());
            existingUser.setDateOfBirth(user.getDateOfBirth());
//            changeRole(user);


            Set<ClassGroup> classGroups = new HashSet<>();
            if (user.getClassGroups() != null) {
                for (ClassGroup teachingClassGroup : user.getClassGroups()) {
                    ClassGroup existingTeachingClassGroup = classGroupRepository.findById(teachingClassGroup.getId()).orElse(null);
                    if (existingTeachingClassGroup != null) {
                        classGroups.add(existingTeachingClassGroup);
                    }
                }
            }
            existingUser.setClassGroups(classGroups);
        }
//        autenticationService.revokeAllUserTokens(user);

        return userRepository.save(existingUser);
    }

//    public User updateUserClassGroup (User user){
//        User existingUser = userRepository.findById(user.getId()).orElse(null);
//        if (existingUser != null) {
//            Set<ClassGroup> classGroups = new HashSet<>();
//            if (user.getClassGroups() != null) {
//                for (ClassGroup teachingClassGroup : user.getClassGroups()) {
//                    ClassGroup existingTeachingClassGroup = classGroupRepository.findById(teachingClassGroup.getId()).orElse(null);
//                    if (existingTeachingClassGroup != null) {
//                        classGroups.add(existingTeachingClassGroup);
//                    }
//                }
//            }
//            existingUser.setClassGroups(classGroups);
//        }
//
//        return userRepository.save(existingUser);
//    }

    public User updateUserClassGroup(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);

        if (existingUser != null) {
            Set<ClassGroup> classGroups = new HashSet<>();

            if (user.getClassGroups() != null) {
                for (ClassGroup teachingClassGroup : user.getClassGroups()) {
                    ClassGroup existingTeachingClassGroup = classGroupRepository.findById(teachingClassGroup.getId()).orElse(null);

                    if (existingTeachingClassGroup != null) {
                        // Check if the assignment does not exceed the limit
                        int currentStudentsCount = classGroupRepository.getStudentsCountInClassGroup(existingTeachingClassGroup.getId());

                        if (currentStudentsCount < existingTeachingClassGroup.getStudentsLimit()) {
                            classGroups.add(existingTeachingClassGroup);
                        } else {
                            // Handle exceeding the limit (throw an exception or return an appropriate message)
                            throw new IllegalArgumentException("Exceeded the limit of students in the group.");
                        }
                    }
                }
            }

            existingUser.setClassGroups(classGroups);
        }

        return userRepository.save(existingUser);
    }

    public User updateUserWithoutPasswordNoLimitCheck (User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
//            existingUser.setCity(user.getCity());
//            existingUser.setCountry(user.getCountry());
            existingUser.setAddress(user.getAddress());
//            existingUser.setPost(user.getPost());
//            existingUser.setPostCode(user.getPostCode());
            existingUser.setPhoneNr(user.getPhoneNr());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setRole(user.getRole());

            Set<ClassGroup> classGroups = new HashSet<>();
            if (user.getClassGroups() != null) {
                for (ClassGroup teachingClassGroup : user.getClassGroups()) {
                    ClassGroup existingTeachingClassGroup = classGroupRepository.findById(teachingClassGroup.getId()).orElse(null);
                    if (existingTeachingClassGroup != null) {
                        classGroups.add(existingTeachingClassGroup);
                    }
                }
            }
            existingUser.setClassGroups(classGroups);
        }

        return userRepository.save(existingUser);
    }

    public User updateUserWithoutPassword(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
//            existingUser.setCity(user.getCity());
//            existingUser.setCountry(user.getCountry());
            existingUser.setAddress(user.getAddress());
//            existingUser.setPost(user.getPost());
//            existingUser.setPostCode(user.getPostCode());
            existingUser.setPhoneNr(user.getPhoneNr());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setRole(user.getRole());

            if (existingUser != null) {
                Set<ClassGroup> classGroups = new HashSet<>();

                if (user.getClassGroups() != null && !user.getClassGroups().isEmpty()) {
                    for (ClassGroup teachingClassGroup : user.getClassGroups()) {
                        ClassGroup existingTeachingClassGroup = classGroupRepository.findById(teachingClassGroup.getId()).orElse(null);

                        if (existingTeachingClassGroup != null) {
                            // Sprawdzenie czy przypisanie nie przekroczy limitu tylko, gdy dodajesz nową grupę
                            if (!existingUser.getClassGroups().contains(existingTeachingClassGroup)) {
                                int currentStudentsCount = classGroupRepository.getStudentsCountInClassGroup(existingTeachingClassGroup.getId());

                                if (currentStudentsCount < existingTeachingClassGroup.getStudentsLimit()) {
                                    classGroups.add(existingTeachingClassGroup);
                                } else {
                                    // Handle exceeding the limit (możesz też pominąć zapis i poinformować użytkownika)
                                    throw new IllegalArgumentException("Przekroczono limit użytkowników w grupie.");
                                }
                            } else {
                                // Jeśli grupa jest już przypisana, nie sprawdzaj limitu
                                classGroups.add(existingTeachingClassGroup);
                            }
                        }
                    }
                }


                existingUser.setClassGroups(classGroups);
            }
        } else {
            throw new EntityNotFoundException("Użytkownik o podanym ID nie istnieje.");

        }

        return userRepository.save(existingUser);
    }



    public User changeRole (User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }


    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public List<ClassGroup> findClassGroupsById(Integer userId) {
        return userRepository.findClassGroupsById(userId);

    }

    public List<User> findUsersByClassGroupIdAndRole(Integer classGroupId, Role userRole) {
        return userRepository.findUsersByClassGroupIdAndRole(classGroupId, userRole);
    }

    public ResponseEntity<?> changePassword (User user, String oldPassword, String newPassword, String newPasswordConfirm) throws Exception {

        User existingUser = userRepository.findById(user.getId()).orElse(null);
        ErrorObject error;
        if(existingUser!=null){
            if(passwordEncoder.matches(oldPassword,existingUser.getPassword()))
            {
                if(oldPassword.equals(newPassword)) {
                    error = new ErrorObject("NOWE HASŁO NIE MOŻE BYĆ TAKIE SAMO JAK OBECNE");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
                }
                else if(newPassword.equals(newPasswordConfirm))
                    existingUser.setPassword(passwordEncoder.encode(newPassword));
                else{
                    error = new ErrorObject("POLA HASŁO I POTWIERDŹ HASŁO NIE SĄ TAKIE SAME");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
                }
                return  ResponseEntity.ok(userRepository.save(existingUser));
            }else{
                error = new ErrorObject("OBECNE HASŁO NIE JEST POPRAWNE");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            }
        }
        throw new Exception("NIE ZNALEZIONO UŻYTKOWNIKA");
    }


    public boolean confirmUserRegistration(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserStatus(UserStatus.AKTYWNY);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}
