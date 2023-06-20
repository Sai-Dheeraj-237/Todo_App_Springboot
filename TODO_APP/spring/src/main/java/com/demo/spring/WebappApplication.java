package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
//@ComponentScan (basePackages = {"com.demo.spring.controller"})
public class WebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}


//<dependency>
//<groupId>org.springframework.boot</groupId>
//<artifactId>spring-boot-starter-data-jpa</artifactId>
//</dependency>