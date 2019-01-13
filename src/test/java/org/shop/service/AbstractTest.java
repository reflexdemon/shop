package org.shop.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by vprasanna on 5/20/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class AbstractTest {

  @Test
  public void loadContext() {
  }

  @Before
  public void beforeEach() {
    try {
      _mongodExe = starter.prepare(new MongodConfigBuilder()
        .version(Version.Main.PRODUCTION)
        .net(new Net("localhost", 12345, Network.localhostIsIPv6()))
        .build());
      _mongod = _mongodExe.start();
      _mongo = new MongoClient("localhost", 12345);
      loadTestData.initData();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @After
  public void tearDown() {
    if( null != _mongod) {
      _mongod.stop();
    }
    if (null != _mongodExe) {
      _mongodExe.stop();
    }
  }

  @Bean
  public Mongo getMongo() {
    return _mongo;
  }
}
