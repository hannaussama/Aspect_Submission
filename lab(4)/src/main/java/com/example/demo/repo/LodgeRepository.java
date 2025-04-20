package com.example.demo.repo;

import com.example.demo.entity.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LodgeRepository extends JpaRepository<Lodge, Long> {}
