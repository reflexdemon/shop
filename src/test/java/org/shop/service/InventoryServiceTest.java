package org.shop.service;

import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vprasanna on 5/15/2016.
 */
public class InventoryServiceTest extends AbstractTest {

    @Autowired
    InventoryService inventoryService;

    @org.junit.Test
    public void testAutoWire() throws Exception {
        assertNotNull("Check if the autowire worked", inventoryService);
    }


    @org.junit.Test
    public void testGetDistinctCategory() throws Exception {
        List<String> list = inventoryService.getCategories();
        assertTrue("Check if the listing worked", list.size() > 0);
    }

    @org.junit.Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testFindByCategory() throws Exception {
        List<Product> list = inventoryService.findByCategory("INCONTINENT PRODUCTS");
        assertTrue("Check if the findByCategory worked", list.size() > 0);
    }

    @org.junit.Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testSearch() throws Exception {
        SearchResponse response = inventoryService.search("Dev", 1);
        assertTrue("Check if the search worked", response.getProducts().size() > 0);
        assertTrue("Check if the search worked", response.getLimit() > 0);
        assertTrue("Check if the search worked", response.getMaxCount() > 0);
    }

    @org.junit.Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testFindById() throws Exception {
        Product item = inventoryService.findById("1");
        assertNotNull("Check if the findById worked", item);
    }


}