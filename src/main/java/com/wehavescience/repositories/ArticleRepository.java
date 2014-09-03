package com.wehavescience.repositories;

import com.wehavescience.domain.Article;
import org.mongodb.morphia.Datastore;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Alternative
public class ArticleRepository extends Repository<Article> {
    @Inject
    public ArticleRepository(Datastore datastore) {
        super(datastore, Article.class);
    }
}
