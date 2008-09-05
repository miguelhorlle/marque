package com.marquesexo.bean;


public class ComentarioSuruba {

	private long id;
	private ParticipacaoSuruba participacao;
	private Suruba suruba;
	private String comentario;
	
	
	
	
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


    public ParticipacaoSuruba getParticipacao() {
        return participacao;
    }


    public void setParticipacao(
            ParticipacaoSuruba participacao) {
        this.participacao = participacao;
    }


    public Suruba getSuruba() {
        return suruba;
    }


    public void setSuruba(
            Suruba suruba) {
        this.suruba = suruba;
    }
	

	
}
