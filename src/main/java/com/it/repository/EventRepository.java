package com.it.repository;

import com.it.model.Event;

public interface EventRepository extends GenericRepository<Event, Long> {

//    @Modifying
//    @Query("update Event as e set e.client_id = ?2 where e.employeeId = ?1")
//    int setClient(Long id, Long client_id);
//
//    @Modifying
//    @Query("update Event as e set e.service_id = ?2 where e.employeeId = ?1")
//    int setService(Long id, Long service_id);
//
//    @Modifying
//    @Query("insert into Event_Resource VALUES(?1, ?2)")
//    int setResourse(Long id, Long resource_id);

}
