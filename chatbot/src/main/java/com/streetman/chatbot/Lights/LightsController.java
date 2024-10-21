package com.streetman.chatbot.Lights;


import com.streetman.chatbot.Zone.Zone;
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

    @PutMapping("/update-state/{zoneName}")
    public ResponseEntity<String> updateLightState(@PathVariable String zoneName, @RequestBody Lights lightData) {
        List<String> lightStates = lightsService.getLightStateInZone(zoneName);

        if (lightStates.isEmpty()) {
            return ResponseEntity.badRequest().body("Zone not found.");
        }

        String currentStatus = lightStates.get(0);
        String requestedState = lightData.getLightstate();

        if (currentStatus.equals(requestedState)) {
            if (currentStatus.equals("ON")) {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                        .body("Lights are already ON.");
            } else {
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                        .body("Lights are already OFF.");
            }
        }
        boolean updated = lightsService.updateLightStateByZoneName(zoneName, lightData);

        if (updated) {
            return ResponseEntity.ok("Light state updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update light state.");
        }
    }


    @GetMapping("/lights-state/zone/{zoneName}")
    public ResponseEntity<List<String>> getAllZoneLight(@PathVariable String zoneName) {
        List<String> lights = lightsService.getLightStateInZone(zoneName);
        return ResponseEntity.ok(lights);
    }

}
