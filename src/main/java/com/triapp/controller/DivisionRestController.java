package com.triapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.triapp.model.Division;
import com.triapp.service.ServicioDivision;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1")
@Api(value = "Alumno")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH,
		RequestMethod.DELETE })
public class DivisionRestController {

	@Autowired
	private ServicioDivision servicioDivision;
	
	@ApiOperation(value = "Registra una division", notes = "Se registro la division")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Division registrada exitosamente"),
			@ApiResponse(code = 409, message = "No se pudo registrar la division"),
			@ApiResponse(code = 500, message = "Error en el servidor") })
	@PostMapping(path = "/division", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Division> create(@RequestBody Division nuevaDivision) {
		try {
			Division division = servicioDivision.agregarDivision(nuevaDivision);
			
			return ResponseEntity.status(HttpStatus.OK).body(division);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
