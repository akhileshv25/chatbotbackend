package com.streetman.chatbot.repository;

import com.streetman.chatbot.models.Light;
import com.streetman.chatbot.models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LightsRepository extends JpaRepository<Light,Long> {
    Optional<Light> findByLightidAndZone(Long lightid, Zone zone);
}
