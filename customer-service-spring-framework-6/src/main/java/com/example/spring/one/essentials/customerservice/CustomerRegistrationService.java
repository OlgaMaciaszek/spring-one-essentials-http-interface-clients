package com.example.spring.one.essentials.customerservice;

import org.springframework.stereotype.Service;

/**
 * @author Olga Maciaszek-Sharma
 */
@Service
public class CustomerRegistrationService {
	private final CustomerRepository customerRepository;

	private final VerificationClient verificationClient;

	public CustomerRegistrationService(CustomerRepository customerRepository, VerificationClient verificationClient) {
		this.customerRepository = customerRepository;
		this.verificationClient = verificationClient;
	}

	CustomerApplicationResult register(CustomerApplication customerApplication) {
		CustomerVerificationResult verificationResult = verificationClient.verify(customerApplication);
		if (CustomerVerificationResult.Status.APPROVED.equals(verificationResult.getStatus())) {
			Customer customer = customerRepository.create(new Customer(customerApplication));
			return new CustomerApplicationResult(customerApplication.getId(), customer.getId(), CustomerApplicationResult.Status.ACCEPTED);
		}
		return new CustomerApplicationResult(customerApplication.getId(), null, CustomerApplicationResult.Status.REJECTED);
	}

}
