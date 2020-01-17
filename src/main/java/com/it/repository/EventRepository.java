package com.it.repository;

import com.it.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends GenericRepository<Event, Long> {

}
