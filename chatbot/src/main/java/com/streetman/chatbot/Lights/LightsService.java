package com.streetman.chatbot.Lights;


import com.streetman.chatbot.scheduling.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LightsService {

    @Autowired
    private LightsRepository lightsRepository;

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
        if (lightsDetails.getZoneid() != null) {
            lights.setZoneid(lightsDetails.getZoneid());
        }


        return lightsRepository.save(lights);
    }


    //Delete

    public void deleteByScheduleId(Long lightid)
    {
        Lights lights = lightsRepository.findById(lightid).orElseThrow();
        lightsRepository.delete(lights);
    }
}
