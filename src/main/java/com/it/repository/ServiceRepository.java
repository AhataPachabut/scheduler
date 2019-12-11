package com.it.repository;

import com.it.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findByNameStartsWith(String name);

    Service findByName(String name);
}
