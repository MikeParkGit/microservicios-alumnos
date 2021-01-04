package mcm.personal.microservicios.app.cursos.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mcm.personal.microservicios.app.cursos.models.entity.Curso;
import mcm.personal.microservicios.app.cursos.services.CursoService;
import mcm.personal.microservicios.commons.controllers.CommonController;

@RestController
public class CursoController extends CommonController<Curso, CursoService>{

	@PutMapping("/{id}")
	public  ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id) {
		Optional<Curso> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		cursoDB.setNombre(curso.getNombre());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
}
