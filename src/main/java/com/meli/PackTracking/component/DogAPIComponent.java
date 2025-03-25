package com.meli.PackTracking.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

import com.meli.PackTracking.form.DogFunFactForm;

@Component
public class DogAPIComponent {

	@Value("${dogapi.funfacts.URL}")
	private String API_URL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getDogFunFact() {
		try {		
			DogFunFactForm dogForm = restTemplate.getForObject(API_URL, DogFunFactForm.class);
			return dogForm.getData().isEmpty() ? "No fun fact found" : dogForm.getData().get(0).getAttributes().getBody();
		} catch (Exception e) {
            System.err.println("Erro na api Fun fact");
    		e.printStackTrace();
            return "No fun fact found";  // Caso a API falhe ou não retorne dados, sinaliza que o funfact nao foi encontrado.
        }
		
		
	}
}
