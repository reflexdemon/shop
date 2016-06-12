package org.shop.api.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.shop.exception.Message;
import org.shop.model.Order;
import org.shop.service.OrderService;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by vprasanna on 5/22/2016.
 */
@RestController
@RequestMapping("/rest")
public class RESTOrderAPI {

    @Autowired
    private OrderService orderService;


    /**
     * Find by username response entity.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/order", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "findByUsername", nickname = "findByUsername")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<List<Order>> findByUsername() {
        List<Order> orders = orderService.findByUsername();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/order", method = POST, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "placeOrder", nickname = "placeOrder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Order.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<Order> placeOrder() {
        Order order = orderService.placeOrder();
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
