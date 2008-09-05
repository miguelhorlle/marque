package com.marquesexo.bean;


public class Voto {
	
	private long id;
	private Enquete enquete;
	private Resposta resposta;
	
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
	
	public Resposta getResposta() {
		return resposta;
	}
	
	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}
	
	

}
