package com.it.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * The type Event response dto.
 */
@NoArgsConstructor
@Data
public class EventResponseDto {

    private Long id;

    private ClientResponseDto client;

    private ServiceResponseDto service;

    private Set<ResourceResponseDto> resources;
}
