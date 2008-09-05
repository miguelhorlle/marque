package com.marquesexo.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionPool {
	
	/** refer�ncia para uma �nica inst�ncia dessa classe */
    private static ConnectionPool mySelf;
	
	/** Especifica o nome do driver do banco de dados utilizado */
    public final static String driver = "com.mysql.jdbc.Driver";

	/** Especifica o nome do banco de dados utilizado */
	public final static String url = "jdbc:mysql://localhost:3309/marque";
		
	/** Especifica o usu�rio do banco de dados utilizado */
	public final static String user = "root";
	
	
	/** Especifica a senha do usu�rio do banco de dados utilizado */
	public final static String password = "";
	  
	/**
	 * M�todo construtor da classe
	 */
	private ConnectionPool() {	
		
	}
    
	/**
	 * Este m�todo obt�m uma instancia desta classe
	 * @return um objeto ConnectionPool
	 */
	public static synchronized ConnectionPool getInstance() {
        
            // verifica se ainda n�o foi criada uma �nica inst�ncia
			if( mySelf == null ) {
                
			    // cria a �nica inst�ncia dessa classe
				mySelf = new ConnectionPool();                   
			}            
		
		return mySelf;
	}
    
	/**
	 * Este m�todo obt�m uma conex�o com a base de
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
