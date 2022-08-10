package com.example.demoag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class DemoagApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoagApplication.class, args);
	}

}
