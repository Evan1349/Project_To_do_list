package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	Task findByTaskId(long taskId);
	List<Task> findByTaskNameContainingIgnoreCase(String taskname);
	
}
