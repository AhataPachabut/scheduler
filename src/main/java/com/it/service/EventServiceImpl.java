package com.it.service;

import com.it.model.Event;
import com.it.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl extends GenericServiceImpl<Event, Long> implements EventService {

    @Autowired
    private EventRepository repository;

}
