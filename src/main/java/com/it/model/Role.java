package com.it.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * The type Role.
 */
@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@Data
public class Role extends BaseClass implements GrantedAuthority {

    @Column(unique = true, nullable = false)
    @NotNull(message = "{role.name.notNull}")
    @NotEmpty(message = "{role.name.notEmpty}")
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_" + name.toUpperCase();
    }
}
