package mcm.personal.microservicios.app.alumnos.services;

import java.util.List;

import mcm.personal.microservicios.commons.entities.models.entity.Alumno;
import mcm.personal.microservicios.commons.services.CommonService;

public interface AlumnoCommonService extends CommonService<Alumno>{

	public List<Alumno> findByNombreOrApellido(String param);
}
