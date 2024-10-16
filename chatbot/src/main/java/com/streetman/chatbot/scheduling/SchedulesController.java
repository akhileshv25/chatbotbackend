package com.streetman.chatbot.scheduling;


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
    public ResponseEntity<Schedules> save(@RequestBody Schedules schedules)
    {
        Schedules saveSchedules = schedulesService.createSechedules(schedules);
        return ResponseEntity.status(HttpStatus.OK).body(saveSchedules);
    }


    @GetMapping("/list")
    public ResponseEntity<List<Schedules>> getAllSchedules()
    {
        List<Schedules> schedules = schedulesService.getAllSechedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/list/{scheduleid}")
    public  ResponseEntity<Schedules> getSchedulesById(@PathVariable Long scheduleid)
    {
        Schedules schedules = schedulesService.getBySchedulesId(scheduleid);
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
    public ResponseEntity<Schedules> updateSchedules(@PathVariable("id") Long scheduleid , @RequestBody Schedules scheduleDetasils)
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
}
