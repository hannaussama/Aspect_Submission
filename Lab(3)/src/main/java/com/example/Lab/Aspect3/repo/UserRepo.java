package com.example.Lab.Aspect3.repo;

import com.example.Lab.Aspect3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
