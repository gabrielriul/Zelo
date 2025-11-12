package br.com.zelo.controller;

import br.com.zelo.dao.ILembreteDAO;
import br.com.zelo.model.Lembrete;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LembreteController {

    private final ILembreteDAO lembreteDAO;

    public LembreteController(ILembreteDAO lembreteDAO) {
        this.lembreteDAO = lembreteDAO;
    }

    public boolean agendarLembrete(LocalTime horario, String frequencia, int idUsuario, int idMedicamento) {
        if (horario == null || idUsuario <= 0 || idMedicamento <= 0) {
            System.err.println("Controller: Horário, usuário ou medicamento inválidos.");
            return false;
        }
        try {
            Lembrete novoLembrete = new Lembrete();
            novoLembrete.setHorario(horario);
            novoLembrete.setFrequencia(frequencia);
            novoLembrete.setIdUsuario(idUsuario);
            novoLembrete.setIdMedicamento(idMedicamento);
            novoLembrete.setStatus("PENDENTE");
            return lembreteDAO.salvar(novoLembrete);
        } catch (Exception e) {
            System.err.println("Controller: Erro ao salvar lembrete.");
            e.printStackTrace();
            return false;
        }
    }

    public List<Lembrete> listarLembretesDoUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            return new ArrayList<>();
        }
        try {
            return lembreteDAO.listarPorUsuario(idUsuario);
        } catch (Exception e) {
            System.err.println("Controller: Erro ao listar lembretes.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public boolean marcarComoTomado(int idLembrete) {
        if (idLembrete <= 0) {
            return false;
        }
        try {
            return lembreteDAO.mudarStatus(idLembrete, "TOMADO");
        } catch (Exception e) {
            System.err.println("Controller: Erro ao marcar como tomado.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean adiarLembrete(int idLembrete) {
        if (idLembrete <= 0) {
            return false;
        }
        try {
            LocalTime horaDisparo = LocalTime.now().plusMinutes(5).withSecond(0).withNano(0);
            String novoStatus = "ADIADO_ATE_" + horaDisparo.toString();
            return lembreteDAO.mudarStatus(idLembrete, novoStatus);
        } catch (Exception e) {
            System.err.println("Controller: Erro ao adiar lembrete.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean mudarStatus(int idLembrete, String novoStatus) {
        if (idLembrete <= 0 || novoStatus == null || novoStatus.trim().isEmpty()) {
            return false;
        }
        
        try {
            return lembreteDAO.mudarStatus(idLembrete, novoStatus);
        } catch (Exception e) {
            System.err.println("Controller: Erro ao mudar status.");
            e.printStackTrace();
            return false;
        }
    }
}