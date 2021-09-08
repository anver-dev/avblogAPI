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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAlumno;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String contrasenia;
	private int trimestre;
	
	@ManyToOne
	private Carrera carrera;
	
	@ManyToOne
	private Division division;
	
	@OneToMany(targetEntity = Materia.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idAlumno")
	@JsonIgnore
	private final List<Materia> materias = new ArrayList<>();
	 
	@OneToMany(targetEntity = Tarea.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idAlumno")
	@JsonIgnore
	private final List<Tarea> tareas = new ArrayList<>();
	
	public boolean addMateria(Materia materia) {

		if (materia == null) {
			throw new IllegalArgumentException("La materia no puede ser null");
		}

		if (materias.contains(materia)) {
			return false;
		}

		return materias.add(materia);

	}
	
	public boolean addTarea(Tarea tarea) {

		if (tarea == null) {
			throw new IllegalArgumentException("La tarea no puede ser null");
		}

		if (tareas.contains(tarea)) {
			return false;
		}

		return tareas.add(tarea);

	}
}
