package com.wehavescience.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class Comment {
    private String author;
    private Date date;
    private String email;
    private String content;
    private boolean approved;
}
