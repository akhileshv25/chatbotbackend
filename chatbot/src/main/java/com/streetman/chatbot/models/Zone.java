package com.streetman.chatbot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Zone")
public class Zone {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String timezone;

    @Column(nullable = false)
    private  String address;


    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Light> lights;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Schedule> schedules;
}
