package mcm.personal.microservicios.app.examenes.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mcm.personal.microservicios.commons.entities.models.entity.Examen;

public interface ExamenRepository extends CrudRepository<Examen, Long> {

	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> buscaPorNombre(String param);
}
