package com.marquesexo.bean;

import java.io.Serializable;


public class Motel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	private String endereco;
	private String telefone;
	private String site;
	private String email;
	private String texto;
	private String cidade;
	private String bairro;
	private int prioridade;
	
	
	
	
	public String getBairro() {
		return bairro;
	}

	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	
	public String getCidade() {
		return cidade;
	}

	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	
	public int getPrioridade() {
		return prioridade;
	}

	
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

}
