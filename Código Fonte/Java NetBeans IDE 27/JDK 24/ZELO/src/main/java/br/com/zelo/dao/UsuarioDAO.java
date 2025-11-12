package br.com.zelo.dao;

import br.com.zelo.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO implements IUsuarioDAO {

    private final String CREATE_TABLE_SQL = 
            "CREATE TABLE IF NOT EXISTS usuarios (" +
            "  idUsuario SERIAL PRIMARY KEY," +
            "  nomeCompleto VARCHAR(255)," +
            "  cpf VARCHAR(20) UNIQUE NOT NULL," +
            "  usuario VARCHAR(255) UNIQUE NOT NULL," +
            "  senha VARCHAR(255) NOT NULL" +
            ");";

    public UsuarioDAO() {
        criarTabelaSeNaoExistir();
    }
    
    private void criarTabelaSeNaoExistir() {
        try (Connection conn = ConexaoBD.getConexao();
            Statement stmt = conn.createStatement()) {
            
            stmt.execute(CREATE_TABLE_SQL);
            
        } catch (SQLException e) {
            System.err.println("USUARIO DAO: Erro ao criar tabela de usuários:");
            e.printStackTrace();
        }
    }

    @Override
    public boolean salvar(Usuario usuario) throws Exception {
        // Checagem de duplicidade pelo 'usuario' (login)
        if (verificarUsuarioExiste(usuario.getUsuario())) {
            System.out.println("Erro: Usuário com login '" + usuario.getUsuario() + "' já existe.");
            return false;
        }
        
        // Checagem de 'CPF'
        if (verificarCPFExiste(usuario.getCPF())) {
            System.out.println("Erro: CPF já cadastrado.");
            return false;
        }

        String INSERT_SQL = "INSERT INTO usuarios (nomeCompleto, cpf, usuario, senha) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {
            
            pstmt.setString(1, usuario.getNomeCompleto());
            pstmt.setString(2, usuario.getCPF());
            pstmt.setString(3, usuario.getUsuario());
            pstmt.setString(4, usuario.getSenha());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário:");
            e.printStackTrace();
            throw new Exception("Erro ao salvar usuário no banco de dados.", e);
        }
    }

    private boolean verificarUsuarioExiste(String usuario) throws SQLException {
        String CHECK_SQL = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(CHECK_SQL)) {
            
            pstmt.setString(1, usuario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    private boolean verificarCPFExiste(String cpf) throws SQLException {
        String CHECK_SQL = "SELECT COUNT(*) FROM usuarios WHERE cpf = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(CHECK_SQL)) {
            
            pstmt.setString(1, cpf);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public Usuario fazerLogin(String usuarioLogin, String senha) throws Exception {
        String LOGIN_SQL = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(LOGIN_SQL)) {
            
            pstmt.setString(1, usuarioLogin);
            pstmt.setString(2, senha);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                    usuario.setCPF(rs.getString("cpf"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setSenha(rs.getString("senha")); 
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao tentar fazer login:");
            e.printStackTrace();
            throw new Exception("Erro de banco de dados ao tentar fazer login.", e);
        }
        
        return null;
    }
    
    @Override
    public boolean excluir(int idUsuario) throws Exception {
        String DELETE_SQL = "DELETE FROM usuarios WHERE idUsuario = ?";

        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {

            pstmt.setInt(1, idUsuario);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; // Retorna true se a linha foi deletada

        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário:");
            e.printStackTrace();
            throw new Exception("Erro ao excluir usuário.", e);
        }
    }
}