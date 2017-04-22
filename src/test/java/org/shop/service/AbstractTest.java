package org.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vprasanna on 5/20/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractTest {


  @Autowired
  private UserServices userServices;

  @Test
  public void loadContext() {
  }

}
