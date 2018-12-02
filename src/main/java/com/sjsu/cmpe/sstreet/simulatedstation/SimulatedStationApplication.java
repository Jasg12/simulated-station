package com.sjsu.cmpe.sstreet.simulatedstation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SimulatedStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulatedStationApplication.class, args);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Logger logger(){
		return LoggerFactory.getLogger("application");
	}

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
