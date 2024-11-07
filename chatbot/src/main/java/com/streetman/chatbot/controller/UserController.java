package com.streetman.chatbot.controller;

import com.streetman.chatbot.models.User;
import com.streetman.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<User> save(@RequestBody  User user)
    {
        User saveUser = userService.createUser(user);

        return ResponseEntity.ok(saveUser);
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<User>> getUser()
    {
        List<User> user = userService.getUser();

        return ResponseEntity.ok(user);
    }
}
