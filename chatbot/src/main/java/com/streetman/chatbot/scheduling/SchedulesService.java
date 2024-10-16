package com.streetman.chatbot.scheduling;

import com.streetman.chatbot.Lights.Lights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;
    //POST
    public Schedules createSechedules(Schedules schedules)
    {
        return schedulesRepository.save(schedules);
    }

    //GET
    public List<Schedules> getAllSechedules()
    {
        return schedulesRepository.findAll();
    }
    //GET
    public Schedules getBySchedulesId(Long scheduleid)
    {
        return schedulesRepository.findById(scheduleid) .orElseThrow(() -> new RuntimeException("schedules not found"));
    }

    //PUT

    public Schedules updateSchedules(Long scheduleid,Schedules schedulesDetails)
    {
        Schedules schedules = getBySchedulesId(scheduleid);

        schedules.setPriority(schedulesDetails.getPriority());
        schedules.setStarttime(schedulesDetails.getStarttime());
        schedules.setEndtime(schedulesDetails.getEndtime());
        schedules.setEnddate(schedulesDetails.getEnddate());
        schedules.setStartdate(schedulesDetails.getStartdate());
        schedules.setRecurrenceRule(schedulesDetails.getRecurrenceRule());
        schedules.setLightlevel(schedulesDetails.getLightlevel());
        schedules.setLightstate(schedulesDetails.getLightstate());

        return schedulesRepository.save(schedules);
    }

    public void deleteByScheduleId(Long scheduleid)
    {
        Schedules schedules = schedulesRepository.findById(scheduleid).orElseThrow();
        schedulesRepository.delete(schedules);
    }
}
