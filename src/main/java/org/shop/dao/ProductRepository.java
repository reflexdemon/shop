package org.shop.dao;

import org.shop.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 */
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAll();

    List<Product> findByCategory(String category);

    Product findById(String id);
}
