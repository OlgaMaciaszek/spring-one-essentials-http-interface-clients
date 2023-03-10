package com.example.spring.one.essentials.customerservice;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author Rossen Stoyanchev
 */
public interface VerificationService {

	@PostExchange(url = "/verify")
	Mono<CustomerVerificationResult> verify(@RequestBody CustomerApplication customerApplication);

}
