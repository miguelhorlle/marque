package com.marquesexo.bean;

import java.io.Serializable;

public class Bloqueado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long codigo_sexual_bloqueado;
	private long codigo_sexual_bloqueante;
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCodigo_sexual_bloqueado() {
		return codigo_sexual_bloqueado;
	}

	public void setCodigo_sexual_bloqueado(long codigo_sexual_bloqueado) {
		this.codigo_sexual_bloqueado = codigo_sexual_bloqueado;
	}

	public long getCodigo_sexual_bloqueante() {
		return codigo_sexual_bloqueante;
	}

	public void setCodigo_sexual_bloqueante(long codigo_sexual_bloqueante) {
		this.codigo_sexual_bloqueante = codigo_sexual_bloqueante;
	}

	
	

}
