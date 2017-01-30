package org.shop.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shop.dao.UserRepository;
import org.shop.model.User;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.shop.utils.CryptoUtils.sha256;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Service
public class UserServices {
    private static final Log logger = LogFactory.getLog(UserServices.class);
    @Autowired
    private UserRepository userRepository;

    @ProfileExecution
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @ProfileExecution
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @ProfileExecution
    public void save(User user) {
        verifyUser(user);
        user.setPassword(sha256(user.getPassword()));
        userRepository.save(user);
    }

  private void verifyUser(User user) {

    if ( null == user) {
      throw new RuntimeException("Empty User please verify user object");
    }

    if ( user.getId() == null) { //This is for new user!

      User anotherUser = null;

      anotherUser  = userRepository.findByUsername(user.getUsername());

      if (null != anotherUser) {
        throw new RuntimeException("User already exist. Please choose another username");
      }

      anotherUser  = userRepository.findByEmail(user.getEmail());
      if (null != anotherUser) {
        throw new RuntimeException("User already exist. Please choose another email address");
      }

    }

  }

  //    @ProfileExecution
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (null != auth && auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof java.lang.String) {
//              anonymousUser
              user = new User();
              user.setUsername(principal.toString());
              user.setFirstName("anonymous");
              user.setLastName("anonymous");
              user.setId(UUID.randomUUID().toString());
            } else {
              user= (User) principal;
            }

            logger.trace("Found user " + user.getUsername());
            return user;
        }
        return user;
    }

    @ProfileExecution
    public User getAuthenticatedUser(String defaultUser) {
        User preAuthenticatedUser = getAuthenticatedUser();

        if (null == preAuthenticatedUser) {
            logger.trace(String.format("No authenticated user found so going to return '%s'", defaultUser));
            return findByUsername(defaultUser);
        }
        return preAuthenticatedUser;
    }
}
