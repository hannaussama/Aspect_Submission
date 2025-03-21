package com.example.demo.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class loggingaspect {

    private static final Logger logger = LoggerFactory.getLogger(loggingaspect.class);

    @Before("execution(* com.example.demo.controllers.ItemController.*(..))")
    public void logBeforeMethod() {
        System.out.println("Aspect message: ItemController method is about to be called");
    }
}
