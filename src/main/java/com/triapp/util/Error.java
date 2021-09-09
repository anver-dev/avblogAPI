package com.triapp.util;

public enum Error {
	ALUMNO_DUPLICADO(0, "El alumno ya existe."), 
	CARRERA_INVALIDA(1, "La carrera no exite."),
	DIVISION_INVALIDA(2, "La division no exite."),
	ALUMNO_INVALIDO(3, "El alumno no existe"),
	CARRERA_SIN_AGREGAR(4, "No se pudo agregar la carrera"),
	DIVISION_SIN_AGREGAR(5, "No se pudo agregar la division");

	private final int codigo;
	private final String mensaje;

	private Error(int code, String description) {
		this.codigo = code;
		this.mensaje = description;
	}

	public String obtenerMensaje() {
		return mensaje;
	}

	public int obtenerCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return codigo + ": " + mensaje;
	}
}
