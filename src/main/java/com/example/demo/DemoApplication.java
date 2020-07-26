package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAuthorizationServer
@EnableResourceServer
@RestController
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}


	@GetMapping("/wtf")
	public String index(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Object rawPassword  = SecurityContextHolder.getContext().getAuthentication().getCredentials();
		return "welcome  to  home page =+"+username+rawPassword;
	}

}
