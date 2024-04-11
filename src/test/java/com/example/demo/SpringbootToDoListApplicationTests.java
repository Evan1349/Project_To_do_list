package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
class SpringbootToDoListApplicationTests {
	
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	TaskService taskService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	
	//Create
		//createUser
	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() {
		User user1 = User.builder().userId(1).userName("Kevin").password("java").tasks(null).email("springboot@gmail.com").build();
		userService.createUser(user1);
	}
		//createTask
	@Test
	@Transactional
	@Rollback(false)
	void contextLoads1() {
		Task task1 = Task.builder().taskName("good morning").build();
//		Task task2 = Task.builder().taskName("good night").build();
		taskService.createTask("Franni", task1);
	}
	//TaskCompleted
	@Test
	void contextLoads2() {
		taskService.isCompleted(102);
	}
	//Delete
	//TaskDelete
	@Test
	@Transactional
	@Rollback(false)
	void contextLoads3() {
		taskService.deleteTask(102);
	}
	//UserDelete
	@Test
	@Transactional
	@Rollback(false)
	void contextLoads4() {
		userService.deleteUser("Evan");
	}
	//Search (?)
	@Test
	@Transactional
	void contextLoads5() {
		taskService.findByTaskName("good");
	}
	//UpdateName
	@Test
	@Transactional
	@Rollback(false)
	void contextLoads6() {
		userService.updateUserName("evan@gmail.com", "Franni");
	}
	//UpdatePassword
	@Test
	@Transactional
	@Rollback(false)
	void contextLoads7() {
		userService.updateUserPassword("evan@gmail.com", "cuty123");
	}
}
