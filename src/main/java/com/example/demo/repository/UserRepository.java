package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(long Id);
	User findByUserName(String Name);
	User findByEmail(String Email);
	List<User> findByUserNameContainingIgnoreCase(String UserName);
}
