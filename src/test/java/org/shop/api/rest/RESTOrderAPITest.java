package org.shop.api.rest;

import org.junit.Before;
import org.junit.Test;
import org.shop.model.Order;
import org.shop.service.AbstractTest;
import org.shop.service.CartService;
import org.shop.service.CatalogService;
import org.shop.service.OrderService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vprasanna on 6/2/2016.
 */
public class RESTOrderAPITest extends AbstractTest {

  @Autowired
  MockHttpSession session;
  @Autowired
  MockHttpServletRequest request;
  @Autowired
  CartService cartService;

  @Autowired
  CatalogService catalogService;

  @Autowired
  OrderService orderService;
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
  public void testFindByUsername() throws Exception {
    String username = "root";
    mockMvc.perform(get("/rest/order/" + username))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//                .andExpect(jsonPath("$[0].username").value("root"));
  }

  @Test
  @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
  public void testFindByCurrentUsername() throws Exception {
    mockMvc.perform(get("/rest/order"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//                .andExpect(jsonPath("$[0].username").value("root"));
  }

  @Test
  @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
  public void testFindByStatusAndUserName() throws Exception {
    String status = "NEW";
    String username = "root";
    mockMvc.perform(get("/rest/order/status/" + status + "/" + username)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$[0].username").value("root"))
    ;
  }

  @Test
  @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
  public void testFindByStatus() throws Exception {
    String status = "OPEN";
    mockMvc.perform(get("/rest/order/status/" + status))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//                .andExpect(jsonPath("$[0].username").value("root"));
  }

  @Test
  @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
  public void testUpdateOrderStatus() throws Exception {
    String productId = catalogService.search("", 1).getProducts().get(0).getId();
    cartService.addItemToCart(productId, 20);
    Order order = orderService.placeOrder();
    String id = order.getId();
    String status = order.getStatus().toString();
    mockMvc.perform(put("/rest/order/" + id + "/" + status))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//                .andExpect(jsonPath("$[0].username").value("root"));
  }

  @Test
  @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
  public void testUpdateOrderStatus500() throws Exception {
    String id = "1";
    String status = "O";
    mockMvc.perform(put("/rest/order/" + id + "/" + status))
      .andExpect(status().is(500))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
//                .andExpect(jsonPath("$[0].username").value("root"));
  }

  @Test
  @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
  public void testPlaceOrderWithEmptyCart() throws Exception {
    mockMvc.perform(post("/rest/order"))
      .andExpect(status().is(406))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
//                .andExpect(jsonPath("$[0].username").value("root"));
  }

  @Test
  @WithUserDetails(value = "root", userDetailsServiceBeanName = "profileService")
  public void testPlaceOrderWithCart() throws Exception {
    cartService.addItemToCart("15", 4);
    mockMvc.perform(post("/rest/order"))
      .andExpect(status().is(200))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
//                .andExpect(jsonPath("$[0].username").value("root"));
  }
}
