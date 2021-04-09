package mcm.personal.microservicios.commons.entities.models.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="alumnos")
public class Alumno extends RepresentationModel<Alumno> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotEmpty				//Valida que el campo traiga informacion (No vacio)
	private String nombre;
	
	@Column
	@NotEmpty
	private String apellido;
	
	@Column
	@Email					// Valida el formato del campo que almacena un email
	private String email;
	
	@Column(nullable = false)
	private LocalDateTime createAt;
	
	@Lob
	@JsonIgnore
	private byte[] foto;  	/**  Para almacenar un archivo binario en la tabla */
	
	@PrePersist
	public void prePersist() {
		this.createAt = LocalDateTime.now();
	}
	
	
	public Integer getFotoHashCode() {
		return (this.foto != null)? this.foto.hashCode():null;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno alumnno = (Alumno) obj;
		
		return this.id != null && this.id.equals(alumnno.getId());
	}
	
}
