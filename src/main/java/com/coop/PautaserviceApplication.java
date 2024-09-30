package com.coop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = {"com.coop.vote.entity"})
public class PautaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PautaserviceApplication.class, args);
	}

}
