package org.shop;

import org.shop.interceptors.WebNoCacheInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by vprasanna on 1/14/17.
 */
@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new WebNoCacheInterceptor())
    .addPathPatterns("/*", "/*.html");
  }
}
