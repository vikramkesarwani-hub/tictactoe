package com.game.java.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.game.java.titactoe.controller")
public class BloxGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloxGameApplication.class, args);
	}

}
