package org.shop;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

/**
 * Created by vprasanna on 5/15/2016.
 * The type Application.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class Application extends SpringBootServletInitializer {
    private static Class<Application> applicationClass = Application.class;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(applicationClass, args);
        displayContext(context);
    }

    private static void displayContext(ConfigurableApplicationContext ctx) {
        if (null == ctx) {
            return;
        }
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        SpringApplicationBuilder appBuilder = application.sources(applicationClass);
        ConfigurableApplicationContext context = application.context();
        displayContext(context);
        return appBuilder;
    }

    /**
     * Gets profiling proxy.
     *
     * @return the profiling proxy
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getProfilingProxy() {
        DefaultAdvisorAutoProxyCreator advisor = new DefaultAdvisorAutoProxyCreator();
        advisor.setUsePrefix(true);
        advisor.setProxyTargetClass(true);
        return  advisor;
    }


}
