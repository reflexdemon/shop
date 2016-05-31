package org.shop.api.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.shop.model.Cart;
import org.shop.service.CartService;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

}
