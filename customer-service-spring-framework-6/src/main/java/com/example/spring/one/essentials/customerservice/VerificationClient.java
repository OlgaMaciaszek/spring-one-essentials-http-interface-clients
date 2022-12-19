package com.example.spring.one.essentials.customerservice;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @author Rossen Stoyanchev
 */
public interface VerificationClient {

	@PostExchange(url = "/verify")
	CustomerVerificationResult verify(@RequestBody CustomerApplication customerApplication);

}
