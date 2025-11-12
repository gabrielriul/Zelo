package br.com.zelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    
    private static final String HOST = "localhost";
    private static final String PORT = "5432";
    private static final String DATABASE_NAME = "Zelo";
    
    private static final String URL = "jdbc:postgresql://"+ HOST +":"+ PORT+ "/"+ DATABASE_NAME;
    
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    
    // Obtém uma conexão com o banco de dados PostgreSQL.
    public static Connection getConexao() {
        try {
            // Carrega o driver do PostgreSQL
            Class.forName("org.postgresql.Driver");
            
            // Tenta estabelecer a conexão
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do PostgreSQL não encontrado!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados PostgreSQL:");
            e.printStackTrace();
            return null;
        }
    }
}