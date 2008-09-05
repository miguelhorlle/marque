package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.marquesexo.bean.Webcam;
import com.marquesexo.web.SendMail;


public class WebcamDAO {
	
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
			sql = ("DELETE FROM tb_webcam " 
					+ "WHERE id_membro = ?");
			
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
	public boolean inserir(Webcam cam){
		String sql;
		boolean resp = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();

			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_webcam " 
					+ "VALUES (?,?,?,?,?,?)");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,cam.getId_membro());
			stmt.setString(2,cam.getIp());
			stmt.setString(3,cam.getPorta());
			stmt.setString(4,cam.getUsername());
			stmt.setString(5,cam.getSenha());
			stmt.setInt(6,0);
			
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
	public boolean atualizar(Webcam cam){
		String sql;
		boolean resp = false;
		
		try {
			

			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_webcam SET " 
					+ "ip=?, "
					+ "porta=?, "
					+ "username=?, "
					+ "senha=? "
					+ "WHERE id_membro=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores			
			stmt.setString(1,cam.getIp());
			stmt.setString(2,cam.getPorta());
			stmt.setString(3,cam.getUsername());
			stmt.setString(4,cam.getSenha());
			stmt.setLong(5, cam.getId_membro());
					
			// 	Executa o statement de atualização
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
	
	
	
	public boolean habilitar(long id){
		String sql;
		boolean resp = false;
		
		try {
			

			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_webcam SET " 
					+ "habilitado=1 "
					+ "WHERE id_membro=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores			
			stmt.setLong(1, id);
					
			// 	Executa o statement de atualização
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
	
	
	public boolean desabilitar(long id){
		String sql;
		boolean resp = false;
		
		try {
			

			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_webcam SET " 
					+ "habilitado=0 "
					+ "WHERE id_membro=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores			
			stmt.setLong(1, id);
					
			// 	Executa o statement de atualização
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
	
	
	
	public Webcam getWebcam(long id){
		Webcam cam = null;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT * "
					+ "FROM tb_webcam "
					+ "WHERE id_membro=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				cam = new Webcam();
				cam.setId_membro(id);	
				cam.setIp(rst.getString("ip"));
				cam.setPorta(rst.getString("porta"));
				cam.setUsername(rst.getString("username"));
				cam.setSenha(rst.getString("senha"));
				cam.setHabilitado(rst.getInt("habilitado"));
			}
			rst.close();				
			stmt.close();
			con.close();
				
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return cam;
	}
	
	
	public boolean hasWebcam(long id){
		boolean result = false;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT ip "
					+ "FROM tb_webcam "
					+ "WHERE id_membro=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				result = true;
			}
			rst.close();				
			stmt.close();
			con.close();
				
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return result;
	}
	
	
	public boolean isHabilitado(long id){
		boolean habilitado = false;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT habilitado "
					+ "FROM tb_webcam "
					+ "WHERE id_membro=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				if(rst.getInt("habilitado") == 1)
					habilitado = true;
				else
					habilitado = false;
			}
			rst.close();				
			stmt.close();
			con.close();
				
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return habilitado;
	}
	
	

}
