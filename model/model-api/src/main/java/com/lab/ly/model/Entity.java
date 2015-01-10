package com.lab.ly.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by haswell on 1/10/15.
 */

@MappedSuperclass
public interface Entity<
    ID extends Serializable,
    Key extends Serializable
> {

    ID getId();

    Key getKey();

    boolean isNew();

    boolean equals(Object o);

}
