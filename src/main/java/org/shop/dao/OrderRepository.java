package org.shop.dao;

import org.shop.model.Order;
import org.shop.model.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Repository("orderRepository")
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByUsername(String username);

    Order findByUsernameAndId(String username, String id);

    List<Order> findByUsernameAndStatus(String username, OrderStatus status);

}