package com.it.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Client request dto.
 */
@NoArgsConstructor
@Data
public class ClientRequestDto {

    @NotNull(message = "{client.name.notNull}")
    @NotEmpty(message = "{client.name.notEmpty}")
    private String name;

    @NotNull(message = "{client.user.notNull}")
    private Long user;
}
