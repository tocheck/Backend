package com.example.yeminhye_project;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class YeminhyeProjectApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
						.directory("C:/yeminhye_project/backend")
				        .filename(".env")
						.load();

		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(YeminhyeProjectApplication.class, args);
	}

}
