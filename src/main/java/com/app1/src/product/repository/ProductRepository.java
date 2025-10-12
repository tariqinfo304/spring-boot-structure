package com.app1.src.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app1.src.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { }
