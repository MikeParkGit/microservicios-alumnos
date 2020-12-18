package mcm.personal.microservicios.app.alumnos.models.repository;

import org.springframework.data.repository.CrudRepository;

import mcm.personal.microservicios.app.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
