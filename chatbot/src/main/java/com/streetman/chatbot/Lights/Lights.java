package com.streetman.chatbot.Lights;

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

    private  String serialNumber;

    private String model;

    private  Integer lightlevel;

    private String lightstate;

    private  Long zoneid;

}
