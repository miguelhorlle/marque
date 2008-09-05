package com.marquesexo.bean;


public class ParticipacaoSuruba {
	
	private long id;
	private Suruba suruba;
	private Membro participante;
	private int status;
	
	public ParticipacaoSuruba(){
        
    }
    
    public ParticipacaoSuruba(long id){
        this.id = id;
    }
    
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	
	
	
	public Membro getParticipante() {
		return participante;
	}


	
	public void setParticipante(Membro participante) {
		this.participante = participante;
	}


	
	public Suruba getSuruba() {
		return suruba;
	}


	
	public void setSuruba(Suruba suruba) {
		this.suruba = suruba;
	}


	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
