package org.shop.api.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.shop.exception.Message;
import org.shop.model.Order;
import org.shop.model.OrderStatus;
import org.shop.service.OrderService;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by vprasanna on 5/22/2016.
 * The type Rest order api.
 */
@RestController
@RequestMapping("/rest")
public class RESTOrderAPI {

    @Autowired
    private OrderService orderService;


    /**
     * Find by username response entity.
     *
     * @param username the username
     * @return the response entity
     */
    @RequestMapping(value = "/order/{username}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "findByUsername", nickname = "findByUsername(username)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<List<Order>> findByUsername(@PathVariable String username) {
        List<Order> orders = orderService.findByUsername(username);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Find by status response entity.
     *
     * @param status the status
     * @return the response entity
     */
    @RequestMapping(value = "/order/status/{status}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "findByStatus", nickname = "findByStatus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<List<Order>> findByStatus(@PathVariable OrderStatus status) {
        List<Order> orders = orderService.findByStatus(status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Find by status response entity.
     *
     * @param status   the status
     * @param username the username
     * @return the response entity
     */
    @RequestMapping(value = "/order/status/{status}/{username}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "findByStatus", nickname = "findByStatus(status,username)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<List<Order>> findByStatus(@PathVariable OrderStatus status, @PathVariable String username) {
        List<Order> orders = orderService.findByUsernameAndStatus(username, status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

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


    /**
     * Update order status response entity.
     *
     * @param id     the id
     * @param status the status
     * @return the response entity
     */
    @RequestMapping(value = "/order/{id}/{status}", method = PUT, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "updateOrderStatus", nickname = "updateOrderStatus")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Order.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<Order> updateOrderStatus(@PathVariable String id, @PathVariable OrderStatus status) {
        Order order = orderService.updateOrderStatus(id, status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     * Place order response entity.
     *
     * @return the response entity
     */
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
