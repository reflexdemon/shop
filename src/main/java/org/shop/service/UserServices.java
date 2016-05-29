package org.shop.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shop.dao.UserRepository;
import org.shop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import static org.shop.utils.CryptoUtils.*;
/**
 * Created by vprasanna on 5/22/2016.
 */
@Component
public class UserServices {
    private static final Log logger = LogFactory.getLog(UserServices.class);
    @Autowired
    private UserRepository userRepository;
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void save(User user) {
        user.setPassword(sha256(user.getPassword()));
        userRepository.save(user);
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null != auth && auth.isAuthenticated()) {
            User user = (User) auth.getPrincipal();
            logger.trace("Found user " + user.getUsername());
            return user;
        }
        logger.info("No authenticated user found so going to return null");
        return null;
    }
}
