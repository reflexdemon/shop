package org.shop;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by vprasanna on 5/15/2016.
 * The type Application.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan
@EnableSwagger2
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
        return advisor;
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("rest")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/rest/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Nanthinis Healthcare REST Shop")
                .description("REST API for Nanthinis Healthcare online shop")
                .termsOfServiceUrl("http://nanthinishealthcare.com/")
                .contact("Venkat")
                .license("Nanthinis Healthcare")
                .licenseUrl("http://nanthinishealthcare.com/")
                .version("1.0")
                .build();
    }
}
