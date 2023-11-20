package com.Create_a_graph.easygraphing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.Create_a_graph.easygraphing.repository")
public class EasygraphingApplication {
	public static void main(String[] args) {
		SpringApplication.run(EasygraphingApplication.class, args);
	}
}
