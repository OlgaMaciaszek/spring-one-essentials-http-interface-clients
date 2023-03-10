package com.example.spring.one.essentials.customerservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Olga Maciaszek-Sharma
 */

@FeignClient("verification-service")
public interface VerificationServiceOFClient {

	@PostMapping(path = "/verify")
	CustomerVerificationResult verify(@RequestBody CustomerApplication customerApplication,
			@RequestHeader("X-Custom-Header") String customHeader);

}
