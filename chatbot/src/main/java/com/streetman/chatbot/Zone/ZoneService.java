package com.streetman.chatbot.Zone;

import com.streetman.chatbot.Lights.LightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private LightsRepository lightsRepository;

    public Zone createZone(Zone zone)
    {
        return zoneRepository.save(zone);
    }

    public List<Zone> getAllZone()
    {
        return zoneRepository.findAll();
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
