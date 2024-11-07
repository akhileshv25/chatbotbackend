package com.streetman.chatbot.service;

import com.streetman.chatbot.models.Message;
import com.streetman.chatbot.repository.MessageRepo;
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
