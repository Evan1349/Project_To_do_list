package com.example.demo.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Base_Entity {
	
	@CreationTimestamp
	@Column
	@JsonIgnore
	private Instant createTime;
	
	@UpdateTimestamp
	@Column
	@JsonIgnore
	private Instant updateTime;
	
	@Column
	@JsonIgnore
	private boolean enabled = true;
}
