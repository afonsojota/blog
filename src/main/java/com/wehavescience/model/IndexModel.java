package com.wehavescience.model;

import com.wehavescience.domain.Article;
import lombok.Data;

import java.util.List;

/**
 * @author Gabriel Francisco  <gabfssilva@gmail.com>
 */
@Data
public class IndexModel {
    private List<Article> articles;
}
