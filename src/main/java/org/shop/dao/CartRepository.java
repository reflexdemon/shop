package org.shop.dao;

import org.shop.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Repository("cartRepository")
public interface CartRepository extends MongoRepository<Cart, String> {

    Cart findByUsername(String username);

}