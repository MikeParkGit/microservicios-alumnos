package mcm.personal.microservicios.app.cursos.services;

import org.springframework.stereotype.Service;

import mcm.personal.microservicios.app.cursos.models.repository.CursoRepository;
import mcm.personal.microservicios.commons.entities.models.entity.Curso;
import mcm.personal.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {



}
