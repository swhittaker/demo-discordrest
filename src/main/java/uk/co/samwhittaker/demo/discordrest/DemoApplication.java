package uk.co.samwhittaker.demo.discordrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application demonstrating integrating a REST endpoint with the Discord (https://discord.com/) messaging platform
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
