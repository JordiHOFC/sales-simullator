package br.com.github.jordihofc.sallessimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SallesSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SallesSimulatorApplication.class, args);
	}

}
