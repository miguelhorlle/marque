package com.marquesexo.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionPool {
	
	/** referência para uma única instância dessa classe */
    private static ConnectionPool mySelf;
	
	/** Especifica o nome do driver do banco de dados utilizado */
    public final static String driver = "com.mysql.jdbc.Driver";

	/** Especifica o nome do banco de dados utilizado */
	public final static String url = "jdbc:mysql://localhost:3309/marque";
		
	/** Especifica o usuário do banco de dados utilizado */
	public final static String user = "root";
	
	
	/** Especifica a senha do usuário do banco de dados utilizado */
	public final static String password = "";
	  
	/**
	 * Método construtor da classe
	 */
	private ConnectionPool() {	
		
	}
    
	/**
	 * Este método obtêm uma instancia desta classe
	 * @return um objeto ConnectionPool
	 */
	public static synchronized ConnectionPool getInstance() {
        
            // verifica se ainda não foi criada uma única instância
			if( mySelf == null ) {
                
			    // cria a única instância dessa classe
				mySelf = new ConnectionPool();                   
			}            
		
		return mySelf;
	}
    
	/**
	 * Este método obtêm uma conexão com a base de
	 * dados
	 * @return um objeto Connection
	 */
	public Connection getConnection(){
        	
		Connection con = null;
		try
		{	
			Class.forName(driver);
			while(con == null){
				con = DriverManager.getConnection(url, user,
						password);
			}
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return con;
	}

}
