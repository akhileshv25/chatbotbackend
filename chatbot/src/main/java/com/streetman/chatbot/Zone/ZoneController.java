package com.streetman.chatbot.Zone;


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
    private ResponseEntity<Zone> save(@RequestBody Zone zone) {

//        if(zoneService.getZoneId(zone.getZoneid())!=null)
//        {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
        Zone savedZone = zoneService.createZone(zone);
        return ResponseEntity.status(HttpStatus.OK).body(savedZone);
    }


    @GetMapping("/listAll")
    private ResponseEntity<List<Zone>> getAllZone()
    {
        List<Zone> Zone = zoneService.getAllZone();
        return  ResponseEntity.ok(Zone);
    }

    @GetMapping("/AllZone")
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
        return ResponseEntity.ok(zoneService.updateZone(zoneid,zoneDetails));
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
