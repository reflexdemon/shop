package org.shop.api.rest;

import org.junit.Before;
import org.junit.Test;
import org.shop.model.CartRequest;
import org.shop.service.AbstractTest;
import org.shop.utils.DebugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by vprasanna on 6/2/2016.
 */
public class RESTCartAPITest extends AbstractTest {

    @Autowired
    MockHttpSession session;
    @Autowired
    MockHttpServletRequest request;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .dispatchOptions(true).build();
    }


    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testGetMyCart() throws Exception {
        mockMvc.perform(get("/rest/cart"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.username").value("root"));
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testAddItemToCart() throws Exception {
        CartRequest request = new CartRequest();
        request.setProductId("15");
        request.setQuantity(2);
        mockMvc.perform(post("/rest/cart", request)
                .content(DebugUtils.jsonDebug(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.username").value("root"));
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testUpdateQuantity() throws Exception {
        testAddItemToCart();
        CartRequest request = new CartRequest();
        request.setProductId("15");
        request.setQuantity(200);
        mockMvc.perform(put("/rest/cart/lineItem", request)
                .content(DebugUtils.jsonDebug(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.username").value("root"));
    }

    @Test
    @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
    public void testDeleteItem() throws Exception {
        testUpdateQuantity();
        String productId = "15";

        mockMvc.perform(delete("/rest/cart/lineItem/" + productId)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.username").value("root"));
    }
}
