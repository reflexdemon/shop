package org.shop.api.rest;

import org.junit.Before;
import org.junit.Test;
import org.shop.service.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by vprasanna on 5/20/2016.
 */
public class RESTProductAPITest extends AbstractTest {

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Autowired
    MockHttpSession session;
    @Autowired
    MockHttpServletRequest request;

    private MockMvc mockMvc;


    @Autowired
    RESTProductAPI restProductAPI;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/product?page=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.products[0].id").value("1"));
    }

    @Test
    public void testGetCategories() throws Exception {
        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testFindById() throws Exception {
        mockMvc.perform(get("/product/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value("4"));
    }

    @Test
    public void testFindByCategory() throws Exception {
        mockMvc.perform(get("/category/SECUREMENT DEVICES"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testSearch() throws Exception {
        mockMvc.perform(get("/search?keyword=dev"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.products").exists());
    }
}