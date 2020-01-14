package com.it.model;

import javax.persistence.*;

/**
 * The type Equipment.
 */
@Entity
@DiscriminatorValue("equipment")
public class Equipment extends Resource {


}
