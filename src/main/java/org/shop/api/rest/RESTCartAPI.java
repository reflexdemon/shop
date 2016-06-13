package org.shop.api.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.shop.exception.Message;
import org.shop.model.Cart;
import org.shop.model.CartRequest;
import org.shop.service.CartService;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by vprasanna on 5/30/2016.
 */
@RestController
public class RESTCartAPI extends RESTBaseAPI {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "getMyCart", nickname = "getMyCart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Cart.class),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Cart> getMyCart() {
        Cart cart = cartService.getMyCart();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @RequestMapping(value = "/cart", method = POST, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "addItemToCart", nickname = "addItemToCart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Cart.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartRequest payload) {
        Cart cart = cartService.addItemToCart(payload.getProductId(), payload.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/lineItem", method = PUT, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "updateQuantity", nickname = "updateQuantity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Cart.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<Cart> updateQuantity(@RequestBody CartRequest payload) {
        Cart cart = cartService.updateQuantity(payload.getProductId(), payload.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/lineItem/{productId}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "deleteItem", nickname = "deleteItem")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Cart.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<Cart> deleteItem(@PathVariable String productId) {
        Cart cart = cartService.deleteItem(productId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
