package org.shop.dao;

import org.shop.model.SearchResponse;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 */
public interface Search {
    List<String> getCategories();

    SearchResponse search(String keyword, int limit, int offset);
}
