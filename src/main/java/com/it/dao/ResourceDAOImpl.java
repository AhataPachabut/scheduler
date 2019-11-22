package com.it.dao;

import com.it.model.Resource;

public class ResourceDAOImpl extends GenericDAOImpl<Resource, Long> implements ResourceDAO {
    private static ResourceDAOImpl instance;

    private ResourceDAOImpl() {
        super(Resource.class);
    }

    synchronized public static ResourceDAOImpl getInstance() {
        if (instance == null) {
            instance = new ResourceDAOImpl();
        }
        return instance;
    }
}
