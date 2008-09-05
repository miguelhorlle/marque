package com.marquesexo.bean;


public class Webcam {
	
	private long id_membro;
	private String ip;
	private String porta;
	private String username;
	private String senha;
	private int habilitado;
	
	public Webcam(){
		this.id_membro = 0;
		this.ip = "";
		this.porta = "";
		this.username = "";
		this.senha = "";
		this.habilitado = 2;	
	}
	
	public int getHabilitado() {
		return habilitado;
	}
	
	public void setHabilitado(int habilitado) {
		this.habilitado = habilitado;
	}
	
	public long getId_membro() {
		return id_membro;
	}
	
	public void setId_membro(long id_membro) {
		this.id_membro = id_membro;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getPorta() {
		return porta;
	}
	
	public void setPorta(String porta) {
		this.porta = porta;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
