package com.triapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.triapp.model.Tarea;

public interface TareaRepository extends CrudRepository<Tarea, Long> {

}
