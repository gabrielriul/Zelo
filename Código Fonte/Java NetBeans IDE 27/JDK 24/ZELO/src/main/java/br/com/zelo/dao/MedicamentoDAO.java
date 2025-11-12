package br.com.zelo.dao;

import br.com.zelo.model.Medicamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO implements IMedicamentoDAO {

    private final String CREATE_TABLE_SQL = 
            "CREATE TABLE IF NOT EXISTS medicamentos (" +
            "  idMedicamento SERIAL PRIMARY KEY," +
            "  nome VARCHAR(255) NOT NULL," +
            "  dosagem VARCHAR(100)," +
            "  forma VARCHAR(100)," +
            "  instrucoes TEXT," +
            "  idUsuario INT NOT NULL," +
            "  CONSTRAINT fk_usuario " +
            "    FOREIGN KEY(idUsuario) " + 
            "    REFERENCES usuarios(idUsuario) " +
            "    ON DELETE CASCADE" +
            ");";

    public MedicamentoDAO() {
        criarTabelaSeNaoExistir();
    }
    
    private void criarTabelaSeNaoExistir() {
        try (Connection conn = ConexaoBD.getConexao();
            Statement stmt = conn.createStatement()) {
            
            stmt.execute(CREATE_TABLE_SQL);
            
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela de medicamentos:");
            e.printStackTrace();
        }
    }

    @Override
    public Medicamento salvar(Medicamento medicamento) throws Exception {
        if (verificarMedicamentoExiste(medicamento.getNome(), medicamento.getIdUsuario())) {
            System.out.println("Erro: Medicamento '" + medicamento.getNome() + "' já cadastrado.");
            return null;
        }

        String INSERT_SQL = "INSERT INTO medicamentos (nome, dosagem, forma, instrucoes, idUsuario) " +
                            "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, medicamento.getNome());
            pstmt.setString(2, medicamento.getDosagem());
            pstmt.setString(3, medicamento.getForma());
            pstmt.setString(4, medicamento.getInstrucoes());
            pstmt.setInt(5, medicamento.getIdUsuario());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        medicamento.setIdMedicamento(generatedKeys.getInt(1)); // Seta o ID no objeto
                        return medicamento;
                    } else {
                        throw new SQLException("Falha ao salvar, nenhum ID obtido.");
                    }
                }
            } else {
                return null;
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao salvar medicamento:");
            e.printStackTrace();
            throw new Exception("Erro ao salvar medicamento.", e);
        }
    }

    private boolean verificarMedicamentoExiste(String nome, int idUsuario) throws SQLException {
        String CHECK_SQL = "SELECT COUNT(*) FROM medicamentos WHERE nome = ? AND idUsuario = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(CHECK_SQL)) {
            
            pstmt.setString(1, nome);
            pstmt.setInt(2, idUsuario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    @Override
    public List<Medicamento> listarPorUsuario(int idUsuario) throws Exception {
        List<Medicamento> lista = new ArrayList<>();
        String LIST_SQL = "SELECT * FROM medicamentos WHERE idUsuario = ? ORDER BY nome ASC";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(LIST_SQL)) {
            
            pstmt.setInt(1, idUsuario);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Medicamento med = new Medicamento();
                    med.setIdMedicamento(rs.getInt("idMedicamento"));
                    med.setNome(rs.getString("nome"));
                    med.setDosagem(rs.getString("dosagem"));
                    med.setForma(rs.getString("forma"));
                    med.setInstrucoes(rs.getString("instrucoes"));
                    med.setIdUsuario(rs.getInt("idUsuario"));
                    lista.add(med);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar medicamentos:");
            e.printStackTrace();
            throw new Exception("Erro ao buscar medicamentos do usuário.", e);
        }
        
        return lista;
    }

    @Override
    public boolean editar(Medicamento medicamento) throws Exception {
        String UPDATE_SQL = "UPDATE medicamentos SET nome = ?, dosagem = ?, forma = ?, instrucoes = ? " +
                            "WHERE idMedicamento = ?"; 

        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_SQL)) {
            
            pstmt.setString(1, medicamento.getNome());
            pstmt.setString(2, medicamento.getDosagem());
            pstmt.setString(3, medicamento.getForma());
            pstmt.setString(4, medicamento.getInstrucoes());
            pstmt.setInt(5, medicamento.getIdMedicamento());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao editar medicamento:");
            e.printStackTrace();
            throw new Exception("Erro ao editar medicamento.", e);
        }
    }

    @Override
    public boolean excluir(int idMedicamento) throws Exception {
        String DELETE_SQL = "DELETE FROM medicamentos WHERE idMedicamento = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_SQL)) {
            
            pstmt.setInt(1, idMedicamento);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir medicamento:");
            e.printStackTrace();
            throw new Exception("Erro ao excluir medicamento.", e);
        }
    }
}