package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;




@Service
public class MailService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    TaskService taskService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void toGetEveryUsersTasks() {
		
	    List<User> users = userRepository.findAll();
	    
	    users.forEach(user -> {
	        List<Task> userTasks = taskService.findTasksByUsername(user.getUserName());
	        List<Task> incompleteTasks = userTasks.stream()
                    .filter(task -> !task.isCompleted())
                    .collect(Collectors.toList());
	        
	        try {
	            sendMail(user.getEmail(), user.getUserName(), incompleteTasks);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
	public void sendMail(String to, String username, List<Task> tasks) throws MessagingException {
		MimeMessage mm = mailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm);
		
		//建立信件內容
		StringBuilder content = new StringBuilder();
	    content.append("Hi ").append(username).append(", your today's works").append("\n\n");
	    for (Task task : tasks) {
	        content.append(task.getTaskName()).append("\n");
	    }
		
		
		mmh.setSubject("Subject: today's works list");
		mmh.setTo(to);
		mmh.setText(content.toString(), true);
			
			
		mailSender.send(mm);
			
	}
	
	
}
