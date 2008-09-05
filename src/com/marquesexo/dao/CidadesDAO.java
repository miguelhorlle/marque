package com.marquesexo.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.marquesexo.web.SendMail;


public class CidadesDAO {

	
	/** Especifica o nome do driver do banco de dados utilizado */
	public final static String driver = "com.mysql.jdbc.Driver";

	/** Especifica o nome do banco de dados utilizado */
	public final static String url = "jdbc:mysql://localhost:3309/marque";
		
	/** Especifica o usuário do banco de dados utilizado */
	public final static String user = "root";
	
	/** Especifica a senha do usuário do banco de dados utilizado */
	public final static String password = "";
	
	
	public List getCidades(String estado){
		List<String> cidades = new ArrayList<String>();
		
		try{	
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user,
					password);
		
			String sql = ("SELECT municipio "
					+ "FROM tb_cidades "
					+ "WHERE uf LIKE ? "
					+ "ORDER BY municipio");
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, estado);
			ResultSet rst = stmt.executeQuery();

			while (rst.next()) {
				cidades.add(rst.getString(1));
			}
					
			rst.close();				
			stmt.close();
			con.close();
			
		}
		catch (java.lang.Exception e){
			trataExcecao(e);
		}
		
		
		return cidades;
	}
	

	private void trataExcecao(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String erro = sw.toString();
		SendMail.sendMail("MarqueSexo - Erro", erro , "rafaelcl@gmail.com");
	}
}
