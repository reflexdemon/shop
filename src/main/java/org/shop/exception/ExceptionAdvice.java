package org.shop.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by vprasanna on 5/21/2016.
 */
@ControllerAdvice
public class ExceptionAdvice {
    private static final Log logger = LogFactory.getLog(ExceptionAdvice.class);

    @ExceptionHandler(Throwable.class)
    public void handleGlobalErrors(Throwable t) {
        logger.error("Error in application:", t);
    }
}
