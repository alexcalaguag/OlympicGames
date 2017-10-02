package com.olympicgames;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Define the main class for the OlympicGames project. The Application class is
 * responsible for starting the rest services, as well as configuring Spring.
 *
 * @author <a href="mailto:alexcalaguag@gmail.com">Alex Calagua</a>
 */
@SpringBootApplication(scanBasePackages = { "com.olympicgames" })
public class OlympicGamesApp {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	/**
	 * Main application method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OlympicGamesApp.class, args);
	}
}
