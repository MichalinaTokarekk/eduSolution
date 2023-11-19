package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Role;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.entity.User;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

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

//    public List<User> findUsersByClassGroupId(int classGroupId) {
//        return userRepository.findUsersByClassGroupId(classGroupId);
//    }

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
            existingUser.setPassword(user.getPassword());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setCity(user.getCity());
            existingUser.setCountry(user.getCountry());
            existingUser.setAddress(user.getAddress());
            existingUser.setPost(user.getPost());
            existingUser.setPostCode(user.getPostCode());
            changeRole(user);


            // Aktualizuj teachingClassGroups
//            Set<ClassGroup> teachingClassGroups = new HashSet<>();
//            if (user.getClassGroups() != null) {
//                for (ClassGroup teachingClassGroup : user.getClassGroups()) {
//                    ClassGroup existingTeachingClassGroup = classGroupRepository.findById(teachingClassGroup.getId()).orElse(null);
//                    if (existingTeachingClassGroup != null) {
//                        teachingClassGroups.add(existingTeachingClassGroup);
//                    }
//                }
//            }
//            existingUser.setClassGroups(teachingClassGroups);
        }
//        autenticationService.revokeAllUserTokens(user);

        return userRepository.save(existingUser);
    }

    public User changeRole (User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

//    public Set<ClassGroup> findTeachingClassGroupsById(Integer userId) {
//        return userRepository.findTeachingClassGroupsById(userId);
//    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public List<ClassGroup> findClassGroupsById(Integer userId) {
        return userRepository.findClassGroupsById(userId);

    }

    public List<User> findUsersByClassGroupId(Integer classGroupId) {
        return userRepository.findUsersByClassGroupId(classGroupId);
    }
}
