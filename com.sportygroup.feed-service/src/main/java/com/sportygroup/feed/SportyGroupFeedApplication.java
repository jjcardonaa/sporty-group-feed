package com.sportygroup.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SportyGroupFeedApplication is the entry point for the Sporty Group Feed service.
 */
@SpringBootApplication(scanBasePackages = {"com.sportygroup.feed"})
public class SportyGroupFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportyGroupFeedApplication.class, args);
	}
}
