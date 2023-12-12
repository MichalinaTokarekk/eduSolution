package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.dto.PasswordDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Role;
import com.eduSolution.eduSolution.entity.User;
import com.eduSolution.eduSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user-controller")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.getUsers();
    }
    @GetMapping ("/user/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping ("/userEmail/{email}")
    public Optional<User> findUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    @GetMapping ("/username/{username}")
    public Optional <User> findUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/usersByRole")
    public List<User> getUsersByRole(@RequestParam Role role) {
        return userService.getUsersByRole(role);
    }
    @DeleteMapping("/deleteUser/{id}")
    public DeleteResponseDTO deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
    @PutMapping("/updateUser")
    public User updateUser (@RequestBody User user) {
        return userService.updateUser(user);
    }
    @PutMapping("/changeRole")
    public User changeRole (@RequestBody User user) {
        return userService.changeRole(user);
    }

    @GetMapping("/findClassGroupsById/{userId}")
    public List<ClassGroup> findClassGroupsById(@PathVariable Integer userId) {
        return userService.findClassGroupsById(userId);
    }

    @GetMapping("/findUsersByClassGroupId/{classGroupId}")
    public List<User> findUsersByClassGroupId(@PathVariable Integer classGroupId){
        return userService.findUsersByClassGroupId(classGroupId);
    }


    @PutMapping("/changePassword")
    public ResponseEntity <?> changePassword (@RequestBody PasswordDTO passwordDTO) throws Exception {
        User user = new User();
        user.setId(passwordDTO.getId());
        String oldPassword = passwordDTO.getOldPassword();
        String newPassword = passwordDTO.getNewPassword();
        String newPasswordConfirm= passwordDTO.getNewPasswordConfirm();
        return userService.changePassword(user,oldPassword,newPassword,newPasswordConfirm);
    }
}
