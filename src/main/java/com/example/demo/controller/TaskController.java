package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/todolist/tasks")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	UserRepository userRepository;
	
	//CreateTask
	@PostMapping("/createTask/{username}")
	public void createTask(@PathVariable String username, @RequestBody Task task) {
		taskService.createTask(username, task);
	}
	//ReadTasks
	@GetMapping("/allTasks")
	public ResponseEntity<List<Task>> getAllTask(){
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	
}
