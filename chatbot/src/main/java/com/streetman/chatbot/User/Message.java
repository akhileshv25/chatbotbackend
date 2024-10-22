package com.streetman.chatbot.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;


    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String senderType;

    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp createdAt;

}