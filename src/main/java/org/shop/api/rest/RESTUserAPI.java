package org.shop.api.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.shop.exception.Message;
import org.shop.model.AppLogger;
import org.shop.model.User;
import org.shop.service.LogReaderService;
import org.shop.service.UserServices;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by vprasanna on 5/22/2016.
 */
@RestController
public class RESTUserAPI extends RESTBaseAPI {

  @Autowired
  private UserServices userServices;

  @Autowired
  private LogReaderService logReaderService;

  /**
   * Gets categories.
   *
   * @return the categories
   */
  @RequestMapping(value = "/user", method = GET, produces = APPLICATION_JSON_VALUE)
  @ProfileExecution
  @ApiOperation(value = "getAuthenticatedUser", nickname = "getAuthenticatedUser")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success", response = User.class),
    @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
    @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
  public ResponseEntity<User> getAuthenticatedUser() {
    User user = userServices.getAuthenticatedUser();

    if (null == user) {
      return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @RequestMapping(value = "/user", method = POST, produces = APPLICATION_JSON_VALUE)
  @ProfileExecution
  @ApiOperation(value = "createUser", nickname = "createUser")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success", response = User.class),
    @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
    @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    userServices.save(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  /**
   * Gets categories.
   *
   * @return the categories
   */
  @RequestMapping(value = "/user/logs/recent", method = GET, produces = APPLICATION_JSON_VALUE)
  @ProfileExecution
  @ApiOperation(value = "getRecentUserLogs", nickname = "getRecentUserLogs")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success", response = User.class),
    @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
    @ApiResponse(code = 500, message = "Internal Server Error", response = Message.class)})
  public ResponseEntity<List<AppLogger>> getRecentUserLogs() {
    User user = userServices.getAuthenticatedUser();

    List<AppLogger> logs = null;
    if (null == user) {
      return new ResponseEntity<>(logs, HttpStatus.UNAUTHORIZED);
    }

    logs = logReaderService.getRecentUserLogs(user.getUsername());
    return new ResponseEntity<>(logs, HttpStatus.OK);
  }

}
