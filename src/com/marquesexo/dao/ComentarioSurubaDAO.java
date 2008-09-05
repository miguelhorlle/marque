package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.ComentarioSuruba;
import com.marquesexo.bean.ParticipacaoSuruba;
import com.marquesexo.bean.Suruba;
import com.marquesexo.web.SendMail;


public class ComentarioSurubaDAO {
	
	private void trataExcecao(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String erro = sw.toString();
		SendMail.sendMail("MarqueSexo - Erro", erro , "rafaelcl@gmail.com");
	}
	
	/**
	 * Este m�todo exclui um registro da tabela
	 * @param id
	 * @return a confirma��o da opera��o
	 */
	public boolean excluir(long id){
		String sql;
		boolean resp = false;
		
		try {
			
			Connection con = ConnectionPool.getInstance().getConnection();
				
			// Prepara um statement para exclus�o de um registro
			sql = ("DELETE FROM tb_comentario_suruba " 
					+ "WHERE id = ?");
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);
			
		    // Seta os valores
			stmt.setLong(1, id);
			
			// Executa o statement de exclus�o
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
	 * Este m�todo inclui um registro da tabela
	 * @param aviso
	 * @return a confirma��o da opera��o
	 */
	public boolean inserir(ComentarioSuruba comentario){
		
		String sql;
		boolean resp = false;
		
		try {

			// Prepara um statement para inser��o de um registro
			sql = ("INSERT INTO tb_comentario_suruba " 
					+ "VALUES (0,?,?,?)");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);

		    // Seta os valores;
			stmt.setLong(1,comentario.getParticipacao().getId());
			stmt.setLong(2,comentario.getSuruba().getId());
			stmt.setString(3,comentario.getComentario());
						
			// Executa o statement de inser��o
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
     * Este m�todo atualiza um registro da tabela
     * @param aviso
     * @return a confirma��o da opera��o
     */
    public boolean atualizar(ComentarioSuruba comentario){
        
        String sql;
        boolean resp = false;
        
        try {
            

            // Prepara um statement para atualiza��o de um registro
            sql = ("UPDATE tb_comentario_suruba SET " 
                    + "id_partipacao=?, "
                    + "id_suruba=?, "       
                    + "comentario=? " 
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            
            // Cria um statement
            PreparedStatement stmt = con.prepareStatement(sql);
            
            // Seta os valores;
            stmt.setLong(1,comentario.getParticipacao().getId());
            stmt.setLong(2,comentario.getSuruba().getId());
            stmt.setString(3,comentario.getComentario());
            stmt.setLong(4,comentario.getId());
            
            
            
            // Executa o statement de atualiza��o
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
    
    
    
    public ComentarioSuruba getComentarioSuruba(long id){
        
        ComentarioSuruba comentario= null;
        Suruba suruba = null;
        ParticipacaoSuruba participacao= null;
        String sql;
        ResultSet rst;
        
        try {
            
        
            sql = ("SELECT * "
                    + "FROM tb_comentario_suruba "
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rst = stmt.executeQuery();

            if(rst.next()) {
                comentario = new ComentarioSuruba();
                comentario.setId(id);
                suruba = new Suruba(rst.getLong("id_suruba"));
                comentario.setSuruba(suruba);
                participacao = new ParticipacaoSuruba(rst.getLong("id_participacao"));
                comentario.setParticipacao(participacao);
                comentario.setComentario(rst.getString("comentario"));
            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return comentario;
        
    }
    
    
    
    public List getComentariosSurubaPelaSuruba(long id_suruba){
        
        ArrayList<ComentarioSuruba> lista = new ArrayList<ComentarioSuruba>();
        ComentarioSuruba comentario= null;
        Suruba suruba = null;
        ParticipacaoSuruba participacao= null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT * "
                    + "FROM tb_comentario_suruba "
                    + "WHERE id_suruba=? ");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1,id_suruba);
            
            rst = stmt.executeQuery();

            while(rst.next()) {
                comentario = new ComentarioSuruba();
                comentario.setId(rst.getLong("id"));
                suruba = new Suruba(id_suruba);
                comentario.setSuruba(suruba);
                participacao = new ParticipacaoSuruba(rst.getLong("id_participacao"));
                comentario.setParticipacao(participacao);
                comentario.setComentario(rst.getString("comentario"));
                lista.add(comentario);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    
    public List getComentariosSurubaPelaParticipacao(long id_participacao){
        
        ArrayList<ComentarioSuruba> lista = new ArrayList<ComentarioSuruba>();
        ComentarioSuruba comentario= null;
        Suruba suruba = null;
        ParticipacaoSuruba participante= null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT * "
                    + "FROM tb_comentario_suruba "
                    + "WHERE id_participacao=? ");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1,id_participacao);
            
            rst = stmt.executeQuery();

            while(rst.next()) {
                comentario = new ComentarioSuruba();
                comentario.setId(rst.getLong("id"));
                suruba = new Suruba(rst.getLong("id_suruba"));
                comentario.setSuruba(suruba);
                participante = new ParticipacaoSuruba(id_participacao);
                comentario.setParticipacao(participante);
                comentario.setComentario(rst.getString("comentario"));
                lista.add(comentario);
                
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
