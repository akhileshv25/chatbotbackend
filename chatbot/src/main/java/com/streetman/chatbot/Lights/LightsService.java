package com.streetman.chatbot.Lights;


import com.streetman.chatbot.Zone.Zone;
import com.streetman.chatbot.Zone.ZoneRepository;
import com.streetman.chatbot.scheduling.Schedules;
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
    public Lights createLights(Lights lights)
    {
        return lightsRepository.save(lights);
    }

    //GET

    public List<Lights> getAllLights()
    {
        return lightsRepository.findAll();
    }

    //GET

    public  Lights getLightById(Long lightid)
    {
        return lightsRepository.findById(lightid).orElseThrow();
    }

    //PUT

    public Lights updateLights(Long lightid, Lights lightsDetails) {
        Lights lights = getLightById(lightid);

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

    public void deleteByScheduleId(Long lightid)
    {
        Lights lights = lightsRepository.findById(lightid).orElseThrow();
        lightsRepository.delete(lights);
    }

        @Transactional
        public boolean updateLightStateByZoneName(String zoneName, Lights lightData) {
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

    public List<String> getLightStateInZone(String zoneName) {
        Optional<Zone> optionalZone = zoneRepository.findByName(zoneName);

        if (optionalZone.isEmpty()) {
            return Collections.emptyList();
        }

        Zone zone = optionalZone.get();

        List<Lights> lightsInZone = zone.getLights();

        List<String> lightStates = new ArrayList<>();

        for (Lights light : lightsInZone) {
            lightStates.add(light.getLightstate());
        }

        return lightStates;
    }

}
