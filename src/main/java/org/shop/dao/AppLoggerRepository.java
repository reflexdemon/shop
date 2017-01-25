package org.shop.dao;

import org.shop.model.AppLogger;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Repository("appLoggerRepository")
public interface AppLoggerRepository extends MongoRepository<AppLogger, String> {

//  List<AppLogger> findByUsername(String username);

  List<AppLogger> findTop30ByUsernameOrderByCreateTimeStampDesc(String username);


}
