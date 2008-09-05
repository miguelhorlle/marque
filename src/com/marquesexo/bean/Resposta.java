package com.marquesexo.bean;

public class Resposta {
	
	private long id;
	private Enquete enquete;
	private String texto;
	
	public Enquete getEnquete() {
		return enquete;
	}
	
	public void setEnquete(Enquete enquete) {
		this.enquete = enquete;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

	
}
