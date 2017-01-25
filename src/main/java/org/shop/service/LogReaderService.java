package org.shop.service;

import org.shop.dao.AppLoggerRepository;
import org.shop.model.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vprasanna on 1/20/17.
 */
@Service
public class LogReaderService {

  @Autowired
  private AppLoggerRepository appLoggerRepository;

  public List<AppLogger> getRecentUserLogs(String username) {
    return appLoggerRepository.findTop30ByUsernameOrderByCreateTimeStampDesc(username);
  }
}
