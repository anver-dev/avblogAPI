package com.triapp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triapp.model.Carrera;
import com.triapp.repository.CarreraRepository;
import com.triapp.util.Error;

@Service
public class ServicioCarrera {
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	public Carrera agregarCarrera(Carrera carrera) {
		Optional<Carrera> nuevaCarrera = Optional.of(carreraRepository.save(carrera)); 
		
		if(nuevaCarrera.isEmpty())
			throw new IllegalArgumentException(Error.CARRERA_SIN_AGREGAR.obtenerMensaje());
		
		return nuevaCarrera.get();
	}
	
	public Carrera actualizarCarrera(Carrera carrera) {
		Optional<Carrera> opCarreraEncontrada = obtenerCarreraPorId(carrera.getIdCarrera());
		
		if(opCarreraEncontrada.isEmpty())
			throw new IllegalArgumentException(Error.CARRERA_INVALIDA.obtenerMensaje());
		
		Carrera carreraEncontrada = opCarreraEncontrada.get();
		
		carreraEncontrada.setNombre(carrera.getNombre());
		carreraEncontrada.setTotalCreditos(carrera.getTotalCreditos());
		carreraEncontrada.setTotalTrimestres(carrera.getTotalTrimestres());
		
		Carrera carreraActualizada = carreraRepository.save(carreraEncontrada);
		
		return carreraActualizada;
	}
	
	public Optional<Carrera> obtenerCarreraPorId(Long idCarrera) {
		return carreraRepository.findById(idCarrera);
	}
	
	public void eliminarCarreraPorID(Long id) {
		carreraRepository.deleteById(id);
	}
	
	public ArrayList<Carrera> obtenerCarreras() {
		return (ArrayList<Carrera>) carreraRepository.findAll();
	}

}
