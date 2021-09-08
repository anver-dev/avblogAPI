package com.triapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Entidad de negocio Materia
 * 
 * @author anver
 *
 */
@Entity
@Data
public class Materia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idMateria;
	private String nombre;

	@OneToMany(targetEntity = Tarea.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idMateria")
	@JsonIgnore
	private final List<Tarea> tareas = new ArrayList<>();
	
	@OneToMany(targetEntity = Examen.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idMateria")
	@JsonIgnore
	private final List<Examen> examenes = new ArrayList<>();
	

	public boolean addTarea(Tarea tarea) {

		if (tarea == null) {
			throw new IllegalArgumentException("La tarea no puede ser null");
		}

		if (tareas.contains(tarea)) {
			return false;
		}

		return tareas.add(tarea);

	}
	
	public boolean addExamen(Examen examen) {

		if (examen == null) {
			throw new IllegalArgumentException("El examen no puede ser null");
		}

		if (examenes.contains(examen)) {
			return false;
		}

		return examenes.add(examen);

	}
}
