package mcm.personal.microservicios.app.alumnos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"mcm.personal.microservicios.commons.entities.models.entity"})
public class MicroserviciosAlumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosAlumnosApplication.class, args);
	}

}
