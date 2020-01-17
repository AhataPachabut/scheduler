package com.it.dto.request;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The type User request dto.
 */
@NoArgsConstructor
@Data
public class UserRequestDto {

    @NotNull(message = "{user.name.notNull}")
    @NotEmpty(message = "{user.name.notEmpty}")
    private String name;

    @NotNull(message = "{user.password.notNull}")
    @NotEmpty(message = "{user.password.notEmpty}")
    private String password;

    @NotNull(message = "{user.roles.notNull}")
    private Set<Long> roles;
}
