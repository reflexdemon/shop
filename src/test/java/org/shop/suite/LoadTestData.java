package org.shop.suite;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.dao.SequenceIdRepository;
import org.shop.model.PricingInfo;
import org.shop.model.Product;
import org.shop.model.SequenceId;
import org.shop.model.User;
import org.shop.service.CatalogService;
import org.shop.service.OrderService;
import org.shop.service.PricingServices;
import org.shop.service.UserServices;
import org.shop.utils.DebugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class LoadTestData {

  @Autowired
  CatalogService catalogService;

  @Autowired
  PricingServices pricingServices;

  @Autowired
  UserServices userService;

  @Autowired
  ObjectMapper mapper;

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  SequenceIdRepository sequenceIdRepository;

  private boolean verbose = false;

  public void loadProducts() {
    File file = new File("./data/products.json");
    System.out.println("Absolute Path" + file.getAbsolutePath());

    InputStream content = null;
    try {
      content = new FileInputStream(file);

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
    }
  }

  public void loadPricing() {
    try {
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
    }
  }
  public void loadUsers() {
    try {
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
    }
  }

  public void createSequence() {
    SequenceId sequence = new SequenceId();
    sequence.setSeq(0);
    sequence.setId(OrderService.SEQUENCE_ID);
    sequenceIdRepository.save(sequence);
  }
  public void initData() {
      loadProducts();
      loadPricing();
      loadUsers();
    createSequence();
  }
}
