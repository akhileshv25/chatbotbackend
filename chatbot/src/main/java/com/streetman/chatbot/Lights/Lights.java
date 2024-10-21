package com.streetman.chatbot.Lights;

import com.streetman.chatbot.Zone.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Light")
public class Lights {

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
    private String lightstate;

    @ManyToOne
    @JoinColumn(name = "zoneid", nullable = false)
    private Zone zone;

}
