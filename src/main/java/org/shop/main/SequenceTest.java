package org.shop.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.Application;
import org.shop.dao.Sequence;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * Created by vprasanna on 5/15/2016.
 */
//@SpringApplicationConfiguration(value = {Application.class})
public class SequenceTest {
  static final ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
  private static boolean verbose;

  public static void main(String[] args) throws IOException {
    ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    String verboseStr = context.getEnvironment().getProperty("loader.verbose");
    System.out.println("loader.verbose = " + verboseStr);
    verbose = null == verboseStr ? false : Boolean.valueOf(verboseStr);
    SequenceTest loader = new SequenceTest();
    loader.nextSequence(context);
    System.exit(0);
  }


  public void nextSequence(ConfigurableApplicationContext context) {
    try {
      Sequence sequence = context.getBean(Sequence.class);
      System.out.println("Returned Value:" + sequence.getNextSequenceId("orderid"));
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

}
