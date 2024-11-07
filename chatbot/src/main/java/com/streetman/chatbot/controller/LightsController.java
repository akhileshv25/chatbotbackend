package com.streetman.chatbot.controller;


import com.streetman.chatbot.models.Light;
import com.streetman.chatbot.models.Lightstate;
import com.streetman.chatbot.service.LightsService;
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
    public ResponseEntity<Light> save(@RequestBody Light lights)
    {
        Light saveLights = lightsService.createLights(lights);
        return ResponseEntity.ok(saveLights);

    }

    @GetMapping("/list")
    public ResponseEntity<List<Light>> getAllLights()
    {
        List<Light> lights = lightsService.getAllLights();
        return  ResponseEntity.ok(lights);
    }

    @GetMapping("/list/{lightid}")
    public ResponseEntity<Light> getLightsById(@PathVariable Long lightid)
    {
        Light lights = lightsService.getLightById(lightid);
        return ResponseEntity.ok(lights);
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Light> updateLights(@PathVariable("id") Long lightid , @RequestBody Light lights)
    {
       return  ResponseEntity.ok(lightsService.updateLights(lightid,lights));
    }

    @DeleteMapping("/remove/{id}")
    private ResponseEntity<Void> deleteZone(@PathVariable("id") Long ligthid)
    {
        try {
            lightsService.deleteByLightId(ligthid);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
          //  throw new RuntimeException(e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update-state/{zoneName}")
    public ResponseEntity<String> updateLightState(@PathVariable String zoneName, @RequestBody Light lightData) {
        List<Lightstate> lightStates = lightsService.getLightStateInZone(zoneName);

        if (lightStates.isEmpty()) {
            return ResponseEntity.badRequest().body("Zone not found.");
        }

        Lightstate currentStatus = lightStates.get(0);
        Lightstate requestedState = lightData.getLightstate();

        if (currentStatus == requestedState) {
            if (currentStatus == Lightstate.ON) {
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
    public ResponseEntity<List<Lightstate>> getAllZoneLight(@PathVariable String zoneName) {
        List<Lightstate> lights = lightsService.getLightStateInZone(zoneName);
        return ResponseEntity.ok(lights);
    }
    @PutMapping("/update-brightness/{zoneName}")
    public ResponseEntity<String> updateLightBrightness(@PathVariable String zoneName, @RequestParam Integer brightnessLevel) {
        boolean isUpdated = lightsService.updateLightBrightnessByZoneName(zoneName, brightnessLevel);

        if (isUpdated) {
            return ResponseEntity.ok("Brightness updated successfully for zone: " + zoneName);
        } else {
            return ResponseEntity.status(404).body("Zone with name " + zoneName + " not found.");
        }
    }

    @PutMapping("/brightness/update/zone/light/{zoneName}")
    public  ResponseEntity<String> updateZoneLightBrightness(@PathVariable String zoneName, @RequestParam Long lightid,@RequestParam Integer brightnessLevel)
    {
        boolean isUpdated = lightsService.updateLightBrightnessByZoneNameAndSerial(zoneName,lightid,brightnessLevel);
        if (isUpdated) {
            return ResponseEntity.ok("Brightness updated successfully for light: "+lightid+ "Zone " + zoneName);
        } else {
            return ResponseEntity.status(404).body("Zone with name " + zoneName + " not found.");
        }
    }
}
