package com.it.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("equipment")
public class Equipment extends Resource {
}
