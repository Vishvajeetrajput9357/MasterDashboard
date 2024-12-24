package com.Master_Dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.Master_Dashboard.repository")
public class MasterDashboardApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(MasterDashboardApplication.class, args);
	}

}