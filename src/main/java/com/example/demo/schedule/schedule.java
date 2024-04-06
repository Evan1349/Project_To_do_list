package com.example.demo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class schedule {
	
	@Scheduled(cron = " 0 0 18 * * ? ") //每晚6點寄Email明日排程
	public void task() {
		
	}

}
