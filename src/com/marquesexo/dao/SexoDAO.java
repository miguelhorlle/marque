package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Membro;
import com.marquesexo.bean.Sexo;
import com.marquesexo.web.SendMail;


public class SexoDAO {
	
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
			sql = ("DELETE FROM tb_sexo " 
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
	public boolean inserir(Sexo sexo){
		
		String sql;
		boolean resp = false;
		
		try {

			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_sexo " 
					+ "VALUES (0,?,?,?,?,?)");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);

		    // Seta os valores;
			stmt.setLong(1,sexo.getPegador().getCodigo_sexual());
			stmt.setLong(2,sexo.getPegado().getCodigo_sexual());
			stmt.setInt(3,sexo.getStatus());
			stmt.setString(4,sexo.getComentario_pegador());
			stmt.setString(5,sexo.getComentario_pegado());
						
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
	public boolean atualizar(Sexo sexo){
		
		String sql;
		boolean resp = false;
		
		try {
			

			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_sexo SET " 
					+ "cod_pegador=?, "
					+ "cod_pegado=?, "
					+ "status=?, "
					+ "comentario_pegador=?, "
					+ "comentario_pegado=? "					
					+ "WHERE id=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setLong(1,sexo.getPegador().getCodigo_sexual());
			stmt.setLong(2,sexo.getPegado().getCodigo_sexual());
			stmt.setInt(3,sexo.getStatus());
			stmt.setString(4,sexo.getComentario_pegador());
			stmt.setString(5,sexo.getComentario_pegado());
			stmt.setLong(6,sexo.getId());
			
			
			
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
	public boolean atualizarStatus(long id, int status){
		
		String sql;
		boolean resp = false;
		
		try {
			
			// 0 = aguardando
			// 1 = confirmado			
				
			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_sexo SET " 
					+ "status=? "
					+ "WHERE id=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setInt(1,status);
			stmt.setLong(2,id);
			
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
	
	
	public Sexo getSexo(long id){
		
		Sexo sexo = null;
		Membro pegador = null;
		Membro pegado = null;
		String sql;
		ResultSet rst;
		
		try {
			
		
			sql = ("SELECT * "
					+ "FROM tb_sexo "
					+ "WHERE id=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,id);
			rst = stmt.executeQuery();

			if(rst.next()) {
				sexo = new Sexo();
				sexo.setId(id);
				pegador = new Membro(rst.getLong("cod_pegador"));
				pegado = new Membro(rst.getLong("cod_pegado"));
				sexo.setPegador(pegador);
				sexo.setPegado(pegado);
				sexo.setStatus(rst.getInt("status"));
				sexo.setComentario_pegador(rst.getString("comentario_pegador"));
				sexo.setComentario_pegado(rst.getString("comentario_pegado"));
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return sexo;
		
	}
	
    public Sexo getSexo(long cod_pegador, long cod_pegado){

		Sexo sexo = null;
		Membro pegador = null;
		Membro pegado = null;
		String sql;
		ResultSet rst;
		
		try {
			

			sql = ("SELECT * "
					+ "FROM tb_sexo "
					+ "WHERE (cod_pegador=? "
					+ "AND cod_pegado=?) "
					+ "OR (cod_pegador=? "
					+ "AND cod_pegado=?) ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1, cod_pegador);
			stmt.setLong(2, cod_pegado);
			stmt.setLong(3, cod_pegado);
			stmt.setLong(4, cod_pegador);
			
			rst = stmt.executeQuery();

			if(rst.next()) {
				sexo = new Sexo();
				sexo.setId(rst.getLong("id"));
				pegador = new Membro(rst.getLong("cod_pegador"));
				pegado = new Membro(rst.getLong("cod_pegado"));
				sexo.setPegador(pegador);
				sexo.setPegado(pegado);
				sexo.setStatus(rst.getInt("status"));
				sexo.setComentario_pegador(rst.getString("comentario_pegador"));
				sexo.setComentario_pegado(rst.getString("comentario_pegado"));
				
			}
			rst.close();				
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		return sexo;
		
	}
	
	
	public List getSexosConfirmados(long cod){
		
		ArrayList<Sexo> lista = new ArrayList<Sexo>();
		Sexo sexo = null;
		Membro pegador = null;
		Membro pegado = null;
		String sql;
		ResultSet rst;
		
		try {
			
			
			sql = ("SELECT * "
					+ "FROM tb_sexo "
					+ "WHERE (cod_pegador=? "
					+ "OR cod_pegado=?) "
					+ "AND status=1 ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1,cod);
			stmt.setLong(2,cod);
			
			rst = stmt.executeQuery();

			while(rst.next()) {
				sexo = new Sexo();
				sexo.setId(rst.getLong("id"));
				pegador = new Membro(rst.getLong("cod_pegador"));
				pegado = new Membro(rst.getLong("cod_pegado"));
				sexo.setPegador(pegador);
				sexo.setPegado(pegado);
				sexo.setStatus(rst.getInt("status"));
				sexo.setComentario_pegador(rst.getString("comentario_pegador"));
				sexo.setComentario_pegado(rst.getString("comentario_pegado"));
				lista.add(sexo);
				
			}
			rst.close();				
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		return lista;
		
	}
	
	
    public List getSexosPegadorAguardando(long cod_pegador){
		
		ArrayList<Sexo> lista = new ArrayList<Sexo>();
		Sexo sexo = null;
		Membro pegador = null;
		Membro pegado = null;
		String sql;
		ResultSet rst;
		
		try {
			
			
			sql = ("SELECT * "
					+ "FROM tb_sexo "
					+ "WHERE cod_pegador=? "
					+ "AND status=0 ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1,cod_pegador);
			
			rst = stmt.executeQuery();

			while(rst.next()) {
				sexo = new Sexo();
				sexo.setId(rst.getLong("id"));
				pegador = new Membro(rst.getLong("cod_pegador"));
				pegado = new Membro(rst.getLong("cod_pegado"));
				sexo.setPegador(pegador);
				sexo.setPegado(pegado);
				sexo.setStatus(rst.getInt("status"));
				sexo.setComentario_pegador(rst.getString("comentario_pegador"));
				sexo.setComentario_pegado(rst.getString("comentario_pegado"));
				lista.add(sexo);
				
			}
			rst.close();				
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		return lista;
		
	}
    
	
    public List getSexosPegadoAguardando(long cod_pegado){
		
		ArrayList<Sexo> lista = new ArrayList<Sexo>();
		Sexo sexo = null;
		Membro pegador = null;
		Membro pegado = null;
		String sql;
		ResultSet rst;
		
		try {
			
			
			sql = ("SELECT * "
					+ "FROM tb_sexo "
					+ "WHERE cod_pegado=? "
					+ "AND status=0 ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1,cod_pegado);
			
			rst = stmt.executeQuery();

			while(rst.next()) {
				sexo = new Sexo();
				sexo.setId(rst.getLong("id"));
				pegador = new Membro(rst.getLong("cod_pegador"));
				pegado = new Membro(rst.getLong("cod_pegado"));
				sexo.setPegador(pegador);
				sexo.setPegado(pegado);
				sexo.setStatus(rst.getInt("status"));
				sexo.setComentario_pegador(rst.getString("comentario_pegador"));
				sexo.setComentario_pegado(rst.getString("comentario_pegado"));
				lista.add(sexo);
				
			}
			rst.close();				
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		return lista;
		
	}
	
	
	public boolean hasSexo(long cod_pegador, long cod_pegado){
		boolean resp = false;		
		String sql;
		ResultSet rst;

		
		try {

			sql = ("SELECT id "
					+ "FROM tb_sexo "
					+ "WHERE (cod_pegador=? "
					+ "AND cod_pegado=?) "
					+ "OR (cod_pegador=? "
					+ "AND cod_pegado=?) ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, cod_pegador);
			stmt.setLong(2, cod_pegado);
			stmt.setLong(3, cod_pegado);
			stmt.setLong(4, cod_pegador);
			
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
	
	public int getQtdSexosConfirmados(long cod){
		
		String sql;
		ResultSet rst;
		int qtd = 0;
		
		try {
						
			sql = ("SELECT COUNT(*) AS qtd "
					+ "FROM tb_sexo "
					+ "WHERE (cod_pegador=? "
					+ "OR cod_pegado=?) "
					+ "AND status=1 ");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setLong(1,cod);
			stmt.setLong(2,cod);
			
			rst = stmt.executeQuery();

			if(rst.next()) {
				qtd = (rst.getInt("qtd"));
			}
			rst.close();				
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		return qtd;
		
	}

}
