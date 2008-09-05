package com.marquesexo.bean;

import java.io.Serializable;


public class Sexo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Membro pegador;
	private Membro pegado;
	private int status;
	private String comentario_pegador;
	private String comentario_pegado;
	
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}
			
	
	public Membro getPegado() {
		return pegado;
	}


	
	public void setPegado(Membro pegado) {
		this.pegado = pegado;
	}


	
	public Membro getPegador() {
		return pegador;
	}


	
	public void setPegador(Membro pegador) {
		this.pegador = pegador;
	}


	public String getComentario_pegado() {
		return comentario_pegado;
	}
	
	public void setComentario_pegado(String comentario_pegado) {
		this.comentario_pegado = comentario_pegado;
	}
	
	public String getComentario_pegador() {
		return comentario_pegador;
	}
	
	public void setComentario_pegador(String comentario_pegador) {
		this.comentario_pegador = comentario_pegador;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	

}
