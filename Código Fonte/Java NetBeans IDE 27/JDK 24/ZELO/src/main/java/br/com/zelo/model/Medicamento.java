package br.com.zelo.model;

public class Medicamento {

    private int idMedicamento;
    private String nome;
    private String dosagem;
    private String forma;
    private String instrucoes;
    private int idUsuario;

    public Medicamento() {
    }

    // Getters
    public int getIdMedicamento() {
        return idMedicamento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getDosagem() {
        return dosagem;
    }
    
    public String getForma() {
        return forma;
    }
    
    public String getInstrucoes() {
        return instrucoes;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    // Setters
    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }
    
    public void setForma(String forma) {
        this.forma = forma;
    }
    
    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}