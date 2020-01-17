package com.it.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Resource request dto.
 */
@NoArgsConstructor
@Data
public class ResourceRequestDto {

    @NotNull(message = "{resource.name.notNull}")
    @NotEmpty(message = "{resource.name.notEmpty}")
    private String name;
}
