package org.shop.api.rest;

import org.junit.Before;
import org.junit.Test;
import org.shop.service.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by vprasanna on 5/20/2016.
 */
public class RESTInventoryAPITest extends AbstractTest {

    @Autowired
    MockHttpSession session;
    @Autowired
    MockHttpServletRequest request;
    @Autowired
    RESTCatalogAPI restCatalogAPI;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/rest/catalog/product?page=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.products[0].id").isNotEmpty());
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testGetCategories() throws Exception {
        mockMvc.perform(get("/rest/catalog/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testFindById() throws Exception {
        mockMvc.perform(get("/rest/catalog/product/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value("4"));
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testFindByCategory() throws Exception {
        mockMvc.perform(get("/rest/catalog/category/SECUREMENT DEVICES"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testSearch() throws Exception {
        mockMvc.perform(get("/rest/catalog/search?keyword=dev"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.products").exists());
    }
}
