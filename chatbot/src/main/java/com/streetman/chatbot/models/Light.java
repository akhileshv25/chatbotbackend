package com.streetman.chatbot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Light")
public class Light {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long lightid;

    @Column(nullable = false)
    private  String serialNumber;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private  Integer lightlevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Lightstate lightstate;

    @ManyToOne
    @JoinColumn(name = "zoneid", nullable = false)
    private Zone zone;

}
