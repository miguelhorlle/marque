package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.marquesexo.bean.Membro;
import com.marquesexo.web.SendMail;


public class MembroDAO {
		
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
	public boolean excluir(long codigo_sexual){
		String sql;
		boolean resp = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
				
			// Prepara um statement para exclusão de um registro
			sql = ("DELETE FROM tb_membro " 
					+ "WHERE codigo_sexual = ?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores
			stmt.setLong(1, codigo_sexual);
			
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
	public boolean inserir(Membro membro){
		
		String sql;
		boolean resp = false;
		
		try {

			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_membro " 
					+ "VALUES (0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);

		    // Seta os valores;
			stmt.setString(1,membro.getNome());
			stmt.setString(2,membro.getEmail());
			stmt.setString(3,membro.getSenha());
			stmt.setInt(4,membro.getSituacao());
			stmt.setInt(5,membro.getFaz_sexo_com());
			stmt.setInt(6,membro.getIdade());
			stmt.setString(7,membro.getCidade());
			stmt.setString(8,membro.getEstado());
			stmt.setInt(9,membro.getSexo());
			stmt.setFloat(10,membro.getPeso());
			stmt.setFloat(11,membro.getAltura());
			stmt.setInt(12,membro.getTipo_fisico());
			stmt.setInt(13,membro.getTom_pele());
			stmt.setInt(14,membro.getPermissao());
			stmt.setString(15,membro.getApelido());
			stmt.setTimestamp(16, new Timestamp(new Date().getTime()));
			stmt.setTimestamp(17, membro.getFim());
			stmt.setInt(18,membro.getEstado_civil());
			stmt.setString(19,membro.getTexto());
			stmt.setInt(20,membro.getDiscreto());

			
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
	public boolean atualizar(Membro membro){
		
		String sql;
		boolean resp = false;
		
		try {
			

			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_membro SET " 
					+ "nome=?, "
					+ "email=?, "
					+ "senha=?, "
					+ "situacao=?, "
					+ "faz_sexo_com=?, "
					+ "idade=?, "
					+ "cidade=?, "
					+ "estado=?, "
					+ "sexo=?, "
					+ "peso=?, "
					+ "altura=?, "
					+ "tipo_fisico=?, "
					+ "tom_pele=?, "
					+ "permissao=?, "
					+ "apelido=?, "
					+ "estado_civil=?, "
					+ "texto=?, "
					+ "discreto=? "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setString(1,membro.getNome());
			stmt.setString(2,membro.getEmail());
			stmt.setString(3,membro.getSenha());
			stmt.setInt(4,membro.getSituacao());
			stmt.setInt(5,membro.getFaz_sexo_com());
			stmt.setInt(6,membro.getIdade());
			stmt.setString(7,membro.getCidade());
			stmt.setString(8,membro.getEstado());
			stmt.setInt(9,membro.getSexo());
			stmt.setFloat(10,membro.getPeso());
			stmt.setFloat(11,membro.getAltura());
			stmt.setInt(12,membro.getTipo_fisico());
			stmt.setInt(13,membro.getTom_pele());
			stmt.setInt(14,membro.getPermissao());
			stmt.setString(15,membro.getApelido());
			stmt.setInt(16,membro.getEstado_civil());
			stmt.setString(17,membro.getTexto());
			stmt.setInt(18,membro.getDiscreto());
			stmt.setLong(19,membro.getCodigo_sexual());
			
			
			
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
	public boolean atualizarSituacao(long codigo_sexual, int situacao){
		
		String sql;
		boolean resp = false;
		
		try {
			
				
			// Prepara um statement para atualização de um registro
			sql = ("UPDATE tb_membro SET " 
					+ "situacao=? "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores;
			stmt.setInt(1,situacao);
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
	
	
	public Membro getMembroSimples(long codigo_sexual){
		
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {
			
		
			sql = ("SELECT email, apelido, faz_sexo_com, idade, cidade, estado, sexo, situacao, estado_civil, texto, discreto "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(codigo_sexual);
				membro.setEmail(rst.getString("email"));
				membro.setApelido(rst.getString("apelido"));
				membro.setFaz_sexo_com(rst.getInt("faz_sexo_com"));
				membro.setIdade(rst.getInt("idade"));
				membro.setCidade(rst.getString("cidade"));
				membro.setEstado(rst.getString("estado").toUpperCase());
				membro.setSexo(rst.getInt("sexo"));
				membro.setSituacao(rst.getInt("situacao"));
				membro.setEstado_civil(rst.getInt("estado_civil"));
				membro.setTexto(rst.getString("texto"));
				membro.setDiscreto(rst.getInt("discreto"));
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return membro;
		
	}
	
	
	public Membro getMembroMaisSimples(long codigo_sexual){
		
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {
			
		
			sql = ("SELECT apelido, sexo, faz_sexo_com, situacao, estado_civil, texto, discreto, nome "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(codigo_sexual);
				membro.setApelido(rst.getString("apelido"));
				membro.setFaz_sexo_com(rst.getInt("faz_sexo_com"));
				membro.setSexo(rst.getInt("sexo"));
				membro.setSituacao(rst.getInt("situacao"));
				membro.setEstado_civil(rst.getInt("estado_civil"));
				membro.setTexto(rst.getString("texto"));
				membro.setDiscreto(rst.getInt("discreto"));
				membro.setNome(rst.getString("nome"));
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return membro;
		
	}
	
	
    public Membro getMembroMaisSimplesAinda(long codigo_sexual){
		
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {
			
		
			sql = ("SELECT nome, email, apelido "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(codigo_sexual);
				membro.setNome(rst.getString("nome"));
				membro.setEmail(rst.getString("email"));
				membro.setApelido(rst.getString("apelido"));
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return membro;
		
	}
	
		
	
	public Membro getMembro(long codigo_sexual){
		
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT * "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(codigo_sexual);
				membro.setNome(rst.getString("nome"));
				membro.setApelido(rst.getString("apelido"));
				membro.setEmail(rst.getString("email"));
				membro.setSenha(rst.getString("senha"));
				membro.setSituacao(rst.getInt("situacao"));
				membro.setFaz_sexo_com(rst.getInt("faz_sexo_com"));
				membro.setIdade(rst.getInt("idade"));
				membro.setCidade(rst.getString("cidade"));
				membro.setEstado(rst.getString("estado").toUpperCase());
				membro.setSexo(rst.getInt("sexo"));
				membro.setPeso(rst.getFloat("peso"));
				membro.setAltura(rst.getFloat("altura"));
				membro.setTipo_fisico(rst.getInt("tipo_fisico"));
				membro.setTom_pele(rst.getInt("tom_pele"));
				membro.setPermissao(rst.getInt("permissao"));
				membro.setData(rst.getTimestamp("data"));
				membro.setFim(rst.getTimestamp("fim"));
				membro.setEstado_civil(rst.getInt("estado_civil"));
				membro.setTexto(rst.getString("texto"));
				membro.setDiscreto(rst.getInt("discreto"));
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return membro;
		
	}
	
	
	public Membro getMembro(String email){
		
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT * "
					+ "FROM tb_membro "
					+ "WHERE email=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,email);
			rst = stmt.executeQuery();

			if(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(rst.getLong("codigo_sexual"));
				membro.setNome(rst.getString("nome"));
				membro.setApelido(rst.getString("apelido"));
				membro.setEmail(email);
				membro.setSenha(rst.getString("senha"));
				membro.setSituacao(rst.getInt("situacao"));
				membro.setFaz_sexo_com(rst.getInt("faz_sexo_com"));
				membro.setIdade(rst.getInt("idade"));
				membro.setCidade(rst.getString("cidade"));
				membro.setEstado(rst.getString("estado").toUpperCase());
				membro.setSexo(rst.getInt("sexo"));
				membro.setPeso(rst.getFloat("peso"));
				membro.setAltura(rst.getFloat("altura"));
				membro.setTipo_fisico(rst.getInt("tipo_fisico"));
				membro.setTom_pele(rst.getInt("tom_pele"));
				membro.setPermissao(rst.getInt("permissao"));
				membro.setData(rst.getTimestamp("data"));
				membro.setFim(rst.getTimestamp("fim"));
				membro.setEstado_civil(rst.getInt("estado_civil"));
				membro.setTexto(rst.getString("texto"));
				membro.setDiscreto(rst.getInt("discreto"));
			}
			rst.close();				
			stmt.close();
			con.close();			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return membro;
		
	}
	
	public Membro getPerfilMembro(long codigo_sexual){
		
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT * "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(codigo_sexual);
				membro.setApelido(rst.getString("apelido"));
				membro.setSituacao(rst.getInt("situacao"));
				membro.setFaz_sexo_com(rst.getInt("faz_sexo_com"));
				membro.setIdade(rst.getInt("idade"));
				membro.setCidade(rst.getString("cidade"));
				membro.setEstado(rst.getString("estado").toUpperCase());
				membro.setSexo(rst.getInt("sexo"));
				membro.setPeso(rst.getFloat("peso"));
				membro.setAltura(rst.getFloat("altura"));
				membro.setTipo_fisico(rst.getInt("tipo_fisico"));
				membro.setTom_pele(rst.getInt("tom_pele"));
				membro.setEstado_civil(rst.getInt("estado_civil"));
				membro.setTexto(rst.getString("texto"));
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return membro;
		
	}

	public String getNomeMembro(long codigo_sexual){
		
		String nome = "";
		String sql;
		ResultSet rst;
		
		try {
			
			
			sql = ("SELECT nome "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {					
				nome = rst.getString("nome");								
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return nome;
		
	}
	
    public String getEmailMembro(long codigo_sexual){
		
		String email = "";
		String sql;
		ResultSet rst;
		
		try {
			
			
			sql = ("SELECT email "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {					
				email = rst.getString("email");								
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return email;
		
	}

	public String getApelidoMembro(long codigo_sexual){
		
		String apelido = "";
		String sql;
		ResultSet rst;
		
		try {
			
			sql = ("SELECT apelido "
					+ "FROM tb_membro "
					+ "WHERE codigo_sexual=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,codigo_sexual);
			rst = stmt.executeQuery();

			if(rst.next()) {					
				apelido = rst.getString("apelido");								
			}
			rst.close();				
			stmt.close();
			con.close();

		} catch (Exception e) {
			trataExcecao(e);
		}
		return apelido;
		
	}

	
	public List buscaMembros(int faz_sexo_com_busca,int idade_min_busca,int idade_max_busca, float altura_min_busca, float altura_max_busca, String cidade_busca,String estado_busca,int sexo_busca,int tipo_fisico_busca,int tom_pele_busca, int estado_civil_busca, long codigo_sexual_buscador){
		
		ArrayList<Membro> lista = new ArrayList<Membro>();
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {
			
			
			sql = ("SELECT * "
					+ "FROM tb_membro "
					+ "WHERE ");
					
			if(faz_sexo_com_busca != 7)
				sql = sql + "faz_sexo_com=? ";
			else
				sql = sql + "faz_sexo_com<>? ";

			sql = sql + "AND idade >= ? AND idade <= ? ";
			sql = sql + "AND altura >= ? AND altura <= ? ";

			if(cidade_busca.equals("") == false)
				sql = sql + "AND cidade LIKE ? ";
			else
				sql = sql + "AND cidade NOT LIKE ? ";
			
			if(estado_busca.equals("") == false)
				sql = sql + "AND estado LIKE ? ";
			else
				sql = sql + "AND estado NOT LIKE ? ";
			
			if(sexo_busca != 3)
				sql = sql + "AND sexo=? ";
			else
				sql = sql + "AND sexo<>? ";
			
			if(tipo_fisico_busca != 7)
				sql = sql + "AND tipo_fisico=? ";
			else
				sql = sql + "AND tipo_fisico<>? ";
			
			if(tom_pele_busca != 10)
				sql = sql + "AND tom_pele=? ";
			else
				sql = sql + "AND tom_pele<>? ";
			
			if(estado_civil_busca != 4)
				sql = sql + "AND estado_civil=? ";
			else
				sql = sql + "AND estado_civil<>? ";
			
			
			sql = sql + "AND codigo_sexual<>? ";
			sql = sql + "AND situacao<>0 ORDER BY apelido";
			
		
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1,faz_sexo_com_busca);
			stmt.setInt(2,idade_min_busca);
			stmt.setInt(3,idade_max_busca);
			stmt.setFloat(4,altura_min_busca);
			stmt.setFloat(5,altura_max_busca);
			stmt.setString(6,cidade_busca);
			stmt.setString(7,estado_busca);
			stmt.setInt(8,sexo_busca);
			stmt.setInt(9,tipo_fisico_busca);
			stmt.setInt(10,tom_pele_busca);
			stmt.setInt(11,estado_civil_busca);
			stmt.setLong(12,codigo_sexual_buscador);

			
			rst = stmt.executeQuery();

			while(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(rst.getLong("codigo_sexual"));
				membro.setNome(rst.getString("nome"));
				membro.setApelido(rst.getString("apelido"));
				membro.setSituacao(rst.getInt("situacao"));
				membro.setFaz_sexo_com(rst.getInt("faz_sexo_com"));
				membro.setIdade(rst.getInt("idade"));
				membro.setCidade(rst.getString("cidade"));
				membro.setEstado(rst.getString("estado").toUpperCase());
				membro.setSexo(rst.getInt("sexo"));
				membro.setPeso(rst.getFloat("peso"));
				membro.setAltura(rst.getFloat("altura"));
				membro.setTipo_fisico(rst.getInt("tipo_fisico"));
				membro.setTom_pele(rst.getInt("tom_pele"));
				membro.setEstado_civil(rst.getInt("estado_civil"));
				membro.setTexto(rst.getString("texto"));
				lista.add(membro);
				
			}
			rst.close();				
			stmt.close();
			con.close();
			
		} catch (Exception e) {
			trataExcecao(e);
		}
		return lista;
		
	}
	
		
	
	public Membro autenticarMembro(String email, String senha){
		
		Membro membro = null;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT * "
					+ "FROM tb_membro "
					+ "WHERE email=? AND senha=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,email);
			stmt.setString(2,senha);
			rst = stmt.executeQuery();

			if(rst.next()) {
				membro = new Membro();
				membro.setCodigo_sexual(rst.getLong("codigo_sexual"));
				membro.setNome(rst.getString("nome"));
				membro.setApelido(rst.getString("apelido"));
				membro.setEmail(email);
				membro.setSituacao(rst.getInt("situacao"));
				membro.setFaz_sexo_com(rst.getInt("faz_sexo_com"));
				membro.setSexo(rst.getInt("sexo"));
				membro.setPermissao(rst.getInt("permissao"));
				membro.setFim(rst.getTimestamp("fim"));
				membro.setDiscreto(rst.getInt("discreto"));
				
			}
			rst.close();				
			stmt.close();
			con.close();
			

		} catch (Exception e) {
			trataExcecao(e);
		}
		return membro;
		
	}
	
	
	public boolean existeEmail(String email){
		
		boolean resp = false;
		String sql;
		ResultSet rst;
		
		try {

			sql = ("SELECT codigo_sexual "
					+ "FROM tb_membro "
					+ "WHERE email=?");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,email);
			
			rst = stmt.executeQuery();
			if(rst.next()) {
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
	
	
	public int getNroMembros(){
		
		int total = 0;
		String sql;
		ResultSet rst;
		
		try {
			
			
			sql = ("SELECT COUNT(*) AS Total"
					+ "FROM tb_membro");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			rst = stmt.executeQuery();
			if(rst.next()) {
				total = rst.getInt("Total");				
			}
			rst.close();				
			stmt.close();
			con.close();

		} catch (Exception e) {
			trataExcecao(e);
		}
		return total;
		
	}

}
