package br.com.zelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    
    // --- DADOS DE CONEXÃO (NUVEM - NEON) ---
    private static final String HOST = "ep-quiet-darkness-aciu4qgl-pooler.sa-east-1.aws.neon.tech";
    private static final String PORT = "5432";
    private static final String DATABASE_NAME = "neondb";
    private static final String USER = "neondb_owner";
    
    // COLOQUE SUA SENHA AQUI
    private static final String PASSWORD = "npg_AdMeWfHwD90S"; 
    
    // URL com SSL obrigatório para o Neon
    private static final String URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE_NAME + "?sslmode=require";
    
    
    // --- DEFINIÇÃO DAS TABELAS (V2 - Com Estoque) ---
    private static final String CREATE_TABLE_USUARIOS_SQL = 
            "CREATE TABLE IF NOT EXISTS usuarios (" +
            "  idUsuario SERIAL PRIMARY KEY," +
            "  nomeCompleto VARCHAR(255)," +
            "  cpf VARCHAR(20) UNIQUE NOT NULL," +
            "  usuario VARCHAR(255) UNIQUE NOT NULL," +
            "  senha VARCHAR(255) NOT NULL" +
            ");";
    
    private static final String CREATE_TABLE_MEDICAMENTOS_SQL = 
            "CREATE TABLE IF NOT EXISTS medicamentos (" +
            "  idMedicamento SERIAL PRIMARY KEY," +
            "  nome VARCHAR(255) NOT NULL," +
            "  dosagem VARCHAR(100)," +
            "  forma VARCHAR(100)," +
            "  instrucoes TEXT," +
            "  idUsuario INT NOT NULL," +
            "  quantidadeEstoque INT DEFAULT 0," +
            "  quantidadeAlerta INT DEFAULT 0," +
            "  CONSTRAINT fk_usuario " +
            "    FOREIGN KEY(idUsuario) " + 
            "    REFERENCES usuarios(idUsuario) " +
            "    ON DELETE CASCADE" +
            ");";
            
    private static final String CREATE_TABLE_LEMBRETES_SQL = 
            "CREATE TABLE IF NOT EXISTS lembretes (" +
            "  idLembrete SERIAL PRIMARY KEY," +
            "  horario TIME NOT NULL," +
            "  frequencia VARCHAR(100)," +
            "  status VARCHAR(50) NOT NULL," +
            "  idUsuario INT NOT NULL," +
            "  idMedicamento INT NOT NULL," +
            "  CONSTRAINT fk_usuario_lembrete " +
            "    FOREIGN KEY(idUsuario) " + 
            "    REFERENCES usuarios(idUsuario) " +
            "    ON DELETE CASCADE," +
            "  CONSTRAINT fk_medicamento_lembrete " +
            "    FOREIGN KEY(idMedicamento) " + 
            "    REFERENCES medicamentos(idMedicamento) " +
            "    ON DELETE CASCADE" +
            ");";

    /**
     * Método estático para inicializar o banco de dados.
     * Cria as tabelas na ordem correta de dependência.
     */
    public static void inicializarBanco() {
        try (Connection conn = getConexao();
             Statement stmt = conn.createStatement()) {
            
            System.out.println("Inicializando BD (Neon)...");
            
            System.out.println("- Verificando tabela Usuarios...");
            stmt.execute(CREATE_TABLE_USUARIOS_SQL);
            
            System.out.println("- Verificando tabela Medicamentos...");
            stmt.execute(CREATE_TABLE_MEDICAMENTOS_SQL);
            
            System.out.println("- Verificando tabela Lembretes...");
            stmt.execute(CREATE_TABLE_LEMBRETES_SQL);
            
            System.out.println("Banco de Dados pronto para uso.");
            
        } catch (SQLException e) {
            System.err.println("Erro fatal ao inicializar o banco de dados:");
            e.printStackTrace();
        }
    }
    
    public static Connection getConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Erro de conexão com o Neon:");
            e.printStackTrace();
            return null;
        }
    }
}