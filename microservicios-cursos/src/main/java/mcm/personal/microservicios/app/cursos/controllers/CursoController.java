package mcm.personal.microservicios.app.cursos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mcm.personal.microservicios.app.alumnos.controllers.AlumnoController;
import mcm.personal.microservicios.app.cursos.services.CursoService;
import mcm.personal.microservicios.commons.controllers.CommonController;

import mcm.personal.microservicios.commons.entities.models.entity.Alumno;
import mcm.personal.microservicios.commons.entities.models.entity.Curso;
import mcm.personal.microservicios.commons.entities.models.entity.Examen;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CursoController extends CommonController<Curso, CursoService>{

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result , @PathVariable Long id) {
		
		if (result.hasErrors()) {
			return this.retornarErrores(result);
		}
		
		Optional<Curso> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		cursoDB.setNombre(curso.getNombre());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/asignar-alumnos/{id}")
	public ResponseEntity<?> asigarAlumnos(@RequestBody List<Alumno> lstAlumnos, @PathVariable Long id) {
		Optional<Curso> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		
		lstAlumnos.forEach(alumno -> cursoDB.addAlumno(alumno));
		
		/*for (Alumno alu: lstAlumnos) {
			cursoDB.addAlumno(alu);
		}*/
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	/**
	 * 
	 * @param alumno Un objeto Alumno que debe contener el id del mismo
	 * @param idCurso id del curso del que se va a eliminar el alumno
	 * @return
	 */
	@PutMapping("/eliminar-alumno/{id}")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable(name = "id") Long idCurso) {
		Optional<Curso> optional = service.findById(idCurso);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		
		cursoDB.removeAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@GetMapping("/cursos-alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id) {
		List<Curso> cursos = service.findCursosByAlumnoId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(cursos);
	}
	
	@PutMapping("/asignar-examenes/{id}")
	public ResponseEntity<?> asigarExamenes(@RequestBody List<Examen> lstExamenes, @PathVariable Long id) {
		Optional<Curso> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		lstExamenes.forEach(cursoDB::addExamen);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	/**
	 * 
	 * @param alumno Un objeto Alumno que debe contener el id del mismo
	 * @param idCurso id del curso del que se va a eliminar el alumno
	 * @return
	 */
	@DeleteMapping("/eliminar-examen/{id}")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable(name = "id") Long idCurso) {
		Optional<Curso> optional = service.findById(idCurso);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		
		cursoDB.removeExamen(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@GetMapping("/consultar-alumnosA/{id}")
	public EntityModel<Curso> consultarAlumnosA(@PathVariable Long id) {
		Optional<Curso> optional = service.findById(id);
		if (!optional.isPresent()) {
			return  EntityModel.of(new Curso()); //Codigo temp
		}
		Curso cursoDB = optional.get();
		
		EntityModel<Curso> recurso = EntityModel.of(cursoDB);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(CursoController.class).verDetalle(id));
		
		recurso.add(linkTo.withSelfRel());
		
		return recurso;
	}
	
	
	@GetMapping("/consultar-alumnosB/{id}")
	public ResponseEntity<?> consultarAlumnosB(@PathVariable Long id) {
		Optional<Curso> optional = service.findById(id);
		if (!optional.isPresent()) {
			return  ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		
		WebMvcLinkBuilder selfLink = linkTo(methodOn(CursoController.class).verDetalle(id));
        cursoDB.add(selfLink.withSelfRel());
		
		List<Alumno> lstAlumnos = cursoDB.getAlumnos();
		
		lstAlumnos.forEach(alumno -> {
			cursoDB.add(linkTo(methodOn(AlumnoController.class).verDetalle(alumno.getId())).withRel("curso-alumnos"));
		});
		
		return ResponseEntity.status(HttpStatus.OK).body(cursoDB);
	}
	
	@GetMapping("/consultar-alumnosC/{id}")
	public ResponseEntity<?> consultarAlumnosC(@PathVariable Long id) {
		Optional<Curso> optional = service.findById(id);
		if (!optional.isPresent()) {
			return  ResponseEntity.notFound().build();
		}
		Curso cursoDB = optional.get();
		
		WebMvcLinkBuilder selfLink = linkTo(methodOn(CursoController.class).verDetalle(id));
        cursoDB.add(selfLink.withSelfRel());
		
		cursoDB.getAlumnos().forEach(alumno -> {
			alumno.add(linkTo(methodOn(AlumnoController.class).verDetalle(alumno.getId())).withRel("curso-alumnos"));
		});
				
		return ResponseEntity.status(HttpStatus.OK).body(cursoDB);
	}
	
}
