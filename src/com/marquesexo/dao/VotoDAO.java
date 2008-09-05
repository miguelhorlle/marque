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
import com.marquesexo.bean.Voto;
import com.marquesexo.web.SendMail;


public class VotoDAO {
	
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
		boolean voto = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para exclusão de um registro
				sql = ("DELETE FROM tb_votos_marque " 
						+ "WHERE id = ?");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores
				stmt.setLong(1, id);
				
				// Executa o statement de exclusão
				int deleteCount = stmt.executeUpdate();
				
				stmt.close();
				
				if(deleteCount > 0)
					voto = true;				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return voto;	
	}
	
	
	/**
	 * Este método inclui um registro da tabela
	 * @param aviso
	 * @return a confirmação da operação
	 */
	public boolean inserir(Voto voto){
		
		String sql;
		boolean resp = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para inserção de um registro
				sql = ("INSERT INTO tb_votos_marque " 
						+ "VALUES (0,?,?)");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores;
				stmt.setLong(1, voto.getEnquete().getId());	
				stmt.setLong(2, voto.getResposta().getId());

				
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
	public boolean atualizar(Voto voto){
				
		String sql;
		boolean resp = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				
				// Prepara um statement para atualização de um registro
				sql = ("UPDATE tb_votos_marque SET " 
						+ "id_enquete=?, "
						+ "id_resposta=? "
						+ "WHERE id=?");
				
				// Cria um statement
				PreparedStatement stmt = con.prepareStatement(sql);
				
			    // Seta os valores				
				stmt.setLong(1, voto.getEnquete().getId());	
				stmt.setLong(2, voto.getResposta().getId());
				stmt.setLong(3, voto.getId());				
				
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
		List votos = new ArrayList();
		String sql;
		ResultSet rst;
		Voto voto = null;
		Enquete enquete = null;
		Resposta resposta = null;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT id, "
						+ "id_enquete, "
						+ "id_resposta "
						+ "FROM tb_votos_marque "
						+ "ORDER BY texto");
				PreparedStatement stmt = con.prepareStatement(sql);
				rst = stmt.executeQuery();

				while (rst.next()) {
					enquete = new Enquete();
					enquete.setId(rst.getLong(2));
					resposta = new Resposta();
					resposta.setId(rst.getLong(3));
					voto = new Voto();
					voto.setEnquete(enquete);
					voto.setResposta(resposta);
					voto.setId(rst.getLong(1));					
					votos.add(voto);
				}
				rst.close();				
				stmt.close();
				
			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return votos;
	}
	
	public Voto getVoto(long id){
		String sql;
		ResultSet rst;
		Voto voto = null;
		Enquete enquete = null;
		Resposta resposta = null;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT id_enquete, "
						+ "id_resposta "
						+ "FROM tb_votos_marque "	
						+ "WHERE id=?");
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, id);
				
				rst = stmt.executeQuery();

				if (rst.next()) {
					enquete = new Enquete();
					enquete.setId(rst.getLong(1));
					resposta = new Resposta();
					resposta.setId(rst.getLong(2));
					voto = new Voto();
					voto.setEnquete(enquete);
					voto.setResposta(resposta);
					voto.setId(id);
				}
				rst.close();				
				stmt.close();

			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return voto;
	}
	
	
	
	public int getQtdVotosEnquete(long id_enquete){
		String sql;
		ResultSet rst;
		int qtd = 0;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT COUNT(*) "
						+ "FROM tb_votos_marque "
						+ "WHERE id_enquete=? ");
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, id_enquete);
				
				rst = stmt.executeQuery();

				if (rst.next()) {
					qtd = rst.getInt(1);
				}else
					qtd = 0;
				
				rst.close();				
				stmt.close();
			}

		} catch (Exception e) {
			trataExcecao(e);
		}
		return qtd;
	}
	
	
	public int getQtdVotosResposta(long id_resposta){
		String sql;
		ResultSet rst;
		int qtd = 0;
		
		try {
			Connection con = ConnectionPool.getInstance().getConnection();
			if (con != null) {
				sql = ("SELECT COUNT(*) "
						+ "FROM tb_votos_marque "
						+ "WHERE id_resposta=? ");
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setLong(1, id_resposta);
				
				rst = stmt.executeQuery();

				if (rst.next()) {
					qtd = rst.getInt(1);
				}else
					qtd = 0;
				
				rst.close();				
				stmt.close();
			}

		} catch (Exception e) {
			trataExcecao(e);
		}

		return qtd;
	}

}
