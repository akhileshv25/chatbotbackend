package com.streetman.chatbot.scheduling;

import com.streetman.chatbot.Zone.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Schedules")
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleid;

    @Column(nullable = false)
    private String schedulename;

    @Column(nullable = false)
    private Long  priority;

    @Column(nullable = false)
    private Long starttime;

    @Column(nullable = false)
    private Long endtime;

    @Column(nullable = false)
    private Long startdate;

    @Column(nullable = false)
    private Long enddate;

    @Column(nullable = false)
    private  String recurrenceRule;

    @Column(nullable = false)
    private  Integer lightlevel;

    @Column(nullable = false)
    private  String lightstate;

    @ManyToOne
    @JoinColumn(name = "zoneid", nullable = false)
    private Zone zone;
}
