package com.streetman.chatbot.User;

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
