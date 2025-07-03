package com.e_bal.e_bal_chat_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ChatServiceApplication {

	static {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
			System.out.println("Loaded env: " + entry.getKey() + "=" + entry.getValue());
		});
	}

	public static void main(String[] args) {
		System.out.println("-------------CHAT_DB_URL = " + System.getenv("DB_URL"));
		SpringApplication.run(ChatServiceApplication.class, args);
	}

}
