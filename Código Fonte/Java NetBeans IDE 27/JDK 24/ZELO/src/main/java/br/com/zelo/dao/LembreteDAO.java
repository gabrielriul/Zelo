package br.com.zelo.dao;

import br.com.zelo.model.Lembrete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LembreteDAO implements ILembreteDAO {

    private final String CREATE_TABLE_SQL = 
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
    
    public LembreteDAO() {
        criarTabelaSeNaoExistir();
    }
    
    private void criarTabelaSeNaoExistir() {
        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(CREATE_TABLE_SQL);
            
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela de lembretes:");
            e.printStackTrace();
        }
    }

    @Override
    public boolean salvar(Lembrete lembrete) throws Exception {
        if (verificarLembreteExiste(lembrete)) {
            System.out.println("Erro: Este medicamento já está agendado para este horário.");
            return false;
        }
        
        String INSERT_SQL = "INSERT INTO lembretes (horario, frequencia, status, idUsuario, idMedicamento) " +
                            "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL)) {
            
            pstmt.setObject(1, lembrete.getHorario()); 
            pstmt.setString(2, lembrete.getFrequencia());
            pstmt.setString(3, lembrete.getStatus());
            pstmt.setInt(4, lembrete.getIdUsuario());
            pstmt.setInt(5, lembrete.getIdMedicamento());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar lembrete:");
            e.printStackTrace();
            throw new Exception("Erro ao salvar lembrete.", e);
        }
    }
    
    private boolean verificarLembreteExiste(Lembrete lembrete) throws SQLException {
        String CHECK_SQL = "SELECT COUNT(*) FROM lembretes " +
                           "WHERE idUsuario = ? AND idMedicamento = ? AND horario = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(CHECK_SQL)) {
            
            pstmt.setInt(1, lembrete.getIdUsuario());
            pstmt.setInt(2, lembrete.getIdMedicamento());
            pstmt.setObject(3, lembrete.getHorario());
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public boolean mudarStatus(int idLembrete, String novoStatus) throws Exception {
        String UPDATE_SQL = "UPDATE lembretes SET status = ? WHERE idLembrete = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            
            pstmt.setString(1, novoStatus);
            pstmt.setInt(2, idLembrete);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao mudar status do lembrete:");
            e.printStackTrace();
            throw new Exception("Erro ao mudar status.", e);
        }
    }

    @Override
    public List<Lembrete> listarPorUsuario(int idUsuario) throws Exception {
        List<Lembrete> lista = new ArrayList<>();
        String LIST_SQL = "SELECT * FROM lembretes WHERE idUsuario = ? ORDER BY horario ASC";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(LIST_SQL)) {
            
            pstmt.setInt(1, idUsuario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Lembrete lembrete = new Lembrete();
                    lembrete.setIdLembrete(rs.getInt("idLembrete"));
                    lembrete.setHorario(rs.getObject("horario", LocalTime.class));
                    lembrete.setFrequencia(rs.getString("frequencia"));
                    lembrete.setStatus(rs.getString("status"));
                    lembrete.setIdUsuario(rs.getInt("idUsuario"));
                    lembrete.setIdMedicamento(rs.getInt("idMedicamento"));
                    
                    lista.add(lembrete);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar lembretes:");
            e.printStackTrace();
            throw new Exception("Erro ao buscar lembretes.", e);
        }
        
        return lista;
    }

    @Override
    public boolean excluirPorMedicamento(int idMedicamento) throws Exception {
        String DELETE_SQL = "DELETE FROM lembretes WHERE idMedicamento = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            
            pstmt.setInt(1, idMedicamento);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir lembretes por medicamento:");
            e.printStackTrace();
            throw new Exception("Erro ao excluir lembretes.", e);
        }
    }
}