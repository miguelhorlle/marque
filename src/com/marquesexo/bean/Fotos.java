package com.marquesexo.bean;

import java.io.InputStream;
import java.io.Serializable;


public class Fotos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long codigo_sexual;
	private InputStream foto_principal;
	private InputStream foto1;
	private InputStream foto2;
	private InputStream foto3;
	
	
	

	public long getCodigo_sexual() {
		return codigo_sexual;
	}

	public void setCodigo_sexual(long codigo_sexual) {
		this.codigo_sexual = codigo_sexual;
	}

	public InputStream getFoto_principal() {
		return foto_principal;
	}
	
	public void setFoto_principal(InputStream foto_principal) {
		this.foto_principal = foto_principal;
	}
	
	public InputStream getFoto1() {
		return foto1;
	}
	
	public void setFoto1(InputStream foto1) {
		this.foto1 = foto1;
	}
	
	public InputStream getFoto2() {
		return foto2;
	}
	
	public void setFoto2(InputStream foto2) {
		this.foto2 = foto2;
	}
	
	public InputStream getFoto3() {
		return foto3;
	}
	
	public void setFoto3(InputStream foto3) {
		this.foto3 = foto3;
	}
	
	

}
