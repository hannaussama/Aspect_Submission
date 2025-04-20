package com.example.demo.controller;

import com.example.demo.annotation.LockBarrier;
import com.example.demo.annotation.ThrottleGate;
import com.example.demo.entity.Lodge;
import com.example.demo.service.LodgeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/lodges")
public class LodgeController {


    @Autowired
    private LodgeManager service;

    @ThrottleGate(limit = 2, window = 10, unit = TimeUnit.SECONDS, keyPrefix = "getAllLodges")
    @GetMapping
    public ResponseEntity<List<Lodge>> list() {
        return ResponseEntity.ok(service.getAll());
    }

    @LockBarrier(prefix = "lodge", keyExpression = "#id", timeout = 15, timeUnit = TimeUnit.SECONDS)
    @GetMapping("/lock/{id}")
    public ResponseEntity<String> lockAndProcess(@PathVariable Long id) {
        return ResponseEntity.ok(service.simulateProcess(id));
    }

    @PostMapping
    public ResponseEntity<Lodge> create(@RequestBody Lodge lodge) {
        return ResponseEntity.ok(service.save(lodge));
    }
}
