package org.shop;

import org.shop.service.ApplicationPasswordEncoder;
import org.shop.service.ProfileService;
import org.shop.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserServices userServices;

    @Autowired
    ProfileService profileService;

    @Autowired
    @Qualifier("applicationPasswordEncoder")
    PasswordEncoder passwordEncoder;

  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new ApplicationPasswordEncoder();
    return encoder;
  }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(profileService)
        .passwordEncoder(passwordEncoder);
    }

}

