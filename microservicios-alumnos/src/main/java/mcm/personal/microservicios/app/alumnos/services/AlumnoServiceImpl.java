package mcm.personal.microservicios.app.alumnos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcm.personal.microservicios.app.alumnos.models.repository.AlumnoRepository;
import mcm.personal.microservicios.commons.entities.models.entity.Alumno;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	//@Autowired
	public AlumnoRepository repository;
	
	public AlumnoServiceImpl (AlumnoRepository alumnoRepository) {
		this.repository = alumnoRepository;
	}
	
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

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String param) {
		
		return repository.findByNombreOrApellido(param);
	}
	

}
