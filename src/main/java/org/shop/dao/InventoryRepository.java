package org.shop.dao;

import org.shop.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 * The interface Inventory repository.
 */
@Repository("inventoryRepository")
public interface InventoryRepository extends MongoRepository<Product, String> {
    List<Product> findAll();

    /**
     * Find by category list.
     *
     * @param category the category
     * @return the list
     */
    List<Product> findByCategory(String category);

    /**
     * Find by id product.
     *
     * @param id the id
     * @return the product
     */
    Product findById(String id);
}
