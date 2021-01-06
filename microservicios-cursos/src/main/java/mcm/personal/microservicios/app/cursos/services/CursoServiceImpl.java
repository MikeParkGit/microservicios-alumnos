package mcm.personal.microservicios.app.cursos.services;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcm.personal.microservicios.app.cursos.models.repository.CursoRepository;
import mcm.personal.microservicios.commons.entities.models.entity.Curso;
import mcm.personal.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Transactional(readOnly = true)
	public List<Curso> findCursosByAlumnoId(Long id) {
		return repository.findCursosByAlumnoId(id);
	}

}
