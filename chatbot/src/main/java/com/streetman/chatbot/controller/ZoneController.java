package com.streetman.chatbot.controller;


import com.streetman.chatbot.models.Zone;
import com.streetman.chatbot.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/save")
    private ResponseEntity<List<Zone>> save(@RequestBody List<Zone> zones) {

//        if(zoneService.getZoneId(zone.getZoneid())!=null)
//        {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
       List<Zone> savedZone = zoneService.createZone(zones);
        return ResponseEntity.ok(savedZone);
    }


    @GetMapping("/list")
    private ResponseEntity<List<Zone>> getAllZone()
    {
        List<Zone> Zone = zoneService.getAllZone();
        return  ResponseEntity.ok(Zone);
    }

    @GetMapping("/list/zonename")
    private ResponseEntity<List<String>> getZoneName()
    {
        List<String> zone = zoneService.getZoneNames();
        return ResponseEntity.ok(zone);
    }

    @GetMapping("/{id}")
    private  ResponseEntity<Zone> getzoneId(@PathVariable Long zoneid)
    {
        Zone zone = zoneService.getZoneId(zoneid);
        if(zone!=null)
        {
            return ResponseEntity.ok(zone);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byName/{name}")
    private ResponseEntity<Long> getZoneIdByName(@PathVariable String name) {
        Zone zone = zoneService.getZoneByName(name);
        if (zone != null) {
            return ResponseEntity.ok(zone.getZoneid());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/list/{id}")
    private ResponseEntity<Zone> updateZone(@PathVariable("id") Long zoneid , @RequestBody Zone zoneDetails)
    {
        Zone zone = zoneService.updateZone(zoneid,zoneDetails);
        return ResponseEntity.ok(zone);
    }


    @DeleteMapping("/remove/{id}")
    private ResponseEntity<Void> deleteZone(@PathVariable("id") Long zoneid)
    {
        try {

            zoneService.deleteZone(zoneid);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
