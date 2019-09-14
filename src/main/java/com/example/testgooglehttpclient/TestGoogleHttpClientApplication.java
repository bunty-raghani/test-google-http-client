package com.example.testgooglehttpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestGoogleHttpClientApplication implements CommandLineRunner {

	@Autowired
	public TestExponentialBackOff TestExponentialBackOff;

	public static void main(String[] args) {
		SpringApplication.run(TestGoogleHttpClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TestExponentialBackOff.testError(504);
		//TestExponentialBackOff.testError(409);
	}

}
