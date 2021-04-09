package mcm.personal.microservicios.app.examenes.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mcm.personal.microservicios.app.examenes.services.ExamenService;
import mcm.personal.microservicios.app.examenes.util.ObjetoBean;
import mcm.personal.microservicios.commons.controllers.CommonController;
import mcm.personal.microservicios.commons.entities.models.entity.Examen;
import mcm.personal.microservicios.commons.entities.models.entity.Pregunta;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExamenController.class);

	private ObjetoBean objBean;
	
	public ExamenController (ObjetoBean ob) {
		this.objBean = ob;
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {
		
		if (result.hasErrors()) {			//Valida el contenido del entity examen
			return this.retornarErrores(result);
		}
		
		Optional<Examen> opcional = service.findById(id);
		if( !opcional.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		Examen examenBd = opcional.get();
		examenBd.setNombre(examen.getNombre());
		
		
		// Inicia bloque
		List<Pregunta> preguntasRecibidas;
		List<Pregunta> preguntasBd;
		List<Pregunta> preguntasEliminadas = new ArrayList<>();
		
		preguntasBd = examenBd.getPreguntas();
		preguntasRecibidas = examen.getPreguntas();
		
		preguntasBd.forEach(pbd -> {
			if ( !preguntasRecibidas.contains(pbd) ) {
				preguntasEliminadas.add(pbd);
			}
		});
		
		preguntasEliminadas.forEach(pregunta -> examenBd.removePregunta(pregunta));
		// Fin bloque
		
		/* El bloque anterior se puede resumir con streams como:
		 * 
		examenBd.getPreguntas()
			.stream().filter(pdb -> !examen.getPreguntas().contains(pdb))
			.forEach(pregunta -> examenBd.removePregunta(pregunta));  // .forEach(examenBd::removePregunta); 
		*/
		
		examenBd.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenBd));
	}
	
	@GetMapping("/filtrar/{param}")
	public ResponseEntity<?> filtrar(@PathVariable String param) {
		objBean.setId(new Integer(1));
		objBean.setNombre("Miguel");
		objBean.setDireccion("Cruz");
		objBean.setFecha(LocalDate.now());
		
		LOGGER.debug("Objeto: " + objBean.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.buscaPorNombre(param));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas() {
		return ResponseEntity.status(HttpStatus.OK).body(service.buscarAsignaturas());
	}
	
}
