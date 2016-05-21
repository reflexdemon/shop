package org.shop.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.Application;
import org.shop.model.Product;
import org.shop.service.ProductService;
import org.shop.utils.DebugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ProductLoad loader = new ProductLoad();
        loader.loadProducts(context);
    }


    public void loadProducts(ConfigurableApplicationContext context) throws IOException {
        try {
            ProductService productService = context.getBean(ProductService.class);
            File file = new File("./data/products.json");
            System.out.println("Absolute Path" + file.getAbsolutePath());

            InputStream content = new FileInputStream(file);
            List<Product> products = mapper.readValue(content, new TypeReference<List<Product>>() {
            });
            productService.deleteAll();
            products.stream().forEach(product -> {
                System.out.println("Saving: " + DebugUtils.jsonDebug(product));
                productService.save(product);
            });
            System.out.println("Product Loading is complete");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
