package org.shop.service;

import org.shop.dao.OrderRepository;
import org.shop.dao.Sequence;
import org.shop.model.Cart;
import org.shop.model.Order;
import org.shop.model.OrderStatus;
import org.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by vprasanna on 6/12/2016.
 */
@Service
public class OrderService {

    private static final String SEQUENCE_ID = "orderid";
    @Autowired
    CartService cartService;
    @Autowired
    private Sequence sequence;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserServices userServices;

    public List<Order> findByUsername() {
        User user = userServices.getAuthenticatedUser();
        return findByUsername(user.getUsername());
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public List<Order> findByStatus(String username, OrderStatus status) {
        User user = userServices.getAuthenticatedUser();
        return orderRepository.findByStatus(user.getUsername(), status);
    }

    public Order placeOrder() {
        User user = userServices.getAuthenticatedUser();
        Cart cart = cartService.getMyCart();
        if (cart.getLineItems().isEmpty()) {
            throw new IllegalStateException("Cannot place order with empty cart");
        }
        Order order = new Order();

        order.setLineItems(cart.getLineItems());
        order.setSummary(cart.getSummary());
        order.setCreated(new Date());
        order.setUpdated(new Date());
        order.setStatus(OrderStatus.NEW);
        order.setUsername(user.getUsername());
        order.setId(Long.toString(sequence.getNextSequenceId(SEQUENCE_ID)));
        orderRepository.save(order);
        cartService.getBlankCart();
        return order;

    }

}
