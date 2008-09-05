package com.marquesexo.bean;

import java.io.Serializable;
import java.sql.Timestamp;


public class Suruba implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Membro organizador;
    private String titulo;
	private Timestamp data;
	private String hora;
    private String minuto;
	private String local;
    private String cidade;
    private String estado;
	private String obs;
	private int status;
	
	public Suruba(){
        
    }
    
    public Suruba(long id){
        this.id = id;        
    }
		
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}
	
    
    
	
	public String getTitulo() {
        return titulo;
    }


    public void setTitulo(
            String titulo) {
        this.titulo = titulo;
    }


    public String getCidade() {
        return cidade;
    }


    public void setCidade(
            String cidade) {
        this.cidade = cidade;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(
            String estado) {
        this.estado = estado;
    }


    public Membro getOrganizador() {
		return organizador;
	}


	
	public String getMinuto() {
        return minuto;
    }

    public void setMinuto(
            String minuto) {
        this.minuto = minuto;
    }

    public void setOrganizador(Membro organizador) {
		this.organizador = organizador;
	}


	public Timestamp getData() {
		return data;
	}
	
	public void setData(Timestamp data) {
		this.data = data;
	}
	
	public String getHora() {
		return hora;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public String getObs() {
		return obs;
	}
	
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
