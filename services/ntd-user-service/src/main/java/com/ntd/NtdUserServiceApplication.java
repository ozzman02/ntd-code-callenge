package com.ntd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NtdUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtdUserServiceApplication.class, args);
	}

}
