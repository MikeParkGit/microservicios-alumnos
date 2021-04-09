package mcm.personal.microservicios.app.examenes.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcm.personal.microservicios.app.examenes.models.repository.AsignaturaRepository;
import mcm.personal.microservicios.app.examenes.models.repository.ExamenRepository;
import mcm.personal.microservicios.commons.entities.models.entity.Asignatura;
import mcm.personal.microservicios.commons.entities.models.entity.Examen;
import mcm.personal.microservicios.commons.services.CommonServiceImpl;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	@Override
	public List<Examen> buscaPorNombre(String param) {
		return repository.buscaPorNombre(param);
	}
	
	@Override
	/**
	 * Lista todas las asignaturas existentes [findAll]
	 */
	public List<Asignatura> buscarAsignaturas() {
		return (List<Asignatura>)  asignaturaRepository.findAll();
	}

}
