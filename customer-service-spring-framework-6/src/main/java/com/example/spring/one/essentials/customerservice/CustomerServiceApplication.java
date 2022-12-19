package com.example.spring.one.essentials.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public VerificationService verificationService(HttpServiceProxyFactory httpServiceProxyFactory) {
		return httpServiceProxyFactory.createClient(VerificationService.class);
	}

	@Bean
	public HttpServiceProxyFactory httpServiceProxyFactory(
			WebClient.Builder webClientBuilder, LoadBalancedExchangeFilterFunction lbFunction) {
		WebClient webClient = webClientBuilder.baseUrl("http://verification-service")
				.filter(lbFunction)
				.build();
		return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
				.build();
	}

}
