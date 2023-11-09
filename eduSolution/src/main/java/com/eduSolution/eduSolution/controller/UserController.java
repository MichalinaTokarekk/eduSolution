package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Role;
import com.eduSolution.eduSolution.entity.User;
import com.eduSolution.eduSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/addUser")
    public User addUser (@RequestBody User user){
        return userService.saveUser(user);
    }
    @PostMapping("/addUsers")
    public List<User> addUsers (@RequestBody List<User> users){
        return userService.saveUsers(users);
    }
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

    @GetMapping("/teachingClassGroups/{userId}")
    public Set<ClassGroup> getTeachingClassGroupsByUserId(@PathVariable Integer userId) {
        return userService.findTeachingClassGroupsById(userId);
    }

    @GetMapping("/findUsersByClassGroupId/{classGroupId}")
    public List<User> findUsersByClassGroupId(@PathVariable int classGroupId) {
        return userService.findUsersByClassGroupId(classGroupId);
    }
}
