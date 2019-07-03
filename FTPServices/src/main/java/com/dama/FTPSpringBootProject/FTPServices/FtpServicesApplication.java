package com.dama.FTPSpringBootProject.FTPServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dama")
public class FtpServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtpServicesApplication.class, args);
	}

}
