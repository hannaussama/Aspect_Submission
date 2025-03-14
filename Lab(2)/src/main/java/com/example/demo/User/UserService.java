package com.example.demo.User;
import com.example.demo.User.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<user> getUsers() {
        return this.userRepository.findAll();
    }

    public Optional<user> getUserById(Integer id) {
        return this.userRepository.findById(id);
    }

    public user createUser(CreateUserDto userDto) {
        user user = new user(
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getPhoneNumber()
        );
        return this.userRepository.save(user);
    }

    public user updateUser(Integer id, CreateUserDto updatedUser) {
        Optional<user> existingUser = this.userRepository.findById(id);
        if (existingUser.isPresent()) {
            user user = existingUser.get();
            user.setEmail(updatedUser.getEmail());
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            return this.userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public String deleteUser(Integer id) {
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
            return "User deleted successfully.";
        } else {
            return "User not found.";
        }
    }
}
