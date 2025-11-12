package br.com.zelo.model;

import java.time.LocalTime;

public class Lembrete {

    private int idLembrete;
    private LocalTime horario;
    private String frequencia;
    private String status;
    private int idUsuario;
    private int idMedicamento;

    public Lembrete() {
    }

    // Getters
    public int getIdLembrete() {
        return idLembrete;
    }

    public LocalTime getHorario() {
        return horario;
    }
    
    public String getFrequencia() {
        return frequencia;
    }
    
    public String getStatus() {
        return status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    // Setters
    public void setIdLembrete(int idLembrete) {
        this.idLembrete = idLembrete;
    }
    
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
    
    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }
}