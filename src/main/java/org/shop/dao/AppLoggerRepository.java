package org.shop.dao;

import org.shop.model.AppLogger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Repository("appLoggerRepository")
public interface AppLoggerRepository extends MongoRepository<AppLogger, String> {

  AppLogger findByUsername(String username);

}
