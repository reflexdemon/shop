package org.shop.api.rest;

import io.swagger.annotations.*;
import org.shop.exception.Message;
import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.shop.service.CatalogService;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by vprasanna on 5/15/2016.
 * The type Rest product api.
 */
@RestController
@RequestMapping(value = "/rest/catalog")
public class RESTCatalogAPI extends RESTBaseAPI {

    /**
     * The Catalog service.
     */
    @Autowired
    CatalogService catalogService;

    /**
     * Find all response entity.
     *
     * @param page the page
     * @return the response entity
     */
    @RequestMapping(value = "/product", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "findAll", nickname = "findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page number", required = false, dataType = "object", paramType = "query", defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = SearchResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
    public ResponseEntity<SearchResponse> findAll(@RequestParam(required = false) Integer page) {
        SearchResponse response = catalogService.search(null, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/product/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "findById", nickname = "findById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Record Identifier", required = false, dataType = "string", paramType = "path", defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Product.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})

    public ResponseEntity<Product> findById(@PathVariable String id) {
        Product item = catalogService.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    @RequestMapping(value = "/category", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "getCategories", nickname = "getCategories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})

    public ResponseEntity<List<String>> getCategories() {
        List<String> list = catalogService.getCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Find by category response entity.
     *
     * @param category the category
     * @return the response entity
     */
    @RequestMapping(value = "/category/{category}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "findByCategory", nickname = "findByCategory")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category", value = "Category name", required = false, dataType = "string", paramType = "path", defaultValue = "INFECTION CONTROL PRODUCTS")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})

    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
        List<Product> list = catalogService.findByCategory(category);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Search response entity.
     *
     * @param keyword the keyword
     * @param page    the page
     * @return the response entity
     */
    @RequestMapping(value = "/search", method = GET, produces = APPLICATION_JSON_VALUE)
    @ProfileExecution
    @ApiOperation(value = "search", nickname = "search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "Search keyword", required = false, dataType = "string", paramType = "query", defaultValue = "device"),
            @ApiImplicitParam(name = "page", value = "Page number", required = false, dataType = "object", paramType = "query", defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = SearchResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})

    public ResponseEntity<SearchResponse> search(@RequestParam String keyword, @RequestParam(required = false) Integer page) {
        SearchResponse response = catalogService.search(keyword, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
