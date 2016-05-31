package org.shop.service;

import org.junit.Test;
import org.shop.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vprasanna on 5/20/2016.
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class, AbstractTest.class})
@WebAppConfiguration
public class AbstractTest {


    @Autowired
    private UserServices userServices;

    @Test
    public void loadContext() {
    }

}
