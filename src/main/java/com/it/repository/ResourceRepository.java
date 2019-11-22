package com.it.repository;

import com.it.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

    @Query("SELECT u FROM Resource u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',:name))")
    List<Resource> findByNameStartsWithParam1(@Param("name") String name);

    @Query("SELECT u FROM Resource u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',?1))")
    List<Resource> findByNameStartsWithParam2(String name);

    List<Resource> findByNameStartsWith(String name);

    Resource findByName(String name);
}
