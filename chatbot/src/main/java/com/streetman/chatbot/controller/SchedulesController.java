package com.streetman.chatbot.controller;


import com.streetman.chatbot.models.Schedule;
import com.streetman.chatbot.service.SchedulesService;
import com.streetman.chatbot.service.ZoneService;
import com.sun.jdi.event.StepEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/schedules")
public class SchedulesController {


    @Autowired
    private SchedulesService schedulesService;

    @PostMapping("/save")
    public ResponseEntity<Schedule> save(@RequestBody Schedule schedules)
    {
        Schedule saveSchedules = schedulesService.createSechedules(schedules);
        return ResponseEntity.ok(saveSchedules);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Schedule>> getAllSchedules()
    {
        List<Schedule> schedules = schedulesService.getAllSechedules();
        return ResponseEntity.ok(schedules);
    }
    @GetMapping("/listbyzone/{zoneName}")
    public ResponseEntity<List<Schedule>> getSchedulesByZoneName(@PathVariable String zoneName)
    {
        List<Schedule> schedules = schedulesService.getScheduleInZone(zoneName);
        return ResponseEntity.ok(schedules);
    }
    @GetMapping("/list/{scheduleid}")
    public  ResponseEntity<Schedule> getSchedulesById(@PathVariable Long scheduleid)
    {
        Schedule schedules = schedulesService.getBySchedulesId(scheduleid);
        if(schedules!=null)
        {
            return ResponseEntity.ok(schedules);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("list/{id}")
    public ResponseEntity<Schedule> updateSchedules(@PathVariable("id") Long scheduleid , @RequestBody Schedule scheduleDetasils)
    {
        return  ResponseEntity.ok(schedulesService.updateSchedules(scheduleid,scheduleDetasils));
    }

    @DeleteMapping("/remove/{id}")
    private ResponseEntity<Void> deleteZone(@PathVariable("id") Long scheduleid)
    {
        try {

            schedulesService.deleteByScheduleId(scheduleid);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/remove/byname/{schedulename}")
    private ResponseEntity<Void> deleteZone(@PathVariable("schedulename") String schedulename)
    {
        try {

            schedulesService.deleteScheduleByName(schedulename);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
