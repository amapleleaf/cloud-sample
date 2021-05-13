package com.sample.cloud.apiclient;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ApiClient {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    Class<?>[] configuration() default {};
}
