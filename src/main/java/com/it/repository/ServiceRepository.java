package com.it.repository;

import com.it.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("SELECT u FROM Service u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',:name))")
    List<Service> findByNameStartsWithParam1(@Param("name") String name);

    @Query("SELECT u FROM Service u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',?1))")
    List<Service> findByNameStartsWithParam2(String name);

    List<Service> findByNameStartsWith(String name);

    Service findByName(String name);
}
