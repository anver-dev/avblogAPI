package com.triapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Tarea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTarea;
	private String fechaCreacion;
	private String fechaEntrega;
	private String descripcion;
	private float calificacion;
	private float valorSobreCalificacionFinal;

	@OneToOne
	private Materia materia;

}
