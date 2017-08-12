package org.shop.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.Application;
import org.shop.model.PricingInfo;
import org.shop.model.Product;
import org.shop.model.User;
import org.shop.service.CatalogService;
import org.shop.service.PricingServices;
import org.shop.service.UserServices;
import org.shop.utils.DebugUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 */
//@SpringApplicationConfiguration(value = {Application.class})
public class ProductLoad {
    static final ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    private static boolean verbose;
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        String verboseStr = context.getEnvironment().getProperty("loader.verbose");
        System.out.println("loader.verbose = " + verboseStr);
        verbose = null == verboseStr ? false : Boolean.valueOf(verboseStr);
        ProductLoad loader = new ProductLoad();
        loader.loadProducts(context);
        loader.loadPricing(context);
        loader.loadUsers(context);
        System.exit(0);
    }


    public void loadProducts(ConfigurableApplicationContext context) throws IOException {
        try {
            CatalogService catalogService = context.getBean(CatalogService.class);
            File file = new File("./data/products.json");
            System.out.println("Absolute Path" + file.getAbsolutePath());

            InputStream content = new FileInputStream(file);
            List<Product> products = mapper.readValue(content, new TypeReference<List<Product>>() {
            });
            catalogService.deleteAll();
            products.stream().forEach(product -> {
                if (verbose) System.out.println("Saving: " + DebugUtils.jsonDebug(product));
                catalogService.save(product);
            });
            System.out.println("Product Loading is complete");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void loadPricing(ConfigurableApplicationContext context) throws IOException {
        try {
            PricingServices pricingServices = context.getBean(PricingServices.class);
            File file = new File("./data/pricing.json");
            System.out.println("Absolute Path" + file.getAbsolutePath());

            InputStream content = new FileInputStream(file);
            List<PricingInfo> pricingInfos = mapper.readValue(content, new TypeReference<List<PricingInfo>>() {
            });
            pricingServices.deleteAll();
            pricingInfos.stream().forEach(pricing -> {
                if (verbose) System.out.println("Saving: " + DebugUtils.jsonDebug(pricing));
                pricingServices.save(pricing);
            });
            System.out.println("Pricing Loading is complete");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void loadUsers(ConfigurableApplicationContext context) throws IOException {
        try {
            UserServices userService = context.getBean(UserServices.class);
            File file = new File("./data/users.json");
            System.out.println("Absolute Path" + file.getAbsolutePath());

            InputStream content = new FileInputStream(file);
            List<User> users = mapper.readValue(content, new TypeReference<List<User>>() {
            });
            userService.deleteAll();
            users.stream().forEach(user -> {
                if (verbose) System.out.println("Saving: " + DebugUtils.jsonDebug(user));
                userService.save(user);
            });
            System.out.println("User Loading is complete");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
