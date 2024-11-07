package com.streetman.chatbot.repository;

import com.streetman.chatbot.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
    List<Message> findByUser_UserId(Long userId);
}
