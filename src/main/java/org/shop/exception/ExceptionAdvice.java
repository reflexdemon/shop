package org.shop.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vprasanna on 5/21/2016.
 */
@ControllerAdvice
public class ExceptionAdvice {
    private static final Log logger = LogFactory.getLog(ExceptionAdvice.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Message> handleAPIException(HttpServletRequest httpServletRequest, Throwable ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus responseCode = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof IllegalArgumentException) {
            responseCode = HttpStatus.BAD_REQUEST;
        }

        if (ex instanceof IllegalStateException) {
            responseCode = HttpStatus.NOT_ACCEPTABLE;
        }
        Message message = new Message(responseCode.toString(), ex.getMessage());
        logger.info("Error Message:" + message);
        return new ResponseEntity<>(message, headers, responseCode);
    }
}
