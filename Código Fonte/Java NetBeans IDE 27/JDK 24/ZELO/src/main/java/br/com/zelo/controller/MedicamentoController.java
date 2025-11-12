package br.com.zelo.controller;

import br.com.zelo.dao.IMedicamentoDAO;
import br.com.zelo.model.Medicamento;
import br.com.zelo.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoController {

    private final IMedicamentoDAO medicamentoDAO;

    public MedicamentoController(IMedicamentoDAO medicamentoDAO) {
        this.medicamentoDAO = medicamentoDAO;
    }

    public Medicamento cadastrarMedicamento(String nome, String dosagem, String forma, String instrucoes, Usuario usuarioLogado) {
        
        if (nome == null || nome.trim().isEmpty() || usuarioLogado == null) {
            System.err.println("Controller: Nome ou usuário inválidos.");
            return null; // MUDANÇA (1)
        }

        try {
            Medicamento novoMed = new Medicamento();
            novoMed.setNome(nome.trim());
            novoMed.setDosagem(dosagem);
            novoMed.setForma(forma);
            novoMed.setInstrucoes(instrucoes);
            novoMed.setIdUsuario(usuarioLogado.getIdUsuario());

            // MUDANÇA (2): O DAO agora retorna o objeto salvo (ou null)
            return medicamentoDAO.salvar(novoMed);

        } catch (Exception e) {
            System.err.println("Controller: Erro ao salvar medicamento.");
            e.printStackTrace();
            return null; // MUDANÇA (3)
        }
    }

    public List<Medicamento> listarMedicamentosDoUsuario(Usuario usuarioLogado) {
        if (usuarioLogado == null) {
            return new ArrayList<>();
        }
        
        try {
            return medicamentoDAO.listarPorUsuario(usuarioLogado.getIdUsuario());
        } catch (Exception e) {
            System.err.println("Controller: Erro ao listar medicamentos.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean editarMedicamento(Medicamento medicamento) {
        if (medicamento == null || medicamento.getIdMedicamento() <= 0) {
            System.err.println("Controller: Medicamento inválido para edição.");
            return false;
        }
        if (medicamento.getNome() == null || medicamento.getNome().trim().isEmpty()) {
            System.err.println("Controller: Nome do medicamento é obrigatório na edição.");
            return false;
        }

        try {
            return medicamentoDAO.editar(medicamento);
        } catch (Exception e) {
            System.err.println("Controller: Erro ao editar medicamento.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean excluirMedicamento(int idMedicamento) {
        if (idMedicamento <= 0) {
            System.err.println("Controller: ID inválido para exclusão.");
            return false;
        }
        
        try {
            return medicamentoDAO.excluir(idMedicamento);
        } catch (Exception e) {
            System.err.println("Controller: Erro ao excluir medicamento.");
            e.printStackTrace();
            return false;
        }
    }
}