package com.it.dto;

import java.util.Set;

public class EventResponseDto {

    private Long id;

    private ClientResponseDto client;

    private ServiceResponseDto service;

    private Set<Long> resourcesId;

}
