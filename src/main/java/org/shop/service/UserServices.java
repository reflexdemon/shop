package org.shop.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shop.dao.UserRepository;
import org.shop.model.User;
import org.shop.utils.DebugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Component
public class UserServices {
    private static final Log logger = LogFactory.getLog(UserServices.class);
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            logger.info("Found user " + user.getUsername());
            return findByUsername(user.getUsername());
        }
        logger.info("No authenticated user found so going to return null");
        return null;
    }
}
