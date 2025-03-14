package com.example.demo.User;
import com.example.demo.User.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(path = "api/usersAspect")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<user> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public Optional<user> getUserById(@PathVariable Integer id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    public user createUser(@RequestBody CreateUserDto createUserDto) {
        return this.userService.createUser(createUserDto);
    }

    @PutMapping("/{id}")
    public user updateUser(@PathVariable Integer id, @RequestBody CreateUserDto updatedUser) {
        return this.userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return this.userService.deleteUser(id);
    }
}
