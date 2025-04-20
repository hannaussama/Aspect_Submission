package com.example.demo.service;

import com.example.demo.entity.Lodge;
import com.example.demo.repo.LodgeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class LodgeManager {

    @Autowired
    private LodgeRepository repo;

    @Autowired
    private RedisBridge redis;

    private final ObjectMapper mapper = new ObjectMapper();
    private static final String CACHE_KEY = "lodge:all";
    private static final Duration TTL = Duration.ofMinutes(10);

    public List<Lodge> getAll() {
        try {
            String cached = redis.get(CACHE_KEY);
            if (cached != null) {
                return mapper.readValue(cached, new TypeReference<>() {});
            }
        } catch (Exception ignored) {}

        List<Lodge> data = repo.findAll();
        try {
            redis.put(CACHE_KEY, mapper.writeValueAsString(data), TTL);
        } catch (Exception ignored) {}
        return data;
    }

    public Lodge save(Lodge lodge) {
        return repo.save(lodge);
    }

    public String simulateProcess(Long id) {
        try {
            System.out.println(" Processing Lodge " + id);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return " Lodge " + id + " processed";
    }
}
