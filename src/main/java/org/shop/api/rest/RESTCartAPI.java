package org.shop.api.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.shop.model.Cart;
import org.shop.model.CartRequest;
import org.shop.service.CartService;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by vprasanna on 5/30/2016.
 */
@RestController
@RequestMapping("/rest")
public class RESTCartAPI {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "getMyCart", nickname = "getMyCart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Cart.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
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
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartRequest request) {
        Cart cart = cartService.addItemToCart(request.getProductId(), request.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "/cart/lineItem", method = PUT, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "updateQuantity", nickname = "updateQuantity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Cart.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Cart> updateQuantity(@RequestBody CartRequest request) {
        Cart cart = cartService.updateQuantity(request.getProductId(), request.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
