package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
	@Autowired
	UserService userService;
	@Autowired
	TaskRepository taskRepository;
	@Autowired
	UserRepository userRepository;
	
	//Functions
	//Create
		@Transactional
		public Task createTask(String username, Task task) {
			Task newTask = null;
			User user = userRepository.findByUserName(username);
			newTask = taskRepository.save(task);
			newTask.setUser(user);			
			return newTask;
		}
		
	//Read
		public List<Task> getAllTasks(){
			List<Task> tasks = null;
			tasks = taskRepository.findAll();
			return tasks;
		}
		
	//Update
		//UpdateTaskName
		@Transactional
		public void updateTaskName(long taskId, String newTaskName) {
			taskRepository.findByTaskId(taskId).setTaskName(newTaskName);
		}
		//Completed
		@Transactional
		public void isCompleted(long taskId) {
			taskRepository.findByTaskId(taskId).setCompleted(true);
		}
	
	//Delete
		@Transactional
		public void deleteTask(long taskId) {
			Task deleteTask = taskRepository.findByTaskId(taskId);
			taskRepository.delete(deleteTask);
		}
		
	//Search
		//search taskName
		public List<Task> findByTaskName(String taskName){
			return taskRepository.findByTaskNameContainingIgnoreCase(taskName);

		}		
		// Find tasks by userId
	    public List<Task> findTasksByUsername(String username) {
	        User user = userRepository.findByUserName(username);
	        return user.getTasks();
	    }
}