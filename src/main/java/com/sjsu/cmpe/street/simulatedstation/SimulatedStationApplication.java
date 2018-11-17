package com.sjsu.cmpe.street.simulatedstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulatedStationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulatedStationApplication.class, args);
	}

	public static class SensorList {
	   String name;
	   String location;
	   String type;

		public void set(String x,String y,String z){
			name=x;
			location=y;
			type=z;
		}
	}
}
