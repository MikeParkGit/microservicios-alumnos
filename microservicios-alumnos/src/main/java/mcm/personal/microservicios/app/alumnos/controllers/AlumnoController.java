package mcm.personal.microservicios.app.alumnos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mcm.personal.microservicios.app.alumnos.models.entity.Alumno;
import mcm.personal.microservicios.app.alumnos.services.AlumnoService;


@RestController
public class AlumnoController {

	@Autowired
	public AlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verDetalle(@PathVariable Long id) {
		Optional<Alumno> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optional.get());
	}
		
	
}
