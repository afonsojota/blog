package com.wehavescience.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.mongodb.morphia.annotations.Entity;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Entity("authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
@Accessors(fluent = true)
public class Author extends AbstractEntity {
    private String authorName;
    private String email;
}
