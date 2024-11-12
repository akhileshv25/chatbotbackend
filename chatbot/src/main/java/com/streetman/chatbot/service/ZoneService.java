package com.streetman.chatbot.service;

import com.streetman.chatbot.repository.LightsRepository;
import com.streetman.chatbot.models.Zone;
import com.streetman.chatbot.repository.ZoneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private LightsRepository lightsRepository;

    public List<Zone> createZone(List<Zone> zone)
    {
        return zoneRepository.saveAll(zone);
    }

    public List<Zone> getAllZone()
    {
        return zoneRepository.findAll();
    }


    public List<String> getZoneNames() {
        return zoneRepository.findAllZoneNames();
    }

    public Zone getZoneByName(String name) {
        Optional<Zone> zoneOptional = zoneRepository.findByName(name);

        if (zoneOptional.isPresent()) {
            return zoneOptional.get();
        } else {
            throw new EntityNotFoundException("Zone not found with name: " + name);
        }
    }


    public Zone getZoneId(Long zoneid)
    {
        return zoneRepository.findById(zoneid)
                .orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    public  Zone updateZone(Long zoneid , Zone zoneDetails)
    {
        Zone zone =  getZoneId(zoneid);

        if(zoneDetails.getName()!=null) {
            zone.setName(zoneDetails.getName());
        }
        if(zoneDetails.getLatitude()!=null) {
            zone.setLatitude(zoneDetails.getLatitude());
        }
        if(zoneDetails.getLongitude()!=null) {
            zone.setLongitude(zoneDetails.getLongitude());
        }
        if(zoneDetails.getAddress()!=null) {
            zone.setAddress(zoneDetails.getAddress());
        }
        System.out.println(zoneDetails.getAddress());

        if(zoneDetails.getTimezone()!=null) {
            zone.setTimezone(zoneDetails.getTimezone());
        }

        return zoneRepository.save(zone);
    }

    public void deleteZone(Long zoneid)
    {
        Zone zone = zoneRepository.findById(zoneid)
                .orElseThrow(() -> new RuntimeException("Zone Id Not found"));
        zoneRepository.delete(zone);
    }





}
