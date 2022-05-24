package com.project.varhrspringbootcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.project")
public class VarHrSpringbootCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(VarHrSpringbootCrudApplication.class, args);
	}

}
