package com.eduSolution.eduSolution.auth;

import com.eduSolution.eduSolution.config.JwtService;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Role;
import com.eduSolution.eduSolution.entity.User;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import com.eduSolution.eduSolution.token.Token;
import com.eduSolution.eduSolution.token.TokenRepository;
import com.eduSolution.eduSolution.token.TokenType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final ClassGroupRepository classGroupRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        Set<ClassGroup> teachingClassGroups = new HashSet<>();
        for (Integer classGroupId : request.getTeachingClassGroups()) {
            ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                    .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klasy o podanym ID: " + classGroupId));
            teachingClassGroups.add(classGroup);
        }
        int classGroupId = request.getClassGroup();
        ClassGroup classGroup = classGroupRepository.findById(classGroupId)
                .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono klasy o podanym ID: " + classGroupId));


        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .streetName(request.getStreetName())
                .buildingNumber(request.getBuildingNumber())
                .apartmentNumber(request.getApartmentNumber())
                .city(request.getCity())
                .post(request.getPost())
                .postCode(request.getPostCode())
                .country(request.getCountry())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .classGroup(classGroup)
                .teachingClassGroups(teachingClassGroups)
                .build();
        String username2 = user.getFirstName() + " " + user.getLastName();
        user.setUsername(username2);
        var savedUser = userRepository.save(user);
        var claimsMap = new HashMap<String,Object>();
        claimsMap.put("role", user.getRole().toString().toLowerCase());
        claimsMap.put("id", user.getId());
        var jwtToken = jwtService.generateToken(claimsMap, user);
        saveUserToken(savedUser, jwtToken);
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
