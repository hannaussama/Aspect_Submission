package com.example.demo.aspect;

import com.example.demo.annotation.LockBarrier;
import com.example.demo.exception.BusyResourceException;
import com.example.demo.service.RedisBridge;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.UUID;

@Aspect
@Component
public class LockBarrierAspect {

    @Autowired
    private RedisBridge redis;

    private final ExpressionParser parser = new SpelExpressionParser();

    @Around("@annotation(lockBarrier)")
    public Object enforceLock(ProceedingJoinPoint joinPoint, LockBarrier lockBarrier) throws Throwable {
        MethodSignature sig = (MethodSignature) joinPoint.getSignature();
        Method method = sig.getMethod();

        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        String[] names = sig.getParameterNames();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(names[i], args[i]);
        }

        String dynamicKey = parser.parseExpression(lockBarrier.keyExpression()).getValue(context, String.class);
        String redisKey = "lock:" + lockBarrier.prefix() + ":" + dynamicKey;
        String token = UUID.randomUUID().toString();
        Duration expiry = Duration.of(lockBarrier.timeout(), lockBarrier.timeUnit().toChronoUnit());

        boolean locked = Boolean.TRUE.equals(redis.tryLock(redisKey, token, expiry));

        if (!locked) {
            throw new BusyResourceException("Resource is in use. Please retry later.");
        }

        try {
            return joinPoint.proceed();
        } finally {
            redis.delete(redisKey);
        }
    }
}

