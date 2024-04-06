package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/todolist/Users")
@Tag(name = "User Controller", description = "API related to User")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	
	//User
	//Create User
	@Operation(summary = "create", description = "create a user by object")
	@PostMapping
	public void createUser(@RequestBody User user) {
		userService.createUser(user);
	}
	//Read all User
	@Operation(summary = "find all user")
	@GetMapping("/allUsers")
	public ResponseEntity<List<User>> findAllUsers(){
		return ResponseEntity.ok(userService.findAllUser());
	}
	//Update UserName
	@Operation(summary = "update user's name", description = "using email to find user and updating user's name")
	@PutMapping("/updateUserName/{userEmail}/{newName}")
	public ResponseEntity<User> updateUser(@PathVariable String userEmail, String newName){
		userService.updateUserName(userEmail, newName);
		return ResponseEntity.ok(userRepository.findByEmail(userEmail));
	}
	//Delete User
	@Operation(summary = "delete", description = "deleted user by typing user's name")
	@DeleteMapping("/delteUser/{userName}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userName){
		userService.deleteUser(userName);
		return ResponseEntity.noContent().build();
	}
	//Search User
	@Operation(summary = "search", description = "search user by typing user's name ")
	@GetMapping("/search/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username){
		User findedUser = userService.findByUserName(username);
		if (findedUser == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(findedUser);
	}
	
}
