package org.shop.service;

import org.shop.dao.ProductRepository;
import org.shop.dao.Search;
import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 */
@Component
public class ProductService {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private Search search;

    @Autowired
    private ProductRepository productDao;

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(String id) {
        return productDao.findById(id);
    }

    public List<Product> findByCategory(String category) {
        return productDao.findByCategory(category);
    }

    public SearchResponse search(String keyword, Integer page) {
        //To make page parameter optional
        if (null == page || 0 == page) {
            page = 1;
        }

        int limit = PAGE_SIZE;
        int offset = (PAGE_SIZE * page) - PAGE_SIZE;
        return search.search(keyword, limit, offset);
    }

    public Product save(final Product product) {
        return productDao.save(product);
    }

    public void deleteAll() {
        productDao.deleteAll();
    }

    public List<String> getCategories() {
        return search.getCategories();
    }
}
