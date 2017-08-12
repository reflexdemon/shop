package org.shop.service;

import org.shop.model.PricingTag;
import org.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vprasanna on 5/28/2016.
 */
@Transactional
@Service
public class ProfileService implements UserDetailsService {

    @Autowired
    private UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServices.findByUsername(username);

        if (null == user) {
          //mainly used for junits
            if (null != username && username.equalsIgnoreCase("root")) {
              user = new User();
              user.setFirstName("root");
              user.setLastName("system");
              user.setUsername(username);
              user.setPricingTag(PricingTag.GOLD_TAG);
              return user;
            }
            throw new UsernameNotFoundException("could not find the user '"
                    + username + "'");
        }

        return user;
    }
}
