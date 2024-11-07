package com.streetman.chatbot.repository;

import com.streetman.chatbot.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedule,Long> {
}
