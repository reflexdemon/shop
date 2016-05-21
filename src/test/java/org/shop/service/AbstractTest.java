package org.shop.service;

import org.junit.Test;
import org.shop.Application;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vprasanna on 5/20/2016.
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
@WebAppConfiguration
public class AbstractTest {


    @Test
    public void loadContext() {
    }
}
