package com.triapp.model;

import java.util.HashSet;
import java.util.Set;

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

@Entity
@Data
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCarrera;
	private String nombre;
	private int totalCreditos;
	private int totalTrimestres;

	@OneToMany(targetEntity = Materia.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idCarrera")
	@JsonIgnore
	private final Set<Materia> materias = new HashSet<>();

	public boolean addMateria(Materia materia) {

		if (materia == null)
			throw new IllegalArgumentException("La materia no puede ser null");

		if (materias.contains(materia))
			return false;

		return materias.add(materia);
	}
}
