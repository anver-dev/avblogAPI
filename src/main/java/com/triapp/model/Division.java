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
public class Division {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDivision;
	private String nombre;
	private String abreviacion;
	
	@OneToMany(targetEntity = Carrera.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idDivision")
	@JsonIgnore
	private final Set<Carrera> carreras = new HashSet<>();
	
	public boolean addCarrera(Carrera carrera) {

		if (carrera == null) {
			throw new IllegalArgumentException("El examen no puede ser null");
		}

		if (carreras.contains(carrera)) {
			return false;
		}

		return carreras.add(carrera);

	}
}
