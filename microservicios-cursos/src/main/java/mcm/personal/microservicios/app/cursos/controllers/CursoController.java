package mcm.personal.microservicios.app.cursos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mcm.personal.microservicios.app.cursos.services.CursoService;
import mcm.personal.microservicios.commons.controllers.CommonController;
import mcm.personal.microservicios.commons.entities.models.entity.Alumno;
import mcm.personal.microservicios.commons.entities.models.entity.Curso;

@RestController
public class CursoController extends CommonController<Curso, CursoService>{

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id) {
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
}
