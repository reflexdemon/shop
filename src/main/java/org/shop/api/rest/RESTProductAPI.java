package org.shop.api.rest;

import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.shop.service.ProductService;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 * The type Rest product api.
 */
@RestController
public class RESTProductAPI {

    /**
     * The Product service.
     */
    @Autowired
    ProductService productService;

    /**
     * Find all response entity.
     *
     * @param page the page
     * @return the response entity
     */
    @RequestMapping("/products")
    @ProfileExecution
    public ResponseEntity<SearchResponse> findAll(@RequestParam(required = false) Integer page) {
        SearchResponse response = productService.search(null, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    @RequestMapping("/categories")
    @ProfileExecution
    public ResponseEntity<List<String>> getCategories() {
        List<String> list = productService.getCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Find by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping("/product/{id}")
    @ProfileExecution
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Product item = productService.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    /**
     * Find by category response entity.
     *
     * @param category the category
     * @return the response entity
     */
    @RequestMapping("/category/{category}")
    @ProfileExecution
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
        List<Product> list = productService.findByCategory(category);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Search response entity.
     *
     * @param keyword the keyword
     * @param page    the page
     * @return the response entity
     */
    @RequestMapping("/search")
    @ProfileExecution
    public ResponseEntity<SearchResponse> search(@RequestParam String keyword, @RequestParam(required = false) Integer page) {
        SearchResponse response = productService.search(keyword, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
