package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	//Functions
	//Create
	@Transactional
	public User createUser(User user) {
		User newUser = null;
		newUser = userRepository.save(user);
		return newUser;
	}
	//Read
		//FindOneUser
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}
		//FindAllUser
	public List<User> findAllUser(){
		List<User> users = null;
		users = userRepository.findAll();
		return users;
	}
	//Delete
	public void deleteUser(String name) {
		User user = userRepository.findByUserName(name);
		userRepository.delete(user);
	}
	//Update
	//Name
	@Transactional
	public User updateUserName(String email, String NewName) {
		User user = userRepository.findByEmail(email);
		user.setUserName(NewName);
		User updateduser = userRepository.save(user); 
		return updateduser;
	}
	//Password
	@Transactional
	public User updateUserPassword(String email, String NewPassword) {
		User user = userRepository.findByEmail(email);
		user.setPassword(NewPassword);;
		User updateduser = userRepository.save(user); 
		return updateduser;
	}
}
