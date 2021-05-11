package mcm.personal.microservicios.app.alumnos.controllers;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mcm.personal.microservicios.app.alumnos.services.AlumnoService;
import mcm.personal.microservicios.commons.entities.models.entity.Alumno;


//@RestController
public class AlumnoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AlumnoController.class);
	

	//@Autowired
	public AlumnoService service;
	
	public AlumnoController (AlumnoService serv) {
		this.service = serv;
	}
	
	@GetMapping
	public ResponseEntity<?> listar() {
		//return ResponseEntity.ok().body(service.findAll());
		LOGGER.debug("Ingresa a AlumnoController.listar ");
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verDetalle(@PathVariable Long id) {
		Optional<Alumno> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		//return ResponseEntity.ok(optional.get());
		Alumno alumno = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(alumno);
		//return ResponseEntity.ok(alumno);
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno) {
		Alumno alumnoDb = service.save(alumno);
		//return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
		BodyBuilder bb = ResponseEntity.status(HttpStatus.CREATED);
		ResponseEntity<Alumno> responseEntity = bb.body(alumnoDb);
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public  ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDB = optional.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar (@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/buscar/{param}")
	public ResponseEntity<?> buscar(@PathVariable String param) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByNombreOrApellido(param));
	}
	
	
	/**
	 * Para crear un registro de Alunmno pero anexando una foto (.jpg) al registro en un campo blob
	 * @param alumno
	 * @param archivo
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearConFoto(Alumno alumno, 
										  @RequestParam MultipartFile archivo) throws IOException {
		if ( !archivo.isEmpty() ) {
			alumno.setFoto(archivo.getBytes());
		}
		Alumno alumnoDb = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
	}
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(Alumno alumno, 
											@PathVariable Long id, 
											@RequestParam MultipartFile archivo) throws IOException  {
		Optional<Alumno> optional = service.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDB = optional.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		if ( !archivo.isEmpty() ) {
			alumnoDB.setFoto(archivo.getBytes());
		}	
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	/**
	 * Para visualizar una foto almacenada el en campo foto de la entidad Alumno
	 * @param id
	 * @return
	 */
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto (@PathVariable Long id) {
		Optional<Alumno> optional = service.findById(id);
		if (!optional.isPresent() || optional.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumno = optional.get();
		Resource imagen = new ByteArrayResource(alumno.getFoto());
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(imagen);
	}
}
