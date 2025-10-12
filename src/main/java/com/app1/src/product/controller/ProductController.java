package com.app1.src.product.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.app1.src.product.model.Product;
import com.app1.src.product.repository.ProductRepository;

import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Product> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @PostMapping
    public Product create(@Valid @RequestBody Product user) {
        return repo.save(user);
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product userDetails) {
    //     return repo.findById(id)
    //     .map(existingProduct -> {
    //         existingProduct.setName(userDetails.getName());
    //         existingProduct.setPrice(userDetails.getPrice());
    //     }).orElse(ResponseEntity.notFound().build());
    // }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
