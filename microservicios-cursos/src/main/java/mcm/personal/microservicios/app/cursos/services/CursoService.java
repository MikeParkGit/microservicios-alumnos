package mcm.personal.microservicios.app.cursos.services;

import java.util.List;

import mcm.personal.microservicios.commons.entities.models.entity.Curso;
import mcm.personal.microservicios.commons.services.CommonService;

public interface CursoService extends CommonService<Curso> {

	public List<Curso> findCursosByAlumnoId(Long id);
}
