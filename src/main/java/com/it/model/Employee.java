package com.it.model;

import javax.persistence.*;

@Entity
@Table
@PrimaryKeyJoinColumn(name="resource_id")
public class Employee extends Resource {
}
