package org.shop.suite;

import org.junit.runners.Suite;
import org.shop.Application;
import org.shop.api.rest.CryptoUtilsTest;
import org.shop.api.rest.RESTProductAPITest;
import org.shop.api.rest.RESTUserAPITest;
import org.shop.service.InventoryServiceTest;
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
        RESTProductAPITest.class,
        CryptoUtilsTest.class,
        InventoryServiceTest.class
})
public class IntegrationTests {
}
