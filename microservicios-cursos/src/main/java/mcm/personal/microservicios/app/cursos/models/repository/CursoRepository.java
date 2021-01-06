package mcm.personal.microservicios.app.cursos.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mcm.personal.microservicios.commons.entities.models.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

	@Query("Select c from Curso c join fetch c.alumnos a where a.id = ?1")
	public List<Curso> findCursosByAlumnoId(Long id);
}
