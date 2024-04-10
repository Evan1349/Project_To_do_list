package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Controller", description = "API related to Tasks")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	UserRepository userRepository;
	
	//Task
	//Create Task
	@Operation(summary = "create", description = "created a task by user's name")
	@PostMapping("/createTask/{userName}")
	public void createTask(@PathVariable("userName") String username, @RequestBody Task task) {
		taskService.createTask(username, task);
	}
	//Read Tasks
	@Operation(summary = "find all tasks")
	@GetMapping("/allTasks")
	public ResponseEntity<List<Task>> getAllTask(){
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	//Updated Completed
	@Operation(summary = "completed", description = "useing task_id to mark a task completed")
	@PutMapping("/Completed/{taskId}")
	public ResponseEntity<Task> completedTask(@PathVariable("taskId") long taskId){
		taskService.isCompleted(taskId);
		return ResponseEntity.ok(taskRepository.findByTaskId(taskId));
	}
	//Delete Task
	@Operation(summary = "delete", description = "delete a task by task id")
	@DeleteMapping("/deleteTask/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable("taskId") long taskId){
		taskService.deleteTask(taskId);
		return ResponseEntity.noContent().build();
	}
	//Search Tasks
	@Operation(summary = "search", description = "Searching tasks by task_name with contain and ignore_case")
	@GetMapping("/Search/{tasksSearch}")
	public ResponseEntity<List<Task>> searchTask(@PathVariable("tasksSearch") String taskName){
		List<Task> tasks = taskService.findByTaskName(taskName);
		if(tasks == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tasks);
	}	
	// Get Tasks by UserName
	@Operation(summary = "get tasks by username", description = "Get all tasks of a user by username")
	@GetMapping("/byUser/{userName}")
	public ResponseEntity<List<Task>> getTasksByUser(@PathVariable("userName") String username) {
	    List<Task> tasks = taskService.findTasksByUsername(username);
	    if (tasks == null)
	        return ResponseEntity.notFound().build();
	    return ResponseEntity.ok(tasks);
	}
}
