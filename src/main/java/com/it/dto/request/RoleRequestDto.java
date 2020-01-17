package com.it.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Role request dto.
 */
@NoArgsConstructor
@Data
public class RoleRequestDto {

    @NotNull(message = "{role.name.notNull}")
    @NotEmpty(message = "{role.name.notEmpty}")
    private String name;
}
