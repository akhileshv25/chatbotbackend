package com.streetman.chatbot.Zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;

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

        zone.setName(zoneDetails.getName());
        zone.setLatitude(zoneDetails.getLatitude());
        zone.setLongitude(zoneDetails.getLongitude());
        zone.setAddress(zoneDetails.getAddress());
        zone.setScheduleid(zoneDetails.getScheduleid());
        zone.setTimezone(zoneDetails.getTimezone());

        return zoneRepository.save(zone);
    }

    public void deleteZone(Long zoneid)
    {
        Zone zone = zoneRepository.findById(zoneid)
                .orElseThrow(() -> new RuntimeException("Zone Id Not found"));
        zoneRepository.delete(zone);
    }


}
