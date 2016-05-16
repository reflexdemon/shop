package org.shop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.shop.Application;
import org.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by vprasanna on 5/15/2016.
 */
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(value = {Application.class})
public class ProductLoad {
    static final ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
    @Autowired
    ProductService productService;

    @Test
    public void loadProducts() throws IOException {
        File file = new File("./data/products.json");
        System.out.println("Absolute Path" + file.getAbsolutePath());

        InputStream content = new FileInputStream(file);
        List<Product> products = mapper.readValue(content, new TypeReference<List<Product>>() {
        });
        productService.deleteAll();
//        products.stream()
//                .forEach(product -> productService.save(product));
        for (Product product :  products) {
            productService.save(product);
        }
    }
}
