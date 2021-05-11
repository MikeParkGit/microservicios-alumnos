package mcm.personal.microservicios.app.alumnos.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcm.personal.microservicios.app.alumnos.models.repository.AlumnoRepository;
import mcm.personal.microservicios.commons.entities.models.entity.Alumno;
import mcm.personal.microservicios.commons.services.CommonServiceImpl;

@Service
public class AlumnoCommonServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoCommonService {

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String param) {
		
		return repository.findByNombreOrApellido(param);
	}
}
