package com.github.smileycrew.CheckInSystem.checkinsystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CheckinsystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(CheckinsystemApplication.class, args);
	}
}
