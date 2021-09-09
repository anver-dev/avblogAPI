package com.triapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.triapp.model.Alumno;
import com.triapp.service.ServicioAlumno;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE })
public class AlumnoRestController {
	
	@Autowired
	private ServicioAlumno servicioAlumno;
	
	@ApiOperation(value = "Obtiene alumnos", notes = "Obtiene todos los alumnos registrados")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alumnos mostrados con exito"),})
	@GetMapping(path = "/alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Alumno>> retrieveAll() {
		
			List<Alumno> alumnos = servicioAlumno.obtenerAlumnos();

			return ResponseEntity.status(HttpStatus.OK).body(alumnos);
	}
	
	@ApiOperation(value = "Obtiene un alumno", notes = "Se obtiene el alumno segun su id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se obtuvo el alumno exitosamente"),
			@ApiResponse(code = 404, message = "No existe el alumno"),
			@ApiResponse(code = 500, message = "Error en el servidor") })
	@GetMapping(path = "/alumnos/{idAlumno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alumno> retrieve(@PathVariable("idAlumno") Long idAlumnoABuscar) {
			
		try {
			Alumno alumnoEncontrado = servicioAlumno.obtenerAlumnoPorID(idAlumnoABuscar);
			return ResponseEntity.status(HttpStatus.CREATED).body(alumnoEncontrado);
			
		} catch(IllegalArgumentException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
	
}
