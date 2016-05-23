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

import java.util.Base64;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Component
public class UserServices {
    private static final Log logger = LogFactory.getLog(UserServices.class);
    @Autowired
    private UserRepository userRepository;
    public User findByUsername(String username) {
        return findByUsername(username, false);
    }
    public User findByUsername(String username, boolean decode) {
        User user = userRepository.findByUsername(username);
        if (decode) {
            user.setPassword(new String(Base64.getDecoder().decode(user.getPassword().getBytes())));
        }
        return user;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void save(User user) {
        user.setPassword(new String(Base64.getEncoder().encode(user.getPassword().getBytes())));
        userRepository.save(user);
    }

    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
            logger.info("Found user " + user.getUsername());
            User u = findByUsername(user.getUsername());
            return u;
        }
        logger.info("No authenticated user found so going to return null");
        return null;
    }
}
