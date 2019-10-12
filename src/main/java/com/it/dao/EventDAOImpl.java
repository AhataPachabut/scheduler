package com.it.dao;

import com.it.model.Event;

public class EventDAOImpl extends GenericDAOImpl<Event, Long> implements EventDAO {
    private static EventDAOImpl instance;

    private EventDAOImpl() {
        super(Event.class);
    }

    synchronized public static EventDAOImpl getInstance() {
        if (instance == null) {
            instance = new EventDAOImpl();
        }
        return instance;
    }
}
