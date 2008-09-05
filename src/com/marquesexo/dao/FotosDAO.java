package com.marquesexo.dao;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.marquesexo.bean.Fotos;
import com.marquesexo.web.SendMail;


public class FotosDAO {
	
	
	private void trataExcecao(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String erro = sw.toString();
		SendMail.sendMail("MarqueSexo - Erro", erro , "rafaelcl@gmail.com");
	}
	
	
	/**
	 * Este método exclui um registro da tabela
	 * @param id
	 * @return a confirmação da operação
	 */
	public boolean excluir(long id){
		String sql;
		boolean resp = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
				
			// Prepara um statement para exclusão de um registro
			sql = ("DELETE FROM tb_fotos " 
					+ "WHERE id = ?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores
			stmt.setLong(1, id);
			
			// Executa o statement de exclusão
			int deleteCount = stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
			if(deleteCount > 0)
				resp = true;				
		

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resp;
	}
	
	/**
	 * Este método inclui um registro da tabela
	 * @param aviso
	 * @return a confirmação da operação
	 */
	public boolean inserir(Fotos fotos){
		String sql;
		boolean resp = false;
		
		try {

			Connection con = ConnectionPool.getInstance().getConnection();
				
			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_fotos " 
					+ "VALUES (?,?,?,?,?)");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,fotos.getCodigo_sexual());
			
			if(fotos.getFoto_principal() == null)
				stmt.setBinaryStream(2, fotos.getFoto_principal(), 0);
			else
				stmt.setBinaryStream(2, fotos.getFoto_principal(), fotos.getFoto_principal().available());
			
			if(fotos.getFoto1() == null)
				stmt.setBinaryStream(3, fotos.getFoto1(), 0);
			else					
				stmt.setBinaryStream(3, fotos.getFoto1(), fotos.getFoto1().available());
			
			if(fotos.getFoto2() == null)
				stmt.setBinaryStream(4, fotos.getFoto2(), 0);
			else
				stmt.setBinaryStream(4, fotos.getFoto2(), fotos.getFoto2().available());
			
			if(fotos.getFoto3() == null)
				stmt.setBinaryStream(5, fotos.getFoto3(), 0);
			else					
				stmt.setBinaryStream(5, fotos.getFoto3(), fotos.getFoto3().available());
			
			// Executa o statement de inserção
			stmt.executeUpdate();
	
			stmt.close();
			con.close();
			
			resp = true;				
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resp;
	}
	
	
	/**
	 * Este método atualiza um registro da tabela
	 * @param aviso
	 * @return a confirmação da operação
	 */
	public boolean atualizarFoto(long codigo_sexual, int nro_foto, InputStream foto){
		
		String sql = "";
		boolean resp = false;
		
		try {
				
			// Prepara um statement para atualização de um registro

			sql = ("UPDATE tb_fotos SET " 
					+ "foto" + nro_foto + "=? "
					+ "WHERE codigo_sexual=?");			
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			if(foto == null)
				stmt.setBinaryStream(1,foto,0);
			else
				stmt.setBinaryStream(1,foto,foto.available());
			
			stmt.setLong(2,codigo_sexual);
			
			// Executa o statement de atualização
			int i = stmt.executeUpdate();
			if(i == 0)
				resp = false;
			else
				resp = true;
			
			stmt.close();		
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resp;
	}	
		
	/**
	 * Este método atualiza um registro da tabela
	 * @param aviso
	 * @return a confirmação da operação
	 */
	public boolean atualizarFotoPrincipal(long codigo_sexual,  InputStream foto){
		
		String sql = "";
		boolean resp = false;
		
		try {
				
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_fotos SET " 
						+ "foto_principal=? "
						+ "WHERE codigo_sexual=?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			if(foto == null)
				stmt.setBinaryStream(1,foto,0);
			else
				stmt.setBinaryStream(1,foto,foto.available());
			
			stmt.setLong(2,codigo_sexual);
			
			// Executa o statement de atualização
			int i = stmt.executeUpdate();
			if(i == 0)
				resp = false;
			else
				resp = true;
			
			stmt.close();			
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resp;
	}
	
	
	public Fotos getFotos(long codigo_sexual){
		
		String sql = "";
		ResultSet rst;
		Fotos fotos = null;
			
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
	
			// Prepara um statement para atualização de um registro
			sql = ("SELECT * " 
						+ "FROM tb_fotos "
						+ "WHERE codigo_sexual=?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,codigo_sexual);
			
			rst = stmt.executeQuery();

			if(rst.next()) {
				fotos = new Fotos();
				fotos.setCodigo_sexual(codigo_sexual);
				fotos.setFoto_principal(rst.getBinaryStream("foto_principal"));
				fotos.setFoto1(rst.getBinaryStream("foto1"));
				fotos.setFoto2(rst.getBinaryStream("foto2"));
				fotos.setFoto3(rst.getBinaryStream("foto3"));
			}
				
			
			stmt.close();			
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return fotos;
	}
    
    public int getNroFotoVazia(long codigo_sexual){
		
		String sql = "";
		ResultSet rst;
		Fotos fotos = null;
		int nro = -1;
			
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
	
			// Prepara um statement para atualização de um registro
			sql = ("SELECT * " 
						+ "FROM tb_fotos "
						+ "WHERE codigo_sexual=?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,codigo_sexual);
			
			rst = stmt.executeQuery();

			if(rst.next()) {
				fotos = new Fotos();
				fotos.setCodigo_sexual(codigo_sexual);
				fotos.setFoto_principal(rst.getBinaryStream("foto_principal"));
				fotos.setFoto1(rst.getBinaryStream("foto1"));
				fotos.setFoto2(rst.getBinaryStream("foto2"));
				fotos.setFoto3(rst.getBinaryStream("foto3"));
			}
			
			if(fotos.getFoto3() == null) {
				nro = 3;
			}			
			if(fotos.getFoto2() == null) {
				nro = 2;
			}
			if(fotos.getFoto1() == null) {
				nro = 1;
			}							
			
			stmt.close();			
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return nro;
	}
	
	

	public InputStream getFotoPrincipal(long codigo_sexual){
		
		String sql = "";
		ResultSet rst;
		InputStream foto = null;
			
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Prepara um statement para atualização de um registro
			sql = ("SELECT foto_principal " 
						+ "FROM tb_fotos "
						+ "WHERE codigo_sexual=?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,codigo_sexual);
			
			rst = stmt.executeQuery();

			if(rst.next()) {
				foto = (rst.getBinaryStream("foto_principal"));					
			}
				
			
			stmt.close();		
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return foto;
	}
	
	
	public InputStream getFoto(int nro, long codigo_sexual){
		
		String sql = "";
		ResultSet rst;
		InputStream foto = null;
			
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();

			// Prepara um statement para atualização de um registro
			sql = ("SELECT foto" + nro + " "
						+ "FROM tb_fotos "
						+ "WHERE codigo_sexual=?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,codigo_sexual);
			
			rst = stmt.executeQuery();

			if(rst.next()) {
				foto = (rst.getBinaryStream("foto" + nro));					
			}
				
			
			stmt.close();		
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return foto;
	}

}
