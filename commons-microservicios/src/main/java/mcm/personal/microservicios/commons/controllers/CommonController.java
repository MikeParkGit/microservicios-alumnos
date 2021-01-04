package mcm.personal.microservicios.commons.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mcm.personal.microservicios.commons.services.CommonService;


public class CommonController<E, S extends CommonService<E> > {

	@Autowired
	protected S service;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verDetalle(@PathVariable Long id) {
		Optional<E> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		E entityDb = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(entityDb);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody E entity) {
		E entityDb = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar (@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
