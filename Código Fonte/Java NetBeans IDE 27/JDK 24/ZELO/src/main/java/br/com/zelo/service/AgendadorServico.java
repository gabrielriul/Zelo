package br.com.zelo.service;

import br.com.zelo.controller.LembreteController;
import br.com.zelo.controller.MedicamentoController;
import br.com.zelo.dao.LembreteDAO;
import br.com.zelo.dao.MedicamentoDAO;
import br.com.zelo.model.Lembrete;
import br.com.zelo.model.Medicamento;
import br.com.zelo.model.Usuario;
import br.com.zelo.util.VerificadorFrequencia;
import br.com.zelo.view.AlertaUI;
import br.com.zelo.view.TelaPrincipal;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class AgendadorServico {

    private final Timer timer;
    private final Usuario usuarioLogado;
    private final LembreteController lembreteController;
    private final MedicamentoController medicamentoController;
    private final TelaPrincipal telaPrincipal;
    
    // Controle para não spamar alertas de estoque
    private final Set<Integer> estoqueAlertadoNestaSessao;

    public AgendadorServico(Usuario usuarioLogado, TelaPrincipal telaPrincipal) {
        this.usuarioLogado = usuarioLogado;
        this.telaPrincipal = telaPrincipal;
        
        this.medicamentoController = new MedicamentoController(new MedicamentoDAO());
        this.lembreteController = new LembreteController(new LembreteDAO(), this.medicamentoController);
        
        this.timer = new Timer(true);
        this.estoqueAlertadoNestaSessao = new HashSet<>();
    }

    public void iniciar() {
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                verificarLembretes();
                verificarEstoque();
            }
        };
        // Verifica a cada 10 segundos
        timer.scheduleAtFixedRate(tarefa, 5000L, 10000L);
    }

    public void parar() {
        timer.cancel();
    }

    private void verificarLembretes() {
        LocalTime agora = LocalTime.now().withSecond(0).withNano(0);
        
        try {
            List<Lembrete> lembretes = lembreteController.listarLembretesDoUsuario(usuarioLogado.getIdUsuario());
            
            // Mapeia medicamentos para acesso rápido
            Map<Integer, Medicamento> mapaMeds = new HashMap<>();
            for(Medicamento m : medicamentoController.listarMedicamentosDoUsuario(usuarioLogado)) {
                mapaMeds.put(m.getIdMedicamento(), m);
            }

            for (Lembrete l : lembretes) {
                boolean disparar = false;
                
                // Lógica para status PENDENTE
                if (l.getStatus().equals("PENDENTE") && 
                    VerificadorFrequencia.isParaHoje(l) && 
                    l.getHorario().equals(agora)) {
                    disparar = true;
                }
                // Lógica para status ADIADO
                else if (l.getStatus().startsWith("ADIADO_ATE_")) {
                    LocalTime horaAdiada = LocalTime.parse(l.getStatus().substring(11));
                    if (horaAdiada.equals(agora)) {
                        disparar = true;
                    }
                }

                if (disparar) {
                    lembreteController.mudarStatus(l.getIdLembrete(), "ACIONADO");
                    
                    Medicamento med = mapaMeds.get(l.getIdMedicamento());
                    if (med != null) {
                        dispararAlertaVisual(l, med);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verificarEstoque() {
        try {
            List<Medicamento> meds = medicamentoController.listarMedicamentosDoUsuario(usuarioLogado);
            
            for (Medicamento m : meds) {
                boolean estoqueBaixo = m.getQuantidadeEstoque() <= m.getQuantidadeAlerta();
                boolean deveAlertar = m.getQuantidadeEstoque() > 0 && m.getQuantidadeAlerta() > 0;
                boolean naoAlertado = !estoqueAlertadoNestaSessao.contains(m.getIdMedicamento());
                
                if (estoqueBaixo && deveAlertar && naoAlertado) {
                    estoqueAlertadoNestaSessao.add(m.getIdMedicamento());
                    
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(telaPrincipal, 
                                "Estoque baixo para o medicamento: " + m.getNome(), 
                                "ZELO - Alerta de Estoque", 
                                JOptionPane.WARNING_MESSAGE);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void dispararAlertaVisual(Lembrete l, Medicamento m) {
        SwingUtilities.invokeLater(() -> {
            AlertaUI alerta = new AlertaUI(telaPrincipal, true, l, m, lembreteController);
            alerta.setVisible(true);
            
            if (telaPrincipal != null) {
                telaPrincipal.carregarLembretesDoDia();
            }
        });
    }
}