package mcm.personal.microservicios.app.examenes.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;



@Data
@Component
public class ObjetoBean {
	
	private Integer id;
	private String nombre;
	private String direccion;
	private LocalDate fecha;
}
