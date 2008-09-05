package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Motel;
import com.marquesexo.web.SendMail;


public class MotelDAO {
	
	
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
			sql = ("DELETE FROM tb_motel " 
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
	public boolean inserir(Motel motel){
		String sql;
		boolean resp = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();

			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_motel " 
					+ "VALUES (0,?,?,?,?,?,?,?,?,?)");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setString(1,motel.getNome());
			stmt.setString(2,motel.getEndereco());
			stmt.setString(3,motel.getTelefone());
			stmt.setString(4,motel.getSite());
			stmt.setString(5,motel.getEmail());
			stmt.setString(6,motel.getTexto());
			stmt.setString(7,motel.getCidade());
			stmt.setString(8,motel.getBairro());
			stmt.setInt(9,motel.getPrioridade());
			
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
	public boolean atualizar(Motel motel){
		String sql;
		boolean resp = false;
		
		try {
			

			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_motel SET " 
					+ "nome=?, "
					+ "endereco=?, "
					+ "telefone=?, "
					+ "site=?, "
					+ "email=?, "
					+ "texto=?, "
					+ "cidade=?, "
					+ "bairro=?, "
					+ "prioridade=? "
					+ "WHERE id=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores			
			stmt.setString(1, motel.getNome());
			stmt.setString(2, motel.getEndereco());
			stmt.setString(3, motel.getTelefone());
			stmt.setString(4, motel.getSite());
			stmt.setString(5, motel.getEmail());
			stmt.setString(6, motel.getTexto());
			stmt.setString(7,motel.getCidade());
			stmt.setString(8,motel.getBairro());
			stmt.setInt(9,motel.getPrioridade());
			stmt.setLong(10, motel.getId());
					
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
	
	
	public List getMoteis(){
		List<Motel> lista = new ArrayList<Motel>();
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT * "
					+ "FROM tb_motel "
					+ "ORDER BY nome");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			rst = stmt.executeQuery();

			while (rst.next()) {
				Motel motel = new Motel();
				motel.setId(rst.getLong("id"));	
				motel.setNome(rst.getString("nome"));
				motel.setEndereco(rst.getString("endereco"));
				motel.setTelefone(rst.getString("telefone"));
				motel.setSite(rst.getString("site"));
				motel.setEmail(rst.getString("email"));
				motel.setTexto(rst.getString("texto"));
				motel.setCidade(rst.getString("cidade"));
				motel.setBairro(rst.getString("bairro"));
				motel.setPrioridade(rst.getInt("prioridade"));
				lista.add(motel);
				
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return lista;
	}
	
	
	public Motel getMotel(long id){
		Motel motel = null;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT * "
					+ "FROM tb_motel "
					+ "WHERE id=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			
			rst = stmt.executeQuery();

			if (rst.next()) {
				motel = new Motel();
				motel.setId(id);	
				motel.setNome(rst.getString("nome"));
				motel.setEndereco(rst.getString("endereco"));
				motel.setTelefone(rst.getString("telefone"));
				motel.setSite(rst.getString("site"));
				motel.setEmail(rst.getString("email"));
				motel.setTexto(rst.getString("texto"));
				motel.setCidade(rst.getString("cidade"));
				motel.setBairro(rst.getString("bairro"));
				motel.setPrioridade(rst.getInt("prioridade"));
			}
			rst.close();				
			stmt.close();
			con.close();
				
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		
		return motel;
	}
	
	

}
