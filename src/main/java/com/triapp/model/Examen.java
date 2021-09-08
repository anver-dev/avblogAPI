package com.triapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Examen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idExamen;
	private String fechaCreacion;
	private String fechaAplicacion;
	private float calificacion;
	private float valorSobreCalificacionFinal;

	@OneToOne
	private Materia materia;
}
