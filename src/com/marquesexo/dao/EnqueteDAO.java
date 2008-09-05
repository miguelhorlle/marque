package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Enquete;
import com.marquesexo.web.SendMail;


public class EnqueteDAO {
	
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
		boolean resposta = false;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para exclusão de um registro
				sql = ("DELETE FROM tb_enquetes_gls " 
						+ "WHERE id = ?");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores
				stmt.setLong(1, id);
				
				// Executa o statement de exclusão
				int deleteCount = stmt.executeUpdate();
				
				stmt.close();
				
				if(deleteCount > 0)
					resposta = true;				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resposta;	
	}	
	
	/**
	 * Este método inclui um registro da tabela
	 * @param aviso
	 * @return a confirmação da operação
	 */
	public boolean inserir(Enquete enquete){
		
		String sql;
		boolean resposta = false;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para inserção de um registro
				sql = ("INSERT INTO tb_enquetes_marque " 
						+ "VALUES (0,?)");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores;
				stmt.setString(1, enquete.getPergunta());

				
				// Executa o statement de inserção
				stmt.executeUpdate();
		
				stmt.close();
				
				resposta = true;				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resposta;
		
	}
	
	
	
	
	
	/**
	 * Este método atualiza um registro da tabela
	 * @param aviso
	 * @return a confirmação da operação
	 */
	public boolean atualizar(Enquete enquete){
				
		String sql;
		boolean resposta = false;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para atualização de um registro
				sql = ("UPDATE tb_enquetes_marque SET " 
						+ "pergunta=? "
						+ "WHERE id=?");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores				
				stmt.setString(1, enquete.getPergunta());
				stmt.setLong(2, enquete.getId());				
				
				// Executa o statement de atualização
				int i = stmt.executeUpdate();
				if(i == 0)
					resposta = false;
				else
					resposta = true;
				
				stmt.close();			
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resposta;
	}
	
	
	public List getTodos(){
		List enquetes = new ArrayList();
		String sql;
		ResultSet rst;
		Enquete enquete = null;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT id, "
						+ "pergunta "
						+ "FROM tb_enquetes_marque "
						+ "ORDER BY pergunta");
				PreparedStatement stmt = con.prepareStatement(sql);
				rst = stmt.executeQuery();

				while (rst.next()) {
					enquete = new Enquete();
					enquete.setId(rst.getLong(1));
					enquete.setPergunta(rst.getString(2));
					enquetes.add(enquete);
				}
				rst.close();				
				stmt.close();
				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return enquetes;
	}

	
	public Enquete getEnquete(long id){
		String sql;
		ResultSet rst;
		Enquete enquete = null;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT pergunta "
						+ "FROM tb_enquetes_marque "
						+ "WHERE id=?");
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, id);
				
				rst = stmt.executeQuery();

				if (rst.next()) {
					enquete = new Enquete();
					enquete.setId(id);
					enquete.setPergunta(rst.getString(1));
				}
				rst.close();				
				stmt.close();

			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return enquete;
	}

}
