package com.it.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("employee")
public class Employee extends Resource {
}
