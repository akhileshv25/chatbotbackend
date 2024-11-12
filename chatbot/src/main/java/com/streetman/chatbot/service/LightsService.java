package com.streetman.chatbot.service;


import com.streetman.chatbot.models.Light;
import com.streetman.chatbot.models.Lightstate;
import com.streetman.chatbot.models.Zone;
import com.streetman.chatbot.repository.LightsRepository;
import com.streetman.chatbot.repository.ZoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LightsService {

    @Autowired
    private LightsRepository lightsRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    //POST
    public List<Light> createLights(List<Light> lights)
    {
        return lightsRepository.saveAll(lights);
    }

    //GET

    public List<Light> getAllLights()
    {
        return lightsRepository.findAll();
    }

    //GET

    public Light getLightById(Long lightid)
    {
        return lightsRepository.findById(lightid).orElseThrow(
                () -> new RuntimeException ("Id Not Found")
        );
    }

    //PUT

    public Light updateLights(Long lightid, Light lightsDetails) {
        Light lights = getLightById(lightid);

        lights.setSerialNumber(Optional.ofNullable(lightsDetails.getSerialNumber()).orElse(lights.getSerialNumber()));
        lights.setModel(Optional.ofNullable(lightsDetails.getModel()).orElse(lights.getModel()));
        lights.setLightstate(Optional.ofNullable(lightsDetails.getLightstate()).orElse(lights.getLightstate()));
        lights.setLightlevel(Optional.ofNullable(lightsDetails.getLightlevel()).orElse(lights.getLightlevel()));


        return lightsRepository.save(lights);
    }


    //Delete

    public void deleteByLightId(Long lightid)
    {
        Light lights = lightsRepository.findById(lightid).orElseThrow(() -> new RuntimeException("Light Id Not Found"));
        lightsRepository.delete(lights);
    }

        @Transactional
        public boolean updateLightStateByZoneName(String zoneName, Light lightData) {
            Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

            if (optionalZone.isEmpty()) {
                return false;
            }

            Zone zone = optionalZone.get();

            zone.getLights().forEach(light -> {
                light.setLightstate(lightData.getLightstate());
                lightsRepository.save(light);
            });

            return true;
        }

    public List<Lightstate> getLightStateInZone(String zoneName) {
        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

        if (optionalZone.isEmpty()) {
            return Collections.emptyList();
        }

        Zone zone = optionalZone.get();

        List<Light> lightsInZone = zone.getLights();

        List<Lightstate> lightStates = new ArrayList<>();

        for (Light light : lightsInZone) {
            lightStates.add(light.getLightstate());
        }

        return lightStates;
    }
    public List<Integer> getBrightnessInZone(String zoneName) {
        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

        if (optionalZone.isEmpty()) {
            return Collections.emptyList();
        }

        Zone zone = optionalZone.get();

        List<Light> zoneBrightness = zone.getLights();

        List<Integer> lightlevel = new ArrayList<>();

        for (Light light : zoneBrightness) {
            lightlevel.add(light.getLightlevel());
        }

        return lightlevel;
    }

    @Transactional
    public boolean updateLightBrightnessByZoneName(String zoneName, Integer brightnessLevel) {
        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

        if (optionalZone.isEmpty()) {
            return false;
        }

        Zone zone = optionalZone.get();
        for (Light light : zone.getLights()) {
            light.setLightlevel(brightnessLevel);
            lightsRepository.save(light);
        }

        return true;
    }

//    @Transactional
//    public boolean updateLightBrightnessByZoneNameAndSerial(String zoneName, Long lightid, Integer brightnessLevel) {
//        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);
//
//        if (optionalZone.isEmpty()) {
//            return false;
//        }
//
//        Zone zone = optionalZone.get();
//        Optional<Light> optionalLight = zone.getLights().stream()
//                .filter(light -> light.getLightid().equals(lightid))
//                .findFirst();
//
//        if (optionalLight.isEmpty()) {
//            return false;
//        }
//
//        Light light = optionalLight.get();
//        light.setLightlevel(brightnessLevel);
//        lightsRepository.save(light);
//
//        return true;
//    }

    public boolean updateLightBrightness(Long lightid, Integer requestLevel) {
        Optional<Light> existingLightOptional = lightsRepository.findById(lightid);

        if (existingLightOptional.isPresent()) {
            Light existingLight = existingLightOptional.get();
            existingLight.setLightlevel(requestLevel);
            lightsRepository.save(existingLight);
            return true;
        } else {
            return false;
        }
    }

    public List<Light> getLightByZone(String zoneName) {
        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

        if (optionalZone.isEmpty()) {
            return Collections.emptyList();
        }

        Zone zone = optionalZone.get();

        return zone.getLights();
    }

    public String processLightStateUpdate(String zoneName, Light lightData) {
        List<Lightstate> lightStates = getLightStateInZone(zoneName);

        if (lightStates.isEmpty()) {
            return "Zone not found";
        }

        Lightstate requestedState = lightData.getLightstate();
        boolean allOn = lightStates.stream().allMatch(state -> state == Lightstate.ON);
        boolean allOff = lightStates.stream().allMatch(state -> state == Lightstate.OFF);

        if (requestedState == Lightstate.OFF) {
            if (allOff) {
                return "All lights in zone are already OFF.";
            } else {
                updateLightStateByZoneName(zoneName, lightData);
                return "Turning off all lights in zone.";
            }
        } else if (requestedState == Lightstate.ON) {
            if (allOn) {
                return "All lights in zone are already ON.";
            } else {
                updateLightStateByZoneName(zoneName, lightData);
                return "Turning on all lights in zone.";
            }
        }
        return "Failed to update light state";
    }

    public String processTheLightState(Long lightid, Light lightData) {
        Light existingLight = getLightById(lightid);
        Lightstate currentState = existingLight.getLightstate();
        Lightstate requestedState = lightData.getLightstate();

        if (currentState == requestedState) {
            return currentState == Lightstate.ON ? "Light is already ON" : "Light is already OFF";
        }

        existingLight.setLightstate(requestedState);
        lightsRepository.save(existingLight);

        return requestedState == Lightstate.ON ? "Light has been turned ON" : "Light has been turned OFF";
    }

}
