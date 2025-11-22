package br.com.zelo.controller;

import br.com.zelo.dao.ILembreteDAO;
import br.com.zelo.model.Lembrete;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LembreteController {

    private final ILembreteDAO lembreteDAO;
    private final MedicamentoController medicamentoController;

    // Injeção de Dependência para comunicação entre controllers
    public LembreteController(ILembreteDAO lembreteDAO, MedicamentoController medicamentoController) {
        this.lembreteDAO = lembreteDAO;
        this.medicamentoController = medicamentoController;
    }

    public boolean agendarLembrete(LocalTime horario, String frequencia, int idUsuario, int idMedicamento) {
        try {
            Lembrete novoLembrete = new Lembrete();
            novoLembrete.setHorario(horario);
            novoLembrete.setFrequencia(frequencia);
            novoLembrete.setIdUsuario(idUsuario);
            novoLembrete.setIdMedicamento(idMedicamento);
            novoLembrete.setStatus("PENDENTE");
            
            return lembreteDAO.salvar(novoLembrete);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Lembrete> listarLembretesDoUsuario(int idUsuario) {
        try {
            return lembreteDAO.listarPorUsuario(idUsuario);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public boolean adiarLembrete(int idLembrete) {
        try {
            // Adia por 5 minutos
            LocalTime horaDisparo = LocalTime.now().plusMinutes(5).withSecond(0).withNano(0);
            String novoStatus = "ADIADO_ATE_" + horaDisparo.toString();
            
            return lembreteDAO.mudarStatus(idLembrete, novoStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean mudarStatus(int idLembrete, String novoStatus) {
        try {
            return lembreteDAO.mudarStatus(idLembrete, novoStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método V2: Atualizado para decrementar estoque
    public boolean marcarComoTomado(int idLembrete, int idMedicamento) {
        try {
            // 1. Atualiza o status do lembrete
            boolean statusMudou = lembreteDAO.mudarStatus(idLembrete, "TOMADO");
            
            if (statusMudou) {
                // 2. Decrementa o estoque do remédio
                medicamentoController.decrementarEstoque(idMedicamento);
            }
            return statusMudou;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}