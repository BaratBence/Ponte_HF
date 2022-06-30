package com.Ponte_HF_C.Ponte_HF_C;

import com.Ponte_HF_C.Ponte_HF_C.model.LanguageProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Entry point of the application
@SpringBootApplication
public class PonteHfCApplication implements CommandLineRunner {

	@Autowired
	LanguageTrainingProcessor languageTrainingProcessor;

	public static void main(String[] args) {

		SpringApplication.run(PonteHfCApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		languageTrainingProcessor.train();
	}
}
