package mcm.personal.microservicios.app.alumnos.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mcm.personal.microservicios.app.alumnos.services.AlumnoCommonService;
import mcm.personal.microservicios.commons.controllers.CommonController;
import mcm.personal.microservicios.commons.entities.models.entity.Alumno;

@RestController
public class AlumnoCommonController extends CommonController<Alumno, AlumnoCommonService> {

	@PutMapping("/{id}")
	public  ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDB = optional.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	
	
	@GetMapping("/buscar/{param}")
	public ResponseEntity<?> buscar(@PathVariable String param) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByNombreOrApellido(param));
	}

}
