package org.shop.service;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.suite.LoadTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by vprasanna on 5/20/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("test")
public class AbstractTest {

  /**
   * please store Starter or RuntimeConfig in a static final field
   * if you want to use artifact store caching (or else disable caching)
   */
  private static final MongodStarter starter = MongodStarter.getDefaultInstance();

  private MongodExecutable _mongodExe;
  private MongodProcess _mongod;

  private MongoClient _mongo;
  @Autowired
  private UserServices userServices;

  @Autowired
  private LoadTestData loadTestData;

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
