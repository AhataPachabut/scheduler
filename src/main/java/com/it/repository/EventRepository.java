package com.it.repository;

import com.it.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT u FROM Event u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',:name))")
    List<Event> findByNameStartsWithParam1(@Param("name") String name);

    @Query("SELECT u FROM Event u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',?1))")
    List<Event> findByNameStartsWithParam2(String name);

    List<Event> findByNameStartsWith(String name);

    Event findByName(String name);
}
