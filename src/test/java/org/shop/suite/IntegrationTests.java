package org.shop.suite;

import org.junit.runners.Suite;
import org.shop.Application;
import org.shop.api.rest.CryptoUtilsTest;
import org.shop.api.rest.RESTCartAPITest;
import org.shop.api.rest.RESTInventoryAPITest;
import org.shop.api.rest.RESTUserAPITest;
import org.shop.service.CatalogServiceTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vprasanna on 5/31/2016.
 */
@org.junit.runner.RunWith(Suite.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
@Suite.SuiteClasses({
        RESTUserAPITest.class,
        RESTInventoryAPITest.class,
        CryptoUtilsTest.class,
        CatalogServiceTest.class,
        RESTCartAPITest.class
})
@Profile("localtest")
public class IntegrationTests {
}
