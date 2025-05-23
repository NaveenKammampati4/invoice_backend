package com.middleware.invoice_ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class InvoiceEmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceEmsApplication.class, args);
	}

}
