package com.it.model;

import javax.persistence.*;

@Entity
@Table(name = "SESSIONS")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

}
