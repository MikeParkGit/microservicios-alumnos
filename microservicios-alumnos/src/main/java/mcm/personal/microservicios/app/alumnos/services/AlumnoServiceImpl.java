package mcm.personal.microservicios.app.alumnos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcm.personal.microservicios.app.alumnos.models.entity.Alumno;
import mcm.personal.microservicios.app.alumnos.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	public AlumnoRepository repository;
	
	@Override
	public Iterable<Alumno> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Alumno> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return repository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	

}