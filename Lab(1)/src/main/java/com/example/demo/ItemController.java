package com.example.demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController{

    @GetMapping
    public String getResource() {
        return "GET request received";
    }

    @PostMapping
    public String createResource() {
        return "POST request received";
    }

    @PutMapping
    public String updateResource() {
        return "PUT request received";
    }

    @DeleteMapping
    public String deleteResource() {
        return "DELETE request received";
    }
}

@Aspect
@Component
class LoggingAspect {

    @Before("execution(* com.example.demo.ResourceControllerWithAspect.*(..))")
    public void logBeforeMethod() {
        System.out.println("Aspect message : Itemcontroller method is about to be called");
    }
}
