package com.pavaniprojects.june_edition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JuneEditionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuneEditionApplication.class, args);
	}

    @GetMapping
    public String helloWorld(){
        return "Hello world Spring Boot";
    }

}
