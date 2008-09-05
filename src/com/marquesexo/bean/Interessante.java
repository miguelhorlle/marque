package com.marquesexo.bean;

import java.io.Serializable;



public class Interessante implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long codigo_sexual_interessado;
	private long codigo_sexual_interessante;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCodigo_sexual_interessado() {
		return codigo_sexual_interessado;
	}

	public void setCodigo_sexual_interessado(long codigo_sexual_interessado) {
		this.codigo_sexual_interessado = codigo_sexual_interessado;
	}

	public long getCodigo_sexual_interessante() {
		return codigo_sexual_interessante;
	}

	public void setCodigo_sexual_interessante(long codigo_sexual_interessante) {
		this.codigo_sexual_interessante = codigo_sexual_interessante;
	}
	
	
	

}
