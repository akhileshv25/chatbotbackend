package com.streetman.chatbot.service;

import com.streetman.chatbot.models.Schedule;
import com.streetman.chatbot.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;
    //POST
    public Schedule createSechedules(Schedule schedules)
    {
        return schedulesRepository.save(schedules);
    }

    //GET
    public List<Schedule> getAllSechedules()
    {
        return schedulesRepository.findAll();
    }
    //GET
    public Schedule getBySchedulesId(Long scheduleid)
    {
        return schedulesRepository.findById(scheduleid) .orElseThrow(() -> new RuntimeException("schedules not found"));
    }

    //PUT

    public Schedule updateSchedules(Long scheduleid, Schedule schedulesDetails)
    {
        Schedule schedules = getBySchedulesId(scheduleid);

        if(schedulesDetails.getPriority() != null) {
            schedules.setPriority(schedulesDetails.getPriority());
        }
        if(schedulesDetails.getStarttime()!=null) {
            schedules.setStarttime(schedulesDetails.getStarttime());
        }

        if(schedulesDetails.getEndtime()!=null) {
            schedules.setEndtime(schedulesDetails.getEndtime());
        }

        if(schedulesDetails.getEnddate()!=null) {
            schedules.setEnddate(schedulesDetails.getEnddate());
        }
        if(schedulesDetails.getStartdate()!=null) {
            schedules.setStartdate(schedulesDetails.getStartdate());
        }
        if(schedulesDetails.getRecurrenceRule()!=null) {
            schedules.setRecurrenceRule(schedulesDetails.getRecurrenceRule());
        }
        if(schedulesDetails.getLightlevel()!=null) {
            schedules.setLightlevel(schedulesDetails.getLightlevel());
        }
        if(schedulesDetails.getLightstate()!=null) {
            schedules.setLightstate(schedulesDetails.getLightstate());
        }

        return schedulesRepository.save(schedules);
    }

    public void deleteByScheduleId(Long scheduleid)
    {
        Schedule schedules = schedulesRepository.findById(scheduleid).orElseThrow(()->new RuntimeException("Schedule Id not found"));
        schedulesRepository.delete(schedules);
    }
}
