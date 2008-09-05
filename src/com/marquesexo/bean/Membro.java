package com.marquesexo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author e160237
 *
 */
public class Membro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long codigo_sexual;
	private String nome;
	private String apelido;
	private String email;
	private String senha;
	private int situacao;
	private int faz_sexo_com;
	private int idade;
	private String cidade;
	private String estado;
	private int sexo;
	private float peso;
	private float altura;
	private int tipo_fisico;
	private int tom_pele;
	private int permissao;
	private boolean online;
	private boolean bloqueado;
	private boolean interessante;
	private boolean pegou;
	private int estado_civil;
	private Timestamp data;
	private Timestamp fim;
	private String texto;
	private int discreto;
	private boolean webcam_habilitada;
	private int qtd_pegou;
	
	
	public Membro(){
		
	}
	
	public Membro(long codigo_sexual){
		this.codigo_sexual = codigo_sexual;
	}
	
	

	
	
	
	
	public int getQtd_pegou() {
		return qtd_pegou;
	}

	
	public void setQtd_pegou(int qtd_pegou) {
		this.qtd_pegou = qtd_pegou;
	}

	public boolean isPegou() {
		return pegou;
	}

	
	public void setPegou(boolean pegou) {
		this.pegou = pegou;
	}

	public boolean isWebcam_habilitada() {
		return webcam_habilitada;
	}




	
	public void setWebcam_habilitada(boolean webcam_habilitada) {
		this.webcam_habilitada = webcam_habilitada;
	}




	public int getDiscreto() {
		return discreto;
	}



	
	public void setDiscreto(int discreto) {
		this.discreto = discreto;
	}



	public String getTexto() {
		return texto;
	}



	public void setTexto(String texto) {
		this.texto = texto;
	}



	public int getEstado_civil() {
		return estado_civil;
	}


	
	public void setEstado_civil(int estado_civil) {
		this.estado_civil = estado_civil;
	}


	
	public Timestamp getFim() {
		return fim;
	}


	
	public void setFim(Timestamp fim) {
		this.fim = fim;
	}


	public Timestamp getData() {
		return data;
	}

	
	public void setData(Timestamp data) {
		this.data = data;
	}



	public boolean isBloqueado() {
		return bloqueado;
	}


	
	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}


	
	public boolean isInteressante() {
		return interessante;
	}


	
	public void setInteressante(boolean interessante) {
		this.interessante = interessante;
	}


	public String getApelido() {
		return apelido;
	}

	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public int getPermissao() {
		return permissao;
	}

	public void setPermissao(int permissao) {
		this.permissao = permissao;
	}

	public int getTom_pele() {
		return tom_pele;
	}

	public void setTom_pele(int tom_pele) {
		this.tom_pele = tom_pele;
	}

	public float getAltura() {
		return altura;
	}
	
	public void setAltura(float altura) {
		this.altura = altura;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public long getCodigo_sexual() {
		return codigo_sexual;
	}
	
	public void setCodigo_sexual(long codigo_sexual) {
		this.codigo_sexual = codigo_sexual;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getFaz_sexo_com() {
		return faz_sexo_com;
	}
	
	public void setFaz_sexo_com(int faz_sexo_com) {
		this.faz_sexo_com = faz_sexo_com;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getSexo() {
		return sexo;
	}
	
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	
	public int getSituacao() {
		return situacao;
	}
	
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	
	public int getTipo_fisico() {
		return tipo_fisico;
	}
	
	public void setTipo_fisico(int tipo_fisico) {
		this.tipo_fisico = tipo_fisico;
	}
	
	

}
