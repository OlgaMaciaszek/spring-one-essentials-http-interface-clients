package com.example.spring.one.essentials.customerservice;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

/**
 * @author Olga Maciaszek-Sharma
 */
@Service
public class CustomerRegistrationService {
	private final CustomerRepository customerRepository;

	private final VerificationService verificationService;

	public CustomerRegistrationService(CustomerRepository customerRepository, VerificationService verificationService) {
		this.customerRepository = customerRepository;
		this.verificationService = verificationService;
	}

	Mono<CustomerApplicationResult> register(CustomerApplication customerApplication) {
		Mono<CustomerVerificationResult> verificationResult = verificationService.verify(customerApplication);
		return verificationResult.map(result -> {
			if (CustomerVerificationResult.Status.APPROVED.equals(result.getStatus())) {
				Customer customer = customerRepository.create(new Customer(customerApplication));
				return new CustomerApplicationResult(customerApplication.getId(), customer.getId(), CustomerApplicationResult.Status.ACCEPTED);
			}
			return new CustomerApplicationResult(customerApplication.getId(), null, CustomerApplicationResult.Status.REJECTED);
		});
	}
}
