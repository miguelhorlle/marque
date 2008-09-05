package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Enquete;
import com.marquesexo.bean.Resposta;
import com.marquesexo.web.SendMail;


public class RespostaDAO {
	
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
				sql = ("DELETE FROM tb_respostas_marque " 
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
	public boolean inserir(Resposta resposta){
		
		String sql;
		boolean resp = false;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para inserção de um registro
				sql = ("INSERT INTO tb_respostas_marque " 
						+ "VALUES (0,?,?)");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores;
				stmt.setLong(1, resposta.getEnquete().getId());	
				stmt.setString(2, resposta.getTexto());

				
				// Executa o statement de inserção
				stmt.executeUpdate();
		
				stmt.close();
				
				resp = true;				
			}

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
	public boolean atualizar(Resposta resposta){
				
		String sql;
		boolean resp = false;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para atualização de um registro
				sql = ("UPDATE tb_respostas_marque SET " 
						+ "id_enquete=?, "
						+ "texto=? "
						+ "WHERE id=?");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores				
				stmt.setLong(1, resposta.getEnquete().getId());	
				stmt.setString(2, resposta.getTexto());
				stmt.setLong(3, resposta.getId());				
				
				// Executa o statement de atualização
				int i = stmt.executeUpdate();
				if(i == 0)
					resp = false;
				else
					resp = true;
				
				stmt.close();			
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return resp;
	}
	
	
	public List getTodos(){
		List respostas = new ArrayList();
		String sql;
		ResultSet rst;
		Resposta resposta = null;
		Enquete enquete = null;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT id, "
						+ "id_enquete, "
						+ "texto "
						+ "FROM tb_respostas_marque "
						+ "ORDER BY texto");
				PreparedStatement stmt = con.prepareStatement(sql);
				rst = stmt.executeQuery();

				while (rst.next()) {
					enquete = new Enquete();
					enquete.setId(rst.getLong(2));
					resposta = new Resposta();
					resposta.setEnquete(enquete);
					resposta.setId(rst.getLong(1));
					resposta.setTexto(rst.getString(3));
					respostas.add(resposta);
				}
				rst.close();				
				stmt.close();
				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return respostas;
	}

	
	public Resposta getResposta(long id){
		String sql;
		ResultSet rst;
		Resposta resposta = null;
		Enquete enquete = null;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT id_enquete, "
						+ "texto "
						+ "FROM tb_respostas_marque "	
						+ "WHERE id=?");
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, id);
				
				rst = stmt.executeQuery();

				if (rst.next()) {
					enquete = new Enquete();
					enquete.setId(rst.getLong(1));
					resposta = new Resposta();
					resposta.setEnquete(enquete);
					resposta.setId(id);
					resposta.setTexto(rst.getString(2));
				}
				rst.close();				
				stmt.close();

			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return resposta;
	}
	
	
	public List getTodosPelaEnquete(long id_enquete){
		List respostas = new ArrayList();
		String sql;
		ResultSet rst;
		Resposta resposta = null;
		Enquete enquete = null;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT id, "
						+ "texto "
						+ "FROM tb_respostas_marque "
						+ "WHERE id_enquete=? "
						+ "ORDER BY texto");
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, id_enquete);
				rst = stmt.executeQuery();

				while (rst.next()) {
					enquete = new Enquete();
					enquete.setId(id_enquete);
					resposta = new Resposta();
					resposta.setEnquete(enquete);
					resposta.setId(rst.getLong(1));
					resposta.setTexto(rst.getString(2));
					respostas.add(resposta);
				}
				rst.close();				
				stmt.close();
				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return respostas;
	}

}
