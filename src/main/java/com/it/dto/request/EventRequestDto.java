package com.it.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The type Event request dto.
 */
@NoArgsConstructor
@Data
public class EventRequestDto {

    @NotNull(message = "{event.client.notNull}")
    private Long client;

    @NotNull(message = "{event.service.notNull}")
    private Long service;

    @NotNull(message = "{event.resources.notNull}")
    private Set<Long> resources;
}
