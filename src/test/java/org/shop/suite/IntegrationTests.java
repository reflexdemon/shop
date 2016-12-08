package org.shop.suite;

import org.junit.runners.Suite;
import org.shop.Application;
import org.shop.api.rest.CryptoUtilsTest;
import org.shop.api.rest.RESTCartAPITest;
import org.shop.api.rest.RESTInventoryAPITest;
import org.shop.api.rest.RESTUserAPITest;
import org.shop.service.CatalogServiceTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by vprasanna on 5/31/2016.
 */
@org.junit.runner.RunWith(Suite.class)
@SpringApplicationConfiguration(value = {Application.class})
@WebAppConfiguration
@Suite.SuiteClasses({
        RESTUserAPITest.class,
        RESTInventoryAPITest.class,
        CryptoUtilsTest.class,
        CatalogServiceTest.class,
        RESTCartAPITest.class
})
public class IntegrationTests {
}
