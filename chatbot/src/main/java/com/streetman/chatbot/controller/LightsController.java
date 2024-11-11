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
    public ResponseEntity<String> updateLightState(@PathVariable("id") Long lightid, @RequestBody Light lightData) {
        String resultMessage = lightsService.processTheLightState(lightid, lightData);

        switch (resultMessage) {
            case "Light is already ON":
            case "Light is already OFF":
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(resultMessage);
            case "Light has been turned ON":
            case "Light has been turned OFF":
                return ResponseEntity.ok(resultMessage);
            default:
                return ResponseEntity.badRequest().body("Failed to update light state.");
        }
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

    @PutMapping("/update/state/{zoneName}")
    public ResponseEntity<String> updateLightState(@PathVariable String zoneName, @RequestBody Light lightData) {
        String resultMessage = lightsService.processLightStateUpdate(zoneName, lightData);

        switch (resultMessage) {
            case "Zone not found":
                return ResponseEntity.badRequest().body(resultMessage);

            case "All lights in zone are already ON.":
            case "All lights in zone are already OFF.":
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(resultMessage);

            case "Turning on all lights in zone.":
            case "Turning off all lights in zone.":
                return ResponseEntity.ok(resultMessage);

            default:
                return ResponseEntity.badRequest().body("Unexpected response: " + resultMessage);
        }
    }



    @GetMapping("/lights-state/zone/{zoneName}")
    public ResponseEntity<List<Lightstate>> getAllZoneLight(@PathVariable String zoneName) {
        List<Lightstate> lights = lightsService.getLightStateInZone(zoneName);
        return ResponseEntity.ok(lights);
    }

    @GetMapping("/zone/lightslevel/{zoneName}")
    public ResponseEntity<List<Integer>> getlightlevel(@PathVariable String zoneName) {
        List<Integer> lights = lightsService.getBrightnessInZone(zoneName);
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

    @PutMapping("/brightness/update/{lightid}")
    public  ResponseEntity<String> updateZoneLightBrightness(@PathVariable Long lightid, @RequestParam Integer brightnessLevel)
    {
        boolean isUpdated = lightsService.updateLightBrightness(lightid,brightnessLevel);
        if (isUpdated) {
            return ResponseEntity.ok("Brightness updated successfully for light: "+lightid);
        } else {
            return ResponseEntity.status(404).body("light with id " + lightid + " not found.");
        }
    }

    @GetMapping("zone/light/{zoneName}")
    public ResponseEntity<List<Light>> getZoneLight(@PathVariable String zoneName)
    {
        List<Light> LightInZone = lightsService.getLightByZone(zoneName);
        return ResponseEntity.ok(LightInZone);
    }
}
