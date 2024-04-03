package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity
@Table(name = "T_TASK")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long taskId;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	private String task;
	private boolean completed;
	private long time;
}