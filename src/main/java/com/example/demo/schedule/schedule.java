package com.example.demo.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.MailService;

@Component
public class schedule {
	
	
	@Autowired
	private MailService mailService;
	
	@Scheduled(cron = " 0 0 9 * * ? ") //我設定早上9點寄Email明日排程
	public void task() {
		mailService.toGetEveryUsersTasks();
	}

}
