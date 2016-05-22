package org.shop;

import org.shop.dao.UserRepository;
import org.shop.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                org.shop.model.User user = userRepository.findByUsername(username);
                if (user != null) {
                    return new User(user.getUsername(), user.getPassword(), true, true, true, true,
                            AuthorityUtils.commaSeparatedStringToAuthorityList(rolesToCommaSeparatedString(user.getRoles())));
                } else {
                    throw new UsernameNotFoundException("could not find the user '"
                            + username + "'");
                }
            }

        };
    }

    private String rolesToCommaSeparatedString(List<UserRole> roles) {
        StringBuilder builder = new StringBuilder();
        roles.stream()
                .forEach(role -> builder.append(role).append(","));
        if (builder.toString().endsWith(",")) {
            return builder.substring(0, builder.length() - 1);
        }
        return builder.toString();
    }

}

