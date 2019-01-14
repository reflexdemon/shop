package org.shop.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by vprasanna on 5/20/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("localtest")
public class AbstractTest {

  @Test
  public void loadContext() {
  }
}
