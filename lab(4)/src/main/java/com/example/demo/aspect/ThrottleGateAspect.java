package com.example.demo.aspect;

import com.example.demo.annotation.ThrottleGate;
import com.example.demo.exception.TooManyHitsException;
import com.example.demo.service.RedisBridge;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component
@Order(1)
public class ThrottleGateAspect {

    @Autowired
    private RedisBridge redis;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(throttleGate)")
    public Object limitCalls(ProceedingJoinPoint joinPoint, ThrottleGate throttleGate) throws Throwable {
        String clientIp = request.getRemoteAddr();
        String redisKey = "throttle:" + throttleGate.keyPrefix() + ":" + clientIp;
        long ttl = throttleGate.unit().toSeconds(throttleGate.window());

        Long hits = redis.increment(redisKey, Duration.ofSeconds(ttl));
        if (hits != null && hits > throttleGate.limit()) {
            throw new TooManyHitsException("You hit the rate limit. Wait a few seconds.");
        }

        return joinPoint.proceed();
    }
}
