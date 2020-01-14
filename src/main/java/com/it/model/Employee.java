package com.it.model;

import javax.persistence.*;

/**
 * The type Employee.
 */
@Entity
@DiscriminatorValue("employee")
public class Employee extends Resource {
}
