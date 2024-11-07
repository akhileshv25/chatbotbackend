package com.streetman.chatbot.repository;

import com.streetman.chatbot.models.Light;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LightsRepository extends JpaRepository<Light,Long> {

}
