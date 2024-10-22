package com.streetman.chatbot.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;


    public Message saveMessage(Message message) {
        return messageRepo.save(message);
    }

    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepo.findByUser_UserId(userId);
    }

}
