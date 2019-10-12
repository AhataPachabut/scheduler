package com.it.dao;

import com.it.model.Service;

public class ServiceDAOImpl extends GenericDAOImpl<Service, Long> implements ServiceDAO {
    private static ServiceDAOImpl instance;

    private ServiceDAOImpl() {
        super(Service.class);
    }

    synchronized public static ServiceDAOImpl getInstance() {
        if (instance == null) {
            instance = new ServiceDAOImpl();
        }
        return instance;
    }
}
