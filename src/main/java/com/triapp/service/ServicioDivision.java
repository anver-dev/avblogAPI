package com.triapp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triapp.model.Division;
import com.triapp.repository.DivisionRepository;
import com.triapp.util.Error;

@Service
public class ServicioDivision {
	
	@Autowired
	private DivisionRepository divisionRepository;
	
	public Division agregarDivision(Division division) {
		Optional<Division> nuevaDivision = Optional.of(divisionRepository.save(division)); 
		
		if(nuevaDivision.isEmpty())
			throw new IllegalArgumentException(Error.DIVISION_SIN_AGREGAR.obtenerMensaje());
		
		return nuevaDivision.get();
	}
	
	public Division actualizarDivision(Division division) {
		Optional<Division> opDivisionEncontrada = obtenerDivisionPorId(division.getIdDivision());
		
		if(opDivisionEncontrada.isEmpty())
			throw new IllegalArgumentException(Error.CARRERA_INVALIDA.obtenerMensaje());
		
		Division divisionEncontrada = opDivisionEncontrada.get();
		
		divisionEncontrada.setNombre(division.getNombre());
		divisionEncontrada.setAbreviacion(division.getAbreviacion());
		
		Division divisionActualizada = divisionRepository.save(divisionEncontrada);
		
		return divisionActualizada;
	}
	
	public Optional<Division> obtenerDivisionPorId(long idDivision) {
		return divisionRepository.findById(idDivision);
	}
	
	public void eliminarDivisionPorID(Long id) {
		divisionRepository.deleteById(id);
	}
	
	public ArrayList<Division> obtenerDivisiones() {
		return (ArrayList<Division>) divisionRepository.findAll();
	}

}
