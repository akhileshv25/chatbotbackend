package com.streetman.chatbot.Zone;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone,Long> {

        Optional<Zone> findByName(String name);

    @Query("SELECT z.name FROM Zone z")
    List<String> findAllZoneNames();
}
