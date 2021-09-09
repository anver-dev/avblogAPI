package com.triapp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triapp.model.Alumno;
import com.triapp.model.Carrera;
import com.triapp.model.Division;
import com.triapp.repository.AlumnoRepository;
import com.triapp.util.Error;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServicioAlumno {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private ServicioCarrera servicioCarrera;

	@Autowired
	private ServicioDivision servicioDivision;

	public Alumno agregarAlumno(Alumno alumno) {
		Optional<Alumno> opAlumnoEncontrado = alumnoRepository.findByCorreo(alumno.getCorreo());
		Alumno alumnoCreado = null;

		if (opAlumnoEncontrado.isEmpty())
			alumnoCreado = alumnoRepository.save(alumno);
		else
			throw new IllegalArgumentException(Error.ALUMNO_DUPLICADO.obtenerMensaje());
		
		log.info("Se agregó el alumno con correo: " + alumnoCreado.getCorreo());
		
		return alumnoCreado;
	}

	public Alumno actualizarAlumno(Alumno alumno) {
		Alumno alumnoActualizado = null;
		validarCarreraYDivision(alumno.getCarrera().getIdCarrera(), alumno.getDivision().getIdDivision());

		Alumno alumnoEncontrado = obtenerAlumnoPorID(alumno.getIdAlumno());

		alumnoEncontrado.setNombre(alumno.getNombre());
		alumnoEncontrado.setApellidoPaterno(alumno.getApellidoPaterno());
		alumnoEncontrado.setApellidoMaterno(alumno.getApellidoMaterno());
		alumnoEncontrado.setTrimestre(alumno.getTrimestre());
		alumnoEncontrado.setCarrera(alumno.getCarrera());
		alumnoEncontrado.setDivision(alumno.getDivision());

		alumnoActualizado = alumnoRepository.save(alumnoEncontrado);
		return alumnoActualizado;
	}

	public Alumno obtenerAlumnoPorID(Long id) {
		Optional<Alumno> opAlumnoEncontrado = alumnoRepository.findById(id);

		if (opAlumnoEncontrado.isEmpty())
			throw new IllegalArgumentException(Error.ALUMNO_INVALIDO.obtenerMensaje());

		return opAlumnoEncontrado.get();
	}

	public void eliminarAlumnoPorID(Long id) {
		alumnoRepository.delete(obtenerAlumnoPorID(id));
	}

	public ArrayList<Alumno> obtenerAlumnos() {
		return (ArrayList<Alumno>) alumnoRepository.findAll();
	}

	private void validarCarreraYDivision(Long idCarrera, Long idDivision) {
		Optional<Carrera> opCarreraEncontrada = servicioCarrera.obtenerCarreraPorId(idCarrera);
		Optional<Division> opDivisionEncontrada = servicioDivision.obtenerDivisionPorId(idDivision);

		if (opCarreraEncontrada.isEmpty())
			throw new IllegalArgumentException(Error.CARRERA_INVALIDA.obtenerMensaje());

		if (opDivisionEncontrada.isEmpty())
			throw new IllegalArgumentException(Error.DIVISION_INVALIDA.obtenerMensaje());
	}

}
