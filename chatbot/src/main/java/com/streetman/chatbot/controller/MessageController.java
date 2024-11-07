package com.streetman.chatbot.controller;

import com.streetman.chatbot.models.Message;
import com.streetman.chatbot.service.MessageService;
import com.streetman.chatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        if (message.getUser() == null || message.getContent() == null || message.getSenderType() == null) {
            return ResponseEntity.badRequest().build();
        }

        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Message>> getMessagesByUserId(@PathVariable Long userId) {
        List<Message> messages = messageService.getMessagesByUserId(userId);
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(messages);
    }


}
