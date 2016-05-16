package org.shop.api.rest;

import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.shop.service.ProductService;
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
 */
@RestController
public class RESTProductAPI {

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> list = productService.getCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping("/product/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Product item = productService.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping("/category/{category}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
        List<Product> list = productService.findByCategory(category);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping("/search")
    public ResponseEntity<SearchResponse> search(@RequestParam String keyword, @RequestParam(required = false) Integer page) {
        SearchResponse response = productService.search(keyword, page);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
