package com.uniovi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.uniovi.services.MarkSoapService;

@Configuration
public class CustomConfiguration {
	@Value("${service.endpoint}")
	private String serviceEndpoint;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.uniovi.wsdl");
		return marshaller;
	}

	@Bean
	public MarkSoapService marksService(Jaxb2Marshaller marshaller) {
		MarkSoapService client = new MarkSoapService();
		client.setDefaultUri(serviceEndpoint);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}