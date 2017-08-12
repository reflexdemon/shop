package org.shop.service;

import org.shop.dao.OrderRepository;
import org.shop.dao.Sequence;
import org.shop.model.Cart;
import org.shop.model.Order;
import org.shop.model.OrderStatus;
import org.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vprasanna on 6/12/2016.
 */
@Service
public class OrderService {

    public static final String SEQUENCE_ID = "orderid";
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

    public Order findById(String id) {
        User user = userServices.getAuthenticatedUser();
        return orderRepository.findByUsernameAndId(user.getUsername(), id);
    }

    public Order updateOrderStatus(String id, OrderStatus status) {
        User user = userServices.getAuthenticatedUser();
        Order order = orderRepository.findByUsernameAndId(user.getUsername(), id);
        if (null == order) {
            throw new RuntimeException(String.format("Order for \'%s\' cannot be found with order id as \'%s\'", user.getUsername(), id));
        }
        order.setStatus(status);
        orderRepository.save(order);
        return order;
    }


    public List<Order> findByUsernameAndStatus(String username, OrderStatus status) {
        return orderRepository.findByUsernameAndStatus(username, status);
    }

    public List<Order> findByStatus(OrderStatus status) {
        User user = userServices.getAuthenticatedUser();
        return findByUsernameAndStatus(user.getUsername(), status);
    }


    @Transactional
    public Order placeOrder() {
        User user = userServices.getAuthenticatedUser();
        Cart cart = cartService.getMyCart();
        if (cart.getLineItems().isEmpty()) {
            throw new IllegalStateException("Cannot place order with empty cart");
        }
        Order order = new Order();

        order.setLineItems(cart.getLineItems());
        order.setSummary(cart.getSummary());
        order.setStatus(OrderStatus.NEW);
        order.setUsername(user.getUsername());
        order.setId(Long.toString(sequence.getNextSequenceId(SEQUENCE_ID)));
        orderRepository.save(order);
        cartService.getBlankCart();
        return order;

    }

}
