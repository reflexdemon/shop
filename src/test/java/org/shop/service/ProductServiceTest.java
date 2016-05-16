package org.shop.service;

import org.shop.Application;
import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vprasanna on 5/15/2016.
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @org.junit.Test
    public void testAutoWire() throws Exception {
        assertNotNull("Check if the autowire worked", productService);
    }


    @org.junit.Test
    public void testGetDistinctCategory() throws Exception {
        List<String> list = productService.getCategories();
        assertTrue("Check if the listing worked", list.size() > 0);
    }

    @org.junit.Test
    public void testFindByCategory() throws Exception {
        List<Product> list = productService.findByCategory("INCONTINENT PRODUCTS");
        assertTrue("Check if the findByCategory worked", list.size() > 0);
    }

    @org.junit.Test
    public void testSearch() throws Exception {
        SearchResponse response = productService.search("PRODUCT", 1);
        assertTrue("Check if the search worked", response.getProducts().size() > 0);
        assertTrue("Check if the search worked", response.getLimit() > 0);
        assertTrue("Check if the search worked", response.getMaxCount() > 0);
    }

    @org.junit.Test
    public void testFindById() throws Exception {
        Product item = productService.findById("1");
        assertNotNull("Check if the findById worked", item);
    }


}