package com.meli.PackTracking.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

import com.meli.PackTracking.form.DogFunFactForm;

@Component
public class DogAPIComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(DogAPIComponent.class);

	@Value("${dogapi.funfacts.URL}")
	private String API_URL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getDogFunFact() {
		try {		
			DogFunFactForm dogForm = restTemplate.getForObject(API_URL, DogFunFactForm.class);
			return dogForm.getData().isEmpty() ? "No fun fact found" : dogForm.getData().get(0).getAttributes().getBody();
		} catch (Exception e) {
			logger.error(String.format("Fun fact API returned error \n %s ", e.getMessage()));
            return "No fun fact found";  // Caso a API falhe ou não retorne dados, sinaliza que o funfact nao foi encontrado.
        }
		
		
	}
}
