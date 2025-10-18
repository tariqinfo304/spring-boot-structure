package com.app1.src.user.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.app1.src.user.model.User;
import com.app1.src.user.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User one(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping
    public User create(@Valid @RequestBody User user) {
        return repo.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        return repo.findById(id)
                .map(existingUser -> {
                    if (!userDetails.getName().isEmpty())
                        existingUser.setName(userDetails.getName());
                    if (!userDetails.getEmail().isEmpty())
                        existingUser.setEmail(userDetails.getEmail());
                    if (!userDetails.getUsername().isEmpty())
                        existingUser.setUsername(userDetails.getUsername());
                    if (!userDetails.getPhone().isEmpty())
                        existingUser.setPhone(userDetails.getPhone());

                    User updatedUser = repo.save(existingUser);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
