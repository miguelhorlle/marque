package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Membro;
import com.marquesexo.bean.Suruba;
import com.marquesexo.web.SendMail;


public class SurubaDAO {
	
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
			sql = ("DELETE FROM tb_suruba " 
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
	public boolean inserir(Suruba suruba){
		
		String sql;
		boolean resp = false;
		
		try {

			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_suruba " 
					+ "VALUES (0,?,?,?,?,?,?,?,?,?,?)");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);

		    // Seta os valores;
			stmt.setLong(1,suruba.getOrganizador().getCodigo_sexual());
            stmt.setString(2,suruba.getTitulo());
			stmt.setTimestamp(3, suruba.getData());
            stmt.setString(4,suruba.getHora());
            stmt.setString(5,suruba.getMinuto());
			stmt.setString(6,suruba.getLocal());
            stmt.setString(7,suruba.getCidade());
            stmt.setString(8,suruba.getEstado());
			stmt.setString(9,suruba.getObs());
			stmt.setInt(10,suruba.getStatus());
						
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
    public boolean atualizar(Suruba suruba){
        
        String sql;
        boolean resp = false;
        
        try {
            

            // Prepara um statement para atualização de um registro
            sql = ("UPDATE tb_suruba SET " 
                    + "organizador=?, "
                    + "titulo=?, "
                    + "data=?, "
                    + "hora=?, "
                    + "minuto=?, "
                    + "local=?, "
                    + "cidade=?, "
                    + "estado=?, "
                    + "obs=?, "
                    + "status=? "              
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            
            // Cria um statement
            PreparedStatement stmt = con.prepareStatement(sql);
            
            // Seta os valores;
            stmt.setLong(1,suruba.getOrganizador().getCodigo_sexual());
            stmt.setString(2,suruba.getTitulo());
            stmt.setTimestamp(3, suruba.getData());
            stmt.setString(4,suruba.getHora());
            stmt.setString(5,suruba.getMinuto());
            stmt.setString(6,suruba.getLocal());
            stmt.setString(7,suruba.getCidade());
            stmt.setString(8,suruba.getEstado());
            stmt.setString(9,suruba.getObs());
            stmt.setInt(10,suruba.getStatus());
            stmt.setLong(11,suruba.getId());
                        
            
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
            
            // 0 = finalizado
            // 1 = aberto          
                
            // Prepara um statement para atualização de um registro
            sql = ("UPDATE tb_suruba SET " 
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
    
    
    public Suruba getSuruba(long id){
        
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {            
        
            sql = ("SELECT * "
                    + "FROM tb_suruba "
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rst = stmt.executeQuery();

            if(rst.next()) {
                suruba = new Suruba();
                suruba.setId(id);
                membro = new Membro(rst.getLong("organizador"));
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setLocal(rst.getString("local"));
                suruba.setCidade(rst.getString("cidade"));
                suruba.setEstado(rst.getString("estado"));
                suruba.setObs(rst.getString("obs"));
                suruba.setStatus(rst.getInt("status"));
            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return suruba;
        
    }
    
    public Suruba getSurubaSimples(long id){
        
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {            
        
            sql = ("SELECT organizador, titulo, data, hora, minuto, status "
                    + "FROM tb_suruba "
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rst = stmt.executeQuery();

            if(rst.next()) {
                suruba = new Suruba();
                suruba.setId(id);
                membro = new Membro(rst.getLong("organizador"));
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setStatus(rst.getInt("status"));
            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return suruba;
        
    }
    
    
    public String getTituloSuruba(long id){
        
        String titulo = "";
        String sql;
        ResultSet rst;
        
        try {            
        
            sql = ("SELECT titulo "
                    + "FROM tb_suruba "
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rst = stmt.executeQuery();

            if(rst.next()) {
            
                titulo = (rst.getString("titulo"));

            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return titulo;
        
    }
    
    
    public long getIdSuruba(String titulo, String cidade, long organizador){
        
        long id = -1;
        String sql;
        ResultSet rst;
        
        try {            
        
            sql = ("SELECT id "
                    + "FROM tb_suruba "
                    + "WHERE titulo=? "
                    + "AND cidade=? "
                    + "AND organizador=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1,titulo);
            stmt.setString(2,cidade);
            stmt.setLong(3,organizador);
            
            rst = stmt.executeQuery();

            if(rst.next()) {
            
                id = (rst.getLong("id"));

            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return id;
        
    }
    
    
    public Membro getOrganizadorSuruba(long id){
        
        Membro organizador = new Membro();
        String sql;
        ResultSet rst;
        
        try {            
        
            sql = ("SELECT organizador "
                    + "FROM tb_suruba "
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rst = stmt.executeQuery();

            if(rst.next()) {
            
            	organizador.setCodigo_sexual(rst.getLong("organizador"));

            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return organizador;
        
    }

    
    public List getSurubasAbertas(){
        
        ArrayList<Suruba> lista = new ArrayList<Suruba>();
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT id, organizador, titulo, data, hora, minuto, cidade, estado "
                    + "FROM tb_suruba "
                    + "WHERE status=1 ");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            rst = stmt.executeQuery();

            while(rst.next()) {
                suruba = new Suruba();
                suruba.setId(rst.getLong("id"));
                membro = new Membro(rst.getLong("organizador"));
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setCidade(rst.getString("cidade"));
                suruba.setEstado(rst.getString("estado"));
                suruba.setStatus(1);
                lista.add(suruba);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    
    public List getSurubasFinalizadas(){
        
        ArrayList<Suruba> lista = new ArrayList<Suruba>();
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT id, organizador, titulo, data, hora, minuto, cidade, estado "
                    + "FROM tb_suruba "
                    + "WHERE status=0 ");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            rst = stmt.executeQuery();

            while(rst.next()) {
                suruba = new Suruba();
                suruba.setId(rst.getLong("id"));
                membro = new Membro(rst.getLong("organizador"));
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setCidade(rst.getString("cidade"));
                suruba.setEstado(rst.getString("estado"));
                suruba.setStatus(0);
                lista.add(suruba);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    
    public List getSurubasAbertasPeloOrganizador(long organizador){
        
        ArrayList<Suruba> lista = new ArrayList<Suruba>();
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT id, titulo, data, hora, minuto, cidade, estado "
                    + "FROM tb_suruba "
                    + "WHERE organizador=? AND status=1");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,organizador);
            rst = stmt.executeQuery();

            while(rst.next()) {
                suruba = new Suruba();
                suruba.setId(rst.getLong("id"));
                membro = new Membro(organizador);
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setCidade(rst.getString("cidade"));
                suruba.setEstado(rst.getString("estado"));
                suruba.setStatus(1);
                lista.add(suruba);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    

    
    public List getSurubasFinalizadasPeloOrganizador(long organizador){
        
        ArrayList<Suruba> lista = new ArrayList<Suruba>();
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT id, titulo, data, hora, minuto, cidade, estado "
                    + "FROM tb_suruba "
                    + "WHERE organizador=? AND status=0");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,organizador);
            rst = stmt.executeQuery();

            while(rst.next()) {
                suruba = new Suruba();
                suruba.setId(rst.getLong("id"));
                membro = new Membro(organizador);
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setCidade(rst.getString("cidade"));
                suruba.setEstado(rst.getString("estado"));
                suruba.setStatus(0);
                lista.add(suruba);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    
    
    public List buscaSurubasAbertas(String estado, String cidade){
        
        ArrayList<Suruba> lista = new ArrayList<Suruba>();
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT id, organizador, titulo, data, hora, minuto "
                    + "FROM tb_suruba "
                    + "WHERE status=1 ");
            
            if(cidade.equals("") == false)
                sql = sql + "AND cidade LIKE ? ";
            else
                sql = sql + "AND cidade NOT LIKE ? ";
            
            if(estado.equals("") == false)
                sql = sql + "AND estado LIKE ? ";
            else
                sql = sql + "AND estado NOT LIKE ? ";
                        
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,cidade);
            stmt.setString(2,estado);
            rst = stmt.executeQuery();

            while(rst.next()) {
                suruba = new Suruba();
                suruba.setId(rst.getLong("id"));
                membro = new Membro(rst.getLong("organizador"));
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setCidade(cidade);
                suruba.setEstado(estado);
                suruba.setStatus(1);
                lista.add(suruba);                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    
    
    public List buscaSurubasFinalizadas(String estado, String cidade){
        
        ArrayList<Suruba> lista = new ArrayList<Suruba>();
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT id, organizador, titulo, data, hora, minuto "
                    + "FROM tb_suruba "
                    + "WHERE status=0 ");
            
            if(cidade.equals("") == false)
                sql = sql + "AND cidade LIKE ? ";
            else
                sql = sql + "AND cidade NOT LIKE ? ";
            
            if(estado.equals("") == false)
                sql = sql + "AND estado LIKE ? ";
            else
                sql = sql + "AND estado NOT LIKE ? ";
                        
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,cidade);
            stmt.setString(2,estado);
            rst = stmt.executeQuery();

            while(rst.next()) {
                suruba = new Suruba();
                suruba.setId(rst.getLong("id"));
                membro = new Membro(rst.getLong("organizador"));
                suruba.setOrganizador(membro);                
                suruba.setTitulo(rst.getString("titulo"));
                suruba.setData(rst.getTimestamp("data"));
                suruba.setHora(rst.getString("hora"));
                suruba.setMinuto(rst.getString("minuto"));
                suruba.setCidade(cidade);
                suruba.setEstado(estado);
                suruba.setStatus(0);
                lista.add(suruba);                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }

}
