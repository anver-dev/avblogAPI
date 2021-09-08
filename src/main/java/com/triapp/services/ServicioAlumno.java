package com.triapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triapp.model.Alumno;
import com.triapp.repository.AlumnoRepository;

@Service
public class ServicioAlumno {
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	public Alumno agregarAlumno(Alumno alumno) {
		Optional<Alumno> opAlumnoCreado = alumnoRepository.findByCorreo(alumno.getCorreo());
		Alumno alumnoCreado = null;
		
		if(opAlumnoCreado.isEmpty()) {
			alumnoCreado = alumnoRepository.save(alumno);
		}
		
		return alumnoCreado;
	}
	
}
