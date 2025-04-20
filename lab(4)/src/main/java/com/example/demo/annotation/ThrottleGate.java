package com.example.demo.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThrottleGate {
    long limit();
    long window();
    TimeUnit unit() default TimeUnit.SECONDS;
    String keyPrefix() default "";
}
