package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing User data from the database.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, or empty otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Check if a user exists by username.
     *
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    Boolean existsByUsername(String username);
}
