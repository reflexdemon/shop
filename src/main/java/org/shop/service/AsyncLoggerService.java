package org.shop.service;

import org.shop.model.AppLogLevel;
import org.shop.model.AppLogger;
import org.shop.service.async.AsyncLoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by vprasanna on 5/28/2016.
 */
@Service
public class AsyncLoggerService {

  @Autowired
  private JmsTemplate jmsTemplate;


  public void log(AppLogger appLogger) {
    if (null != appLogger) {

      if (StringUtils.isEmpty(appLogger.getMessage())) {
        throw new IllegalArgumentException("Argument message is empty!");
      }

      if (StringUtils.isEmpty(appLogger.getAction())) {
        appLogger.setAction("DEFAULT");
      }

      //This sends Async message
      jmsTemplate.convertAndSend(AsyncLoggingService.LOG_DESTINATION, appLogger);
    }
  }

  public void log(AppLogLevel logLevel, String message, String action, String username) {
    AppLogger appLogger = new AppLogger();

    if (null != logLevel) {
      appLogger.setLevel(logLevel);
    }

    appLogger.setMessage(message);
    appLogger.setAction(action);
    appLogger.setUsername(username);

    //Now make the Async call.
    log(appLogger);
  }


  public void log(AppLogLevel logLevel, String message, String action) {
    log(logLevel, message, action, null);
  }

  public void log(AppLogLevel logLevel, String message) {
    log(logLevel, message, null);
  }

  public void log(String message) {
    log(null, message);
  }

}
