package com.streetman.chatbot.repository;

import com.streetman.chatbot.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedule,Long> {
    Optional<Schedule> findBySchedulename(String schedulename);
}
