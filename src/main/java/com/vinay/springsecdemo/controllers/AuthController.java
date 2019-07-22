package com.vinay.springsecdemo.controllers;

import com.vinay.springsecdemo.models.Role;
import com.vinay.springsecdemo.models.Token;
import com.vinay.springsecdemo.models.User;
import com.vinay.springsecdemo.repositories.RoleRepository;
import com.vinay.springsecdemo.repositories.UserRepository;
import com.vinay.springsecdemo.services.RoleService;
import com.vinay.springsecdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        user = userService.save(user);
        user.setPassword("");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Token> loginUser(@RequestBody User user){
        Token token = userService.loginUser(user);
        token.getUser().setPassword(null);
        return ResponseEntity.ok(token);
    }

}
