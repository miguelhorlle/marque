package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.bean.Membro;
import com.marquesexo.bean.ParticipacaoSuruba;
import com.marquesexo.bean.Suruba;
import com.marquesexo.web.SendMail;


public class ParticipacaoSurubaDAO {
	
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
			sql = ("DELETE FROM tb_participacao_suruba " 
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
	public boolean inserir(ParticipacaoSuruba participacao){
		
		String sql;
		boolean resp = false;
		
		try {

			// Prepara um statement para inserção de um registro
			sql = ("INSERT INTO tb_participacao_suruba " 
					+ "VALUES (0,?,?,?)");
			
			Connection con = ConnectionPool.getInstance().getConnection();
			
			// Cria um statement
			PreparedStatement stmt = con.prepareStatement(sql);

		    // Seta os valores;
			stmt.setLong(1,participacao.getSuruba().getId());
			stmt.setLong(2,participacao.getParticipante().getCodigo_sexual());
			stmt.setInt(3,participacao.getStatus());
						
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
    public boolean atualizar(ParticipacaoSuruba participacao){
        
        String sql;
        boolean resp = false;
        
        try {
            

            // Prepara um statement para atualização de um registro
            sql = ("UPDATE tb_participacao_suruba SET " 
                    + "id_suruba=?, "
                    + "codigo_sexual=?, "
                    + "status=? "              
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            
            // Cria um statement
            PreparedStatement stmt = con.prepareStatement(sql);
            
            // Seta os valores;
            stmt.setLong(1,participacao.getSuruba().getId());
            stmt.setLong(2,participacao.getParticipante().getCodigo_sexual());
            stmt.setInt(3,participacao.getStatus());
            stmt.setLong(4,participacao.getId());
            
            
            
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
            // 1 = aceito
            // 2 = recusado
            // 3 = desistiu
            // 4 = foi
            // 5 = nao foi
            
                
            // Prepara um statement para atualização de um registro
            sql = ("UPDATE tb_participacao_suruba SET " 
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
    
    
    public ParticipacaoSuruba getParticipacaoSuruba(long id){
        
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
        
            sql = ("SELECT * "
                    + "FROM tb_participacao_suruba "
                    + "WHERE id=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id);
            rst = stmt.executeQuery();

            if(rst.next()) {
                participacao = new ParticipacaoSuruba();
                participacao.setId(id);
                membro = new Membro(rst.getLong("codigo_sexual"));
                participacao.setParticipante(membro);
                suruba = new Suruba(rst.getLong("id_suruba"));
                participacao.setSuruba(suruba);
                participacao.setStatus(rst.getInt("status"));
            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return participacao;
        
    }
    
    
    public ParticipacaoSuruba getParticipacaoSurubaPelaSurubaPeloParticipante(long id_suruba, long codigo_sexual){
        
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
        
            sql = ("SELECT id, status "
                    + "FROM tb_participacao_suruba "
                    + "WHERE id_suruba=? AND codigo_sexual=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id_suruba);
            stmt.setLong(2,codigo_sexual);
            rst = stmt.executeQuery();

            if(rst.next()) {
                participacao = new ParticipacaoSuruba();
                participacao.setId(rst.getLong("id"));
                membro = new Membro(codigo_sexual);
                participacao.setParticipante(membro);
                suruba = new Suruba(id_suruba);
                participacao.setSuruba(suruba);
                participacao.setStatus(rst.getInt("status"));
            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return participacao;
        
    }
    
    

    public boolean hasParticipacaoSuruba(long id_suruba, long codigo_sexual){
        
        boolean resposta = false;
        String sql;
        ResultSet rst;
        
        try {
            
        
            sql = ("SELECT id "
                    + "FROM tb_participacao_suruba "
                    + "WHERE id_suruba=? AND codigo_sexual=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,id_suruba);
            stmt.setLong(2,codigo_sexual);
            rst = stmt.executeQuery();

            if(rst.next()) {
                resposta = true;
            }
            rst.close();                
            stmt.close();
            con.close();
            

        } catch (Exception e) {
            trataExcecao(e);
        }
        return resposta;
        
    }

    
    public List getParticipacoesSurubaPelaSuruba(long id_suruba){
        
        ArrayList<ParticipacaoSuruba> lista = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT * "
                    + "FROM tb_participacao_suruba "
                    + "WHERE id_suruba=? ");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1,id_suruba);
            
            rst = stmt.executeQuery();

            while(rst.next()) {
                participacao = new ParticipacaoSuruba();
                participacao.setId(rst.getLong("id"));
                membro = new Membro(rst.getLong("codigo_sexual"));
                participacao.setParticipante(membro);
                suruba = new Suruba(id_suruba);
                participacao.setSuruba(suruba);
                participacao.setStatus(rst.getInt("status"));
                lista.add(participacao);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    
    public List getParticipacoesSurubaPeloStatus(int status){
        
        ArrayList<ParticipacaoSuruba> lista = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT * "
                    + "FROM tb_participacao_suruba "
                    + "WHERE status=? ");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,status);
            
            rst = stmt.executeQuery();

            while(rst.next()) {
                participacao = new ParticipacaoSuruba();
                participacao.setId(rst.getLong("id"));
                membro = new Membro(rst.getLong("codigo_sexual"));
                participacao.setParticipante(membro);
                suruba = new Suruba(rst.getLong("id_suruba"));
                participacao.setSuruba(suruba);
                participacao.setStatus(status);
                lista.add(participacao);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    public List getParticipacoesSurubaPeloStatusPelaSuruba(int status, long id_suruba){
        
        ArrayList<ParticipacaoSuruba> lista = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participante = null;
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT * "
                    + "FROM tb_participacao_suruba "
                    + "WHERE status=? AND id_suruba=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,status);
            stmt.setLong(2,id_suruba);
            
            rst = stmt.executeQuery();

            while(rst.next()) {
                participante = new ParticipacaoSuruba();
                participante.setId(rst.getLong("id"));
                membro = new Membro(rst.getLong("codigo_sexual"));
                participante.setParticipante(membro);
                suruba = new Suruba(id_suruba);
                participante.setSuruba(suruba);
                participante.setStatus(status);
                lista.add(participante);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    
    public List getParticipacoesSurubaPeloStatusPeloParticipante(int status, long codigo_sexual){
        
        ArrayList<ParticipacaoSuruba> lista = new ArrayList<ParticipacaoSuruba>();
        ParticipacaoSuruba participacao = null;
        Suruba suruba = null;
        Membro membro = null;
        String sql;
        ResultSet rst;
        
        try {
            
            
            sql = ("SELECT * "
                    + "FROM tb_participacao_suruba "
                    + "WHERE status=? AND codigo_sexual=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,status);
            stmt.setLong(2,codigo_sexual);
            
            rst = stmt.executeQuery();

            while(rst.next()) {
                participacao = new ParticipacaoSuruba();
                participacao.setId(rst.getLong("id"));
                membro = new Membro(codigo_sexual);
                participacao.setParticipante(membro);
                suruba = new Suruba(rst.getLong("id_suruba"));
                participacao.setSuruba(suruba);
                participacao.setStatus(status);
                lista.add(participacao);
                
            }
            rst.close();                
            stmt.close();
            con.close();
            
        } catch (Exception e) {
            trataExcecao(e);
        }
        return lista;
        
    }
    
    public int getQtdParticipacoesSurubaPeloStatusPeloParticipante(int status, long codigo_sexual){
        
        String sql;
        ResultSet rst;
        int qtd = 0;
        
        try {
                        
            
            sql = ("SELECT COUNT(*) AS qtd "
                    + "FROM tb_participacao_suruba "
                    + "WHERE status=? AND codigo_sexual=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,status);
            stmt.setLong(2,codigo_sexual);
            
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
    
    
    public int getQtdParticipacoesSurubaPeloStatusPelaSuruba(int status, long id_suruba){
        
        String sql;
        ResultSet rst;
        int qtd = 0;
        
        try {
                        
            
            sql = ("SELECT COUNT(*) AS qtd "
                    + "FROM tb_participacao_suruba "
                    + "WHERE status=? AND id_suruba=?");
            
            Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,status);
            stmt.setLong(2,id_suruba);
            
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
