package org.shop.service.async;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shop.dao.AppLoggerRepository;
import org.shop.model.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by vprasanna on 12/29/16.
 */
@Component
public class AsyncLoggingService {

  private static final Log logger = LogFactory.getLog(AsyncLoggingService.class);

  public static final String LOG_DESTINATION = "appLogger";
  @Autowired
  private AppLoggerRepository appLoggerRepository;

  @JmsListener(destination = LOG_DESTINATION, containerFactory = "myFactory")
  public void receiveMessage(AppLogger appLogger) {
//    logger.info("Received <" + appLogger + ">");
    appLoggerRepository.save(appLogger);
  }
}
