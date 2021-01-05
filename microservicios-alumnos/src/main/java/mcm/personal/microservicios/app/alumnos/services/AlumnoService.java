package mcm.personal.microservicios.app.alumnos.services;

import java.util.Optional;

import mcm.personal.microservicios.commons.entities.models.entity.Alumno;

public interface AlumnoService {

	public Iterable<Alumno> findAll();
	
	public Optional<Alumno> findById(Long id);
	
	public Alumno save(Alumno alumno);
	
	public void deleteById(Long Id);
	
}
