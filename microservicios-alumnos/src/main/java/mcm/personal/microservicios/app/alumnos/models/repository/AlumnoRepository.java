package mcm.personal.microservicios.app.alumnos.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import mcm.personal.microservicios.commons.entities.models.entity.Alumno;

//public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {

		// Ya contiene las operaciones CRUD para Alumno a través de CrudRepository
		// Las operaciones personalizadas a BD se añaden aqui.
	
	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> findByNombreOrApellido(String param);
	
}
