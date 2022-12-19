package com.example.spring.one.essentials.customerservice;

import java.util.UUID;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Olga Maciaszek-Sharma
 */
@RestController
public class CustomerRegistrationController {

	private final CustomerRegistrationService registrationService;

	public CustomerRegistrationController(CustomerRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping("/register")
	Mono<CustomerApplicationResult> register(@RequestBody CustomerApplication customerApplication) {
		return registrationService.register(customerApplication);
	}

	@GetMapping("/test")
	Mono<CustomerApplicationResult> register() {
		return registrationService.register(new CustomerApplication(UUID.randomUUID(), "Anna", "Smith"));
	}
}

