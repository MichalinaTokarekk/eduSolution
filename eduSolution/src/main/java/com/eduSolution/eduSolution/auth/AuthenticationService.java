package com.eduSolution.eduSolution.auth;

import com.eduSolution.eduSolution.config.JwtService;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Role;
import com.eduSolution.eduSolution.entity.User;
import com.eduSolution.eduSolution.entity.UserStatus;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import com.eduSolution.eduSolution.service.EmailService;
import com.eduSolution.eduSolution.token.Token;
import com.eduSolution.eduSolution.token.TokenRepository;
import com.eduSolution.eduSolution.token.TokenType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final ClassGroupRepository classGroupRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private EmailService emailService;
    public AuthenticationResponse register(RegisterRequest request) {
//        Set<ClassGroup> teachingClassGroups = new HashSet<>();
//        if (request.getTeachingClassGroups() != null && !request.getTeachingClassGroups().isEmpty()) {
//            for (Integer classGroupId : request.getTeachingClassGroups()) {
//                ClassGroup classGroup = classGroupRepository.findById(classGroupId)
//                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klasy o podanym ID: " + classGroupId));
//                teachingClassGroups.add(classGroup);
//            }
//        }
//
//        int classGroupId = request.getClassGroup();
//        ClassGroup classGroup = classGroupRepository.findById(classGroupId)
//                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klasy o podanym ID: " + classGroupId));

//        if (request.getTeachingClassGroups() != null && !request.getTeachingClassGroups().isEmpty()) {
//            for (Integer classGroupId : request.getTeachingClassGroups()) {
//                ClassGroup classGroup = classGroupRepository.findById(classGroupId)
//                        .orElse(null); // Zamiast zgłaszania wyjątku, użyj null, jeśli klasa nie istnieje
//                if (classGroup != null) {
//                    teachingClassGroups.add(classGroup);
//                }
//            }
//        }

//        int classGroupId = request.getClassGroup();
//        ClassGroup classGroup = classGroupRepository.findById(classGroupId)
//                .orElse(null); // Zamiast zgłaszania wyjątku, użyj null, jeśli klasa nie istnieje

        Random random = new Random();

        int losowyIndex = random.nextInt(100);

        Set<ClassGroup> classGroups = new HashSet<>();
        if (request.getClassGroups() != null && !request.getClassGroups().isEmpty()) {
            for (Integer classGroupId : request.getClassGroups()) {
                ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                        .orElse(null); // Zamiast zgłaszania wyjątku, użyj null, jeśli klasa nie istnieje
                if (classGroup != null) {
                    classGroups.add(classGroup);
                }
            }
        }

//        Set<ClassGroup> classGroups = request.getClassGroups().stream()
//                .map(classGroupId -> classGroupRepository.findById(classGroupId)
//                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klasy o podanym ID: " + classGroupId)))
//                .collect(Collectors.toSet());



        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .city(request.getCity())
                .post(request.getPost())
                .postCode(request.getPostCode())
                .country(request.getCountry())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .userStatus(UserStatus.AKTYWNY)
                .classGroups(classGroups)
                .build();
        String username2 = user.getFirstName() + " " + user.getLastName();
        user.setUsername(username2);
        var savedUser = userRepository.save(user);
        var claimsMap = new HashMap<String,Object>();
        claimsMap.put("role", user.getRole().toString().toLowerCase());
        claimsMap.put("id", user.getId());
        var jwtToken = jwtService.generateToken(claimsMap, user);
        saveUserToken(savedUser, jwtToken);

        String confirmationLink = "http://localhost:9191/confirm/" + savedUser.getId();
        String subject = "Potwierdzenie rejestracji";
        String body = "Dziękujemy za rejestrację! Kliknij poniższy link, aby potwierdzić rejestrację:\n" + confirmationLink;

        emailService.sendConfirmationEmail(user.getEmail(), subject, body);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    private String generateRandomPassword() {
        int passwordLength = 10;
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = secureRandom.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(randomIndex));
        }

        return password.toString();
    }

    public AuthenticationResponse registerCreate(RegisterRequest request) {

        Random random = new Random();

        int losowyIndex = random.nextInt(100);

        Set<ClassGroup> classGroups = new HashSet<>();
        if (request.getClassGroups() != null && !request.getClassGroups().isEmpty()) {
            for (Integer classGroupId : request.getClassGroups()) {
                ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                        .orElse(null); // Zamiast zgłaszania wyjątku, użyj null, jeśli klasa nie istnieje
                if (classGroup != null) {
                    classGroups.add(classGroup);
                }
            }
        }

        String generatedPassword = generateRandomPassword();

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .address(request.getAddress())
                .city(request.getCity())
                .post(request.getPost())
                .postCode(request.getPostCode())
                .country(request.getCountry())
                .email(request.getEmail())
                .password(passwordEncoder.encode(generatedPassword))
                .role(Role.USER)
                .userStatus(UserStatus.AKTYWNY)
                .classGroups(classGroups)
                .build();
        String username2 = user.getFirstName() + " " + user.getLastName();
        user.setUsername(username2);
        var savedUser = userRepository.save(user);
        var claimsMap = new HashMap<String,Object>();
        claimsMap.put("role", user.getRole().toString().toLowerCase());
        claimsMap.put("id", user.getId());
        var jwtToken = jwtService.generateToken(claimsMap, user);
        saveUserToken(savedUser, jwtToken);

        String subject = "Potwierdzenie rejestracji";
        String body = "Dziękujemy za rejestrację! Tu twoje hasło:" + generatedPassword + "Zalecana zmiana hasła na własne";

        emailService.sendConfirmationEmail(user.getEmail(), subject, body);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }





    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var claimsMap = new HashMap<String,Object>();
        claimsMap.put("role", user.getRole().toString().toLowerCase());
        claimsMap.put("id", user.getId());
        var jwtToken = jwtService.generateToken((Map<String, Object>) claimsMap, (UserDetails) user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    private void revokeAllUserTokens(User user) {
        var validUserToken = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserToken.isEmpty())
            return;
        validUserToken.forEach(t  -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
}
