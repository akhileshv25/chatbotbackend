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
    public Light createLights(Light lights)
    {
        return lightsRepository.save(lights);
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

        if (lightsDetails.getSerialNumber() != null) {
            lights.setSerialNumber(lightsDetails.getSerialNumber());
        }
        if (lightsDetails.getModel() != null) {
            lights.setModel(lightsDetails.getModel());
        }
        if (lightsDetails.getLightstate() != null) {
            lights.setLightstate(lightsDetails.getLightstate());
        }
        if (lightsDetails.getLightlevel() != null) {
            lights.setLightlevel(lightsDetails.getLightlevel());
        }


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

    @Transactional
    public boolean updateLightBrightnessByZoneNameAndSerial(String zoneName, Long lightid, Integer brightnessLevel) {
        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

        if (optionalZone.isEmpty()) {
            return false;
        }

        Zone zone = optionalZone.get();
        Optional<Light> optionalLight = zone.getLights().stream()
                .filter(light -> light.getLightid().equals(lightid))
                .findFirst();

        if (optionalLight.isEmpty()) {
            return false;
        }

        Light light = optionalLight.get();
        light.setLightlevel(brightnessLevel);
        lightsRepository.save(light);

        return true;
    }


    public List<Light> getLightByZone(String zoneName) {
        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

        if (optionalZone.isEmpty()) {
            return Collections.emptyList();
        }

        Zone zone = optionalZone.get();

        return zone.getLights();
    }

}
