package com.knits.sbs.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class Sbs120Application {

	public static void main(String[] args) {
		 SpringApplication.run(Sbs120Application.class, args);

	}

}
