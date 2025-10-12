package com.app1.src.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app1.src.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> { }
