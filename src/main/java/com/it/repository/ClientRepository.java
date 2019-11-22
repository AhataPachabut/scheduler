package com.it.repository;

import com.it.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT u FROM Client u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',:name))")
    List<Client> findByNameStartsWithParam1(@Param("name") String name);

    @Query("SELECT u FROM Client u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%',?1))")
    List<Client> findByNameStartsWithParam2( String name);

    List<Client> findByNameStartsWith(String name);

    Client findByName(String name);
}
