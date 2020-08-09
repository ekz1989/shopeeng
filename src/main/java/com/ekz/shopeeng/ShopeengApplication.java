package com.ekz.shopeeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ShopeengApplication extends SpringBootServletInitializer{
	public static final String MAIN_PATH = "/api";

	public static void main(String[] args) {
		SpringApplication.run(ShopeengApplication.class, args);
	}

}
