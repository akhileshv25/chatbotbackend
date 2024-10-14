package com.streetman.chatbot.Zone;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Zone")
public class Zone {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneid;

    private String name;

    private Double latitude;

    private Double longitude;

    private String timezone;

    private  String Address;

    private Long scheduleid;
}
