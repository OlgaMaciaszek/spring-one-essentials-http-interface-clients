package com.example.spring.one.essentials.customerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class CustomerServiceApplication {

	@Value("${spring.cloud.discovery.client.simple.instances.verification-service[0].uri}")
	private String verificationServiceUrl;


	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	public VerificationService verificationService(HttpServiceProxyFactory httpServiceProxyFactory) {
		return httpServiceProxyFactory.createClient(VerificationService.class);
	}

	@Bean
	public HttpServiceProxyFactory httpServiceProxyFactory(
			WebClient.Builder webClientBuilder) {
		WebClient webClient = webClientBuilder.baseUrl(this.verificationServiceUrl)
				.build();
		return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
				.build();
	}

}
