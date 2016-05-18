package org.shop.spring.aop;

import java.lang.annotation.*;

/**
 * Created by vprasanna on 5/17/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface ProfileExecution {

}
