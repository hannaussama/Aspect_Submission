package com.example.demo.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LockBarrier {
    String prefix();
    String keyExpression();
    long timeout() default 15;
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
