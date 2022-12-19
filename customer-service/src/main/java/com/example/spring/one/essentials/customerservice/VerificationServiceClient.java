package com.example.spring.one.essentials.customerservice;

/**
 * @author Olga Maciaszek-Sharma
 */
public interface VerificationServiceClient {

	CustomerVerificationResult verify(CustomerApplication customerApplication);
}
