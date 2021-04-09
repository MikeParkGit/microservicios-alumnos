package mcm.personal.microservicios.app.examenes.services;

import java.util.List;

import mcm.personal.microservicios.commons.entities.models.entity.Asignatura;
import mcm.personal.microservicios.commons.entities.models.entity.Examen;
import mcm.personal.microservicios.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen>{

	public List<Examen> buscaPorNombre(String param);
	
	public List<Asignatura> buscarAsignaturas();
	
	
}
