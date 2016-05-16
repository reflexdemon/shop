package org.shop.model;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 */
public class SearchResponse {
    private List<Product> products;
    private int offSet;
    private int limit;
    private long maxCount;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(long maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "products=" + products +
                ", offSet=" + offSet +
                ", limit=" + limit +
                ", maxCount=" + maxCount +
                '}';
    }
}
