package br.com.codenation.mapfood;

import br.com.codenation.mapfood.model.Motorcyclist;
import br.com.codenation.mapfood.repository.MotocyclistRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MapfoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapfoodApplication.class, args);
	}
}

