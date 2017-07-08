package org.shop.utils;

import org.junit.Test;
import org.shop.model.CartRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by venkateswara on 7/1/17.
 */
public class DebugUtilsTest {
  @Test
  public void debugString() throws Exception {
    String x = "String";
    assertNotNull("check if it returns value", DebugUtils.debugString(x));
  }

  @Test
  public void debugString1() throws Exception {
    CartRequest request = null;
    assertNotNull("check if it returns value", DebugUtils.debugString(request));
  }

  @Test
  public void debugString2() throws Exception {
    List<CartRequest> cartRequests = new ArrayList<>();
    CartRequest request1 = new CartRequest();
    request1.setProductId("10");
    CartRequest request2 = new CartRequest();
    request2.setProductId("20");

    cartRequests.add(request1);
    cartRequests.add(request2);
    assertNotNull("check if it returns value", DebugUtils.debugString(cartRequests));
  }

  @Test
  public void debugString3() throws Exception {
    List<CartRequest> cartRequests = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      CartRequest request1 = new CartRequest();
      request1.setProductId("" + i);
      cartRequests.add(request1);
    }
    assertNotNull("check if it returns value", DebugUtils.debugString(cartRequests));
  }


}
