package com.wehavescience.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Entity("articles")
@Data
@Accessors(fluent = true)
public class Article extends AbstractEntity {
    private String title;
    @Embedded
    private Content content;
    @Embedded
    private Rate rate;
    @Reference
    private Author author;
    @Embedded
    private List<Tag> tags;
    @Embedded
    private List<Comment> comments;
    private String resource;

    public String preview(){
        return content.content().substring(0, 80);
    }
}
