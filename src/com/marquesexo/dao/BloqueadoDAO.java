package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Bloqueado;
import com.marquesexo.web.SendMail;

public class BloqueadoDAO {
		
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
			

			// Prepara um statement para exclusão de um registro
			sql = ("DELETE FROM tb_bloqueado " 
					+ "WHERE id = ?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
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
	public boolean inserir(Bloqueado bloqueado){
		String sql;
		boolean resp = false;
		
		try {
							
			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_bloqueado " 
					+ "VALUES (0,?,?)");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,bloqueado.getCodigo_sexual_bloqueante());
			stmt.setLong(2,bloqueado.getCodigo_sexual_bloqueado());
			
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
	
	
	public List getBloqueados(long codigo_sexual_bloqueante){
		List<Bloqueado> lista = new ArrayList<Bloqueado>();
		String sql;
		ResultSet rst;
		
		try {
			
			sql = ("SELECT id,codigo_sexual_bloqueado "
					+ "FROM tb_bloqueado "
					+ "WHERE codigo_sexual_bloqueante=? "
					+ "ORDER BY codigo_sexual_bloqueado");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual_bloqueante);
			
			rst = stmt.executeQuery();

			while (rst.next()) {
				Bloqueado bloq = new Bloqueado();								
				bloq.setId(rst.getLong("id"));
				bloq.setCodigo_sexual_bloqueado(rst.getLong("codigo_sexual_bloqueado"));
				bloq.setCodigo_sexual_bloqueante(codigo_sexual_bloqueante);		
				lista.add(bloq);
				
			}
			rst.close();				
			stmt.close();
			con.close();
				
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return lista;
	}
	
	
	public boolean isBloqueado(long codigo_sexual, long codigo_sexual_consultado){
		String sql;
		ResultSet rst;
		boolean resp = false;
		
		try {

			sql = ("SELECT id "
					+ "FROM tb_bloqueado "
					+ "WHERE codigo_sexual_bloqueante=? "
					+ "AND codigo_sexual_bloqueado=? ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual);
			stmt.setLong(2, codigo_sexual_consultado);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				resp = true;
			}else
				resp = false;
			
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resp;
	}
	
	
	public Bloqueado getBloqueado(long codigo_sexual_bloqueante, long codigo_sexual_bloqueado){
		Bloqueado bloq = null;		
		String sql;
		ResultSet rst;

		
		try {
			

			sql = ("SELECT id "
					+ "FROM tb_bloqueado "
					+ "WHERE codigo_sexual_bloqueante=? "
					+ "AND codigo_sexual_bloqueado=? ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual_bloqueante);
			stmt.setLong(2, codigo_sexual_bloqueado);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				bloq = new Bloqueado();
				bloq.setId(rst.getLong("id"));
				bloq.setCodigo_sexual_bloqueado(codigo_sexual_bloqueado);
				bloq.setCodigo_sexual_bloqueante(codigo_sexual_bloqueante);		
			}
			
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return bloq;
	}
	
	
	public long getIdBloqueado(long codigo_sexual_bloqueante, long codigo_sexual_bloqueado){
		long id = -1;		
		String sql;
		ResultSet rst;

		
		try {
						
			sql = ("SELECT id "
					+ "FROM tb_bloqueado "
					+ "WHERE codigo_sexual_bloqueante=? "
					+ "AND codigo_sexual_bloqueado=? ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual_bloqueante);
			stmt.setLong(2, codigo_sexual_bloqueado);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				id = rst.getLong("id");				
			}
			
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return id;
	}
	
	

}
