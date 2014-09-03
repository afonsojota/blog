package com.wehavescience.domain;

import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@EqualsAndHashCode(of = {"id"})
public class AbstractEntity {
    @Id
    @Accessors(fluent = true)
    private ObjectId id;
}
