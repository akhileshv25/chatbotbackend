package com.streetman.chatbot.scheduling;

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
    private  Long id;

    private Long scheduleid;

    private Long  priority;

    private Long starttime;

    private Long endtime;

    private Long startdate;

    private Long enddate;

    private  String recurrenceRule;

    private  Integer lightlevel;

    private  String lightstate;

}
