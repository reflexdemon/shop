package org.shop.model;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 * The type Search response.
 */
public class SearchResponse {
    private List<Product> products;
    private int offSet;
    private int limit;
    private long maxCount;
    private int page;

    /**
     * Gets products.
     *
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Gets off set.
     *
     * @return the off set
     */
    public int getOffSet() {
        return offSet;
    }

    /**
     * Sets off set.
     *
     * @param offSet the off set
     */
    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets limit.
     *
     * @param limit the limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Gets max count.
     *
     * @return the max count
     */
    public long getMaxCount() {
        return maxCount;
    }

    /**
     * Sets max count.
     *
     * @param maxCount the max count
     */
    public void setMaxCount(long maxCount) {
        this.maxCount = maxCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "products=" + products +
                ", offSet=" + offSet +
                ", limit=" + limit +
                ", maxCount=" + maxCount +
                ", page=" + page +
                '}';
    }
}
