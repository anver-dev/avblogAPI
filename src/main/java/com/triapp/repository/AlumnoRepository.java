package com.triapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.triapp.model.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

	Optional<Alumno> findByCorreo(String correo);

}
