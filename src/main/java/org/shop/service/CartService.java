package org.shop.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shop.dao.CartRepository;
import org.shop.model.Cart;
import org.shop.model.LineItem;
import org.shop.model.Product;
import org.shop.model.User;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Created by vprasanna on 5/30/2016.
 */
@Service
public class CartService {

    private static final Log logger = LogFactory.getLog(CartService.class);
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserServices userServices;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CounterService counter;

    @ProfileExecution
    public Cart getMyCart() {
        User currentUser = verifyAndGetUser();

        Cart cart = cartRepository.findByUsername(currentUser.getUsername());

        if (null == cart) {
            logger.info("Crating new empty cart");
            cart = new Cart();
            cart.setUsername(currentUser.getUsername());
            //Saving to DB to get a new Cart ID
            cart = cartRepository.save(cart);
        }

        return cart;
    }


    public Cart addItemToCart(String productId, int quantity) {

        if (StringUtils.isEmpty(productId) || quantity <= 0) {
            throw new IllegalArgumentException("Product ID cannot be empty or quantity cannot be 0 or less whle adding item to cart.");
        }

        Cart cart = getMyCart();

        List<LineItem> lineItems = cart.getLineItems();
        Optional<LineItem> item = lineItems.stream()
                .filter(lineItem -> productId.equals(lineItem.getProductId()))
                .findFirst();

        LineItem lineItem = null;

        if (item.isPresent()) {
            lineItem = item.get();
            lineItems.remove(item);
        } else {
            lineItem = createLineItem(productId);
        }

        if (null == lineItem) {
            throw new IllegalStateException("Line Item cannot be null or empty");
        }

        lineItem.setQuantity(lineItem.getQuantity() + quantity);
        lineItems.add(lineItem);

        counter.init();
        lineItems.stream().forEach(line ->
                line.setId(String.valueOf(counter.next())));
        counter.reset();

        cart.setLineItems(lineItems);
        cart = cartRepository.save(cart);

        return cart;
    }

    public LineItem createLineItem(String productId) {
        return createLineItem(productId, 0);//quantity will be added by the caller later.
    }

    public LineItem createLineItem(String productId, int quantity) {
        Product product = inventoryService.findById(productId);
        LineItem lineItem = new LineItem();
        lineItem.setQuantity(quantity);
        lineItem.setCurrency(product.getPricingInfo().getCurrency());
        lineItem.setDescription(product.getName());
        lineItem.setPrice(product.getPricingInfo().getBasePrice());
        lineItem.setTax(product.getPricingInfo().getTaxPercentage());
        lineItem.setImageURL(product.getImageURL());
        return lineItem;
    }

    public User verifyAndGetUser() {
        User currentUser = userServices.getAuthenticatedUser();
        if (null == currentUser) {
            throw new IllegalStateException("Cannot View/Modify cart for non logged in user.");
        }
        return currentUser;
    }
}
