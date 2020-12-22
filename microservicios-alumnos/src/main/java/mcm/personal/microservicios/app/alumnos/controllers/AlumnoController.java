package mcm.personal.microservicios.app.alumnos.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mcm.personal.microservicios.app.alumnos.models.entity.Alumno;
import mcm.personal.microservicios.app.alumnos.services.AlumnoService;


@RestController
public class AlumnoController {

	@Autowired
	public AlumnoService service;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		//return ResponseEntity.ok().body(service.findAll());
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verDetalle(@PathVariable Long id) {
		Optional<Alumno> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		//return ResponseEntity.ok(optional.get());
		Alumno alumno = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(alumno);
		//return ResponseEntity.ok(alumno);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno) {
		Alumno alumnoDb = service.save(alumno);
		//return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
		BodyBuilder bb = ResponseEntity.status(HttpStatus.CREATED);
		ResponseEntity<Alumno> responseEntity = bb.body(alumnoDb);
		return responseEntity;
	}
	
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar (@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
