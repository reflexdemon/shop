package org.shop.dao;

import org.shop.model.SearchResponse;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 * The interface Search.
 */
public interface Search {
    /**
     * Gets categories.
     *
     * @return the categories
     */
    List<String> getCategories();

    /**
     * Search search response.
     *
     * @param keyword the keyword
     * @param limit   the limit
     * @param offset  the offset
     * @return the search response
     */
    SearchResponse search(String keyword, int limit, int offset);
}
