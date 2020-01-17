package com.it.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Service request dto.
 */
@NoArgsConstructor
@Data
public class ServiceRequestDto {

    @NotNull(message = "{service.name.notNull}")
    @NotEmpty(message = "{service.name.notEmpty}")
    private String name;
}
