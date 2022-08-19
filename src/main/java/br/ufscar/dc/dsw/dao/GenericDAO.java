package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import java.lang.*;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract public class GenericDAO {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public abstract T find(Long id);
    public abstract List<T> findAll();
    public abstract void save(T t);
    public abstract void update(T t);
    public abstract void delete(Long id);

    public static void close() {
        emf.close();
    }

    // public GenericDAO() {
    //     try {
            
    //     	/* Setup Banco de dados Derby */
        	
    //     	// Class.forName("org.apache.derby.jdbc.ClientDriver");
            
    //     	/* Setup Banco de dados MySQL */
        	
    //     	Class.forName("com.mysql.cj.jdbc.Driver");
            
        	
    //     } catch (ClassNotFoundException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    // protected Connection getConnection() throws SQLException {	
    // 	/* Conexão banco de dados MySQL */
    	
    // 	String url = "jdbc:mysql://localhost:3306/CONSULTAS_ONLINE";
    	
    // 	return DriverManager.getConnection(url, System.getenv("SQL_USER"), System.getenv("SQL_PASS"));
    // }
}