package com.ecom.Order.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderServiceApplication {

	@Value("${message.value}")
	public String name;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);

	}

	@Bean
	public ObjectMapper objectMapper(){
		System.out.println(name);
		return new ObjectMapper();
	}

}
