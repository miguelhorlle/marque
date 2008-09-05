package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Interessante;
import com.marquesexo.web.SendMail;


public class InteressanteDAO {
	
	
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
			sql = ("DELETE FROM tb_interessante " 
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
	public boolean inserir(Interessante interessante){
		String sql;
		boolean resp = false;
		
		try {

			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_interessante " 
					+ "VALUES (0,?,?)");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,interessante.getCodigo_sexual_interessado());
			stmt.setLong(2,interessante.getCodigo_sexual_interessante());
			
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
	
	
	public List getInteressantes(long codigo_sexual_interessado){
		List<Interessante> lista = new ArrayList<Interessante>();
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT id,codigo_sexual_interessante "
					+ "FROM tb_interessante "
					+ "WHERE codigo_sexual_interessado=? "
					+ "ORDER BY codigo_sexual_interessante");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual_interessado);
			
			rst = stmt.executeQuery();

			while (rst.next()) {
				Interessante inter = new Interessante();
				inter.setId(rst.getLong("id"));
				inter.setCodigo_sexual_interessante(rst.getLong("codigo_sexual_interessante"));
				inter.setCodigo_sexual_interessado(codigo_sexual_interessado);					
				lista.add(inter);					
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return lista;
	}
	
	
	public Interessante getInteressante(long codigo_sexual_interessado, long codigo_sexual_interessante){
		Interessante inter = null;		
		String sql;
		ResultSet rst;

		
		try {

			sql = ("SELECT id "
					+ "FROM tb_interessante "
					+ "WHERE codigo_sexual_interessado=? "
					+ "AND codigo_sexual_interessante=? ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual_interessado);
			stmt.setLong(2, codigo_sexual_interessante);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				inter = new Interessante();
				inter.setId(rst.getLong("id"));
				inter.setCodigo_sexual_interessado(codigo_sexual_interessado);
				inter.setCodigo_sexual_interessante(codigo_sexual_interessante);		
			}
			
			rst.close();				
			stmt.close();
			con.close();

		} catch (Exception e) {
			trataExcecao(e);
		}
		return inter;
	}
	
	
	public long getIdInteressante(long codigo_sexual_interessado, long codigo_sexual_interessante){
		long id = -1;		
		String sql;
		ResultSet rst;

		
		try {

			sql = ("SELECT id "
					+ "FROM tb_interessante "
					+ "WHERE codigo_sexual_interessado=? "
					+ "AND codigo_sexual_interessante=? ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual_interessado);
			stmt.setLong(2, codigo_sexual_interessante);
			
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
	
	public boolean isInteressante(long codigo_sexual_interessado, long codigo_sexual_interessante){
		boolean resp = false;		
		String sql;
		ResultSet rst;

		
		try {

			sql = ("SELECT id "
					+ "FROM tb_interessante "
					+ "WHERE codigo_sexual_interessado=? "
					+ "AND codigo_sexual_interessante=? ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, codigo_sexual_interessado);
			stmt.setLong(2, codigo_sexual_interessante);
			
			rst = stmt.executeQuery();

			if (rst.next()) {					
				resp = true;

			}
			
			rst.close();				
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		return resp;
	}

}
