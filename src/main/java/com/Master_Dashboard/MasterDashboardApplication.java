package com.Master_Dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.Master_Dashboard.Encryption.Encryption;

@SpringBootApplication
@EnableJpaRepositories("com.Master_Dashboard.repository")
public class MasterDashboardApplication {
	
	public static void main(String[] args) {
		
		System.out.println(Encryption.decString("RFIsTevO+yySrMgui4vf4cDds0ThlYWrSohWYcXn19d7qdA+QlZJ2IHHoXupuVe5"));
		SpringApplication.run(MasterDashboardApplication.class, args);
	}

}	