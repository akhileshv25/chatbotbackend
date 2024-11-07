package com.streetman.chatbot.service;

import com.streetman.chatbot.models.User;
import com.streetman.chatbot.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user)
    {
        return userRepo.save(user);
    }

    public List<User> getUser()
    {
        return userRepo.findAll();
    }

    public User findById(Long userId) {
        return userRepo.findById(userId).orElse(null); // Return null if not found
    }
}
