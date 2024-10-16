package com.streetman.chatbot.Lights;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lights")
public class LightsController {

    @Autowired
    private LightsService lightsService;

    @PostMapping("/save")
    public ResponseEntity<Lights> save(@RequestBody Lights lights)
    {
        Lights saveLights = lightsService.createLights(lights);
        return ResponseEntity.ok(saveLights);

    }

    @GetMapping("/list")
    public ResponseEntity<List<Lights>> getAllLights()
    {
        List<Lights> lights = lightsService.getAllLights();
        return  ResponseEntity.ok(lights);
    }

    @GetMapping("/list/{lightid}")
    public ResponseEntity<Lights> getLightsById(@PathVariable Long lightid)
    {
        Lights lights = lightsService.getLightById(lightid);
        return ResponseEntity.ok(lights);
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Lights> updateLights(@PathVariable("id") Long lightid ,@RequestBody Lights lights)
    {
       return  ResponseEntity.ok(lightsService.updateLights(lightid,lights));
    }

    @DeleteMapping("/remove/{id}")
    private ResponseEntity<Void> deleteZone(@PathVariable("id") Long ligthid)
    {
        try {

            lightsService.deleteByScheduleId(ligthid);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
