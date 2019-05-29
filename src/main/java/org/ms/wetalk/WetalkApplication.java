package org.ms.wetalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class WetalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(WetalkApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

}
