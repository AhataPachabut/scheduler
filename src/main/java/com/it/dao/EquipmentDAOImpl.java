package com.it.dao;

import com.it.model.Equipment;

public class EquipmentDAOImpl extends GenericDAOImpl<Equipment, Long> implements EquipmentDAO {
    private static EquipmentDAOImpl instance;

    private EquipmentDAOImpl() {
        super(Equipment.class);
    }

    synchronized public static EquipmentDAOImpl getInstance() {
        if (instance == null) {
            instance = new EquipmentDAOImpl();
        }
        return instance;
    }
}
