package com.it.repository;

import com.it.model.Resource;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends GenericRepository<Resource,Long> {

    Resource findByName(String name);

    @Query("SELECT u FROM Employee u")
    List<Resource> findAllEmployee();

    @Query("SELECT u FROM Equipment u")
    List<Resource> findAllEquipment();

}
