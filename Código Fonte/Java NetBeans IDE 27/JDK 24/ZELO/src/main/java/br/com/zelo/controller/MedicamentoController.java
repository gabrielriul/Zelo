package br.com.zelo.controller;

import br.com.zelo.dao.IMedicamentoDAO;
import br.com.zelo.dao.MedicamentoDAO;
import br.com.zelo.model.Medicamento;
import br.com.zelo.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoController {

    private final MedicamentoDAO medicamentoDAO; // Usamos a classe concreta para acessar métodos específicos

    public MedicamentoController(MedicamentoDAO medicamentoDAO) {
        this.medicamentoDAO = medicamentoDAO;
    }

    public Medicamento cadastrarMedicamento(String nome, String dosagem, String forma, String instrucoes, Usuario usuarioLogado, int estoque, int alerta) {
        
        // Validação básica
        if (nome == null || nome.trim().isEmpty() || usuarioLogado == null) {
            return null;
        }

        try {
            Medicamento novoMed = new Medicamento();
            novoMed.setNome(nome.trim());
            novoMed.setDosagem(dosagem);
            novoMed.setForma(forma);
            novoMed.setInstrucoes(instrucoes);
            novoMed.setIdUsuario(usuarioLogado.getIdUsuario());
            
            // Dados V2
            novoMed.setQuantidadeEstoque(estoque);
            novoMed.setQuantidadeAlerta(alerta);

            return medicamentoDAO.salvar(novoMed);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Medicamento> listarMedicamentosDoUsuario(Usuario usuarioLogado) {
        if (usuarioLogado == null) {
            return new ArrayList<>();
        }
        try {
            return medicamentoDAO.listarPorUsuario(usuarioLogado.getIdUsuario());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean editarMedicamento(Medicamento medicamento) {
        if (medicamento == null || medicamento.getIdMedicamento() <= 0) {
            return false;
        }
        try {
            return medicamentoDAO.editar(medicamento);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean excluirMedicamento(int idMedicamento) {
        if (idMedicamento <= 0) {
            return false;
        }
        try {
            return medicamentoDAO.excluir(idMedicamento);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Método V2
    public boolean decrementarEstoque(int idMedicamento) {
        try {
            return medicamentoDAO.decrementarEstoque(idMedicamento);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}