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
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

public class AgendadorServico {

    private final Timer timer;
    private final Usuario usuarioLogado;
    private final LembreteController lembreteController;
    private final MedicamentoController medicamentoController;
    private final TelaPrincipal telaPrincipal;

    public AgendadorServico(Usuario usuarioLogado, TelaPrincipal telaPrincipal) {
        this.usuarioLogado = usuarioLogado;
        this.telaPrincipal = telaPrincipal;
        this.lembreteController = new LembreteController(new LembreteDAO());
        this.medicamentoController = new MedicamentoController(new MedicamentoDAO());
        this.timer = new Timer(true);
    }

    public void iniciar() {
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                verificarAlarmes();
            }
        };
        timer.scheduleAtFixedRate(tarefa, 1000L, 60000L); 
    }

    public void parar() {
        timer.cancel();
    }

    private void verificarAlarmes() {
        LocalTime agora = LocalTime.now().withSecond(0).withNano(0);
        System.out.println("[AgendadorServico] Verificando... " + agora);

        try {
            List<Lembrete> todosLembretes = lembreteController.listarLembretesDoUsuario(usuarioLogado.getIdUsuario());
            Map<Integer, Medicamento> mapaMedicamentos = new HashMap<>();
            List<Medicamento> medicamentos = medicamentoController.listarMedicamentosDoUsuario(usuarioLogado);
            
            for (Medicamento med : medicamentos) {
                mapaMedicamentos.put(med.getIdMedicamento(), med);
            }
            
            for (Lembrete lembrete : todosLembretes) {
                
                boolean deveDisparar = false;
                boolean statusPendente = lembrete.getStatus().equals("PENDENTE");
                boolean statusAdiado = lembrete.getStatus().startsWith("ADIADO_ATE_");

                if (statusPendente && 
                    VerificadorFrequencia.isParaHoje(lembrete) &&
                    lembrete.getHorario().equals(agora)) {
                    
                    deveDisparar = true;
                
                } else if (statusAdiado) {
                    LocalTime horaAdiada = LocalTime.parse(lembrete.getStatus().substring(11));
                    if (horaAdiada.equals(agora)) {
                        deveDisparar = true;
                    }
                }
                
                if (deveDisparar) {
                    System.out.println("[AgendadorServico] ALARME DISPARADO PARA: " + lembrete.getIdLembrete());
                    
                    Medicamento med = mapaMedicamentos.get(lembrete.getIdMedicamento());
                    
                    if (med != null) {
                        // Muda status ANTES de disparar
                        lembreteController.mudarStatus(lembrete.getIdLembrete(), "ACIONADO");
                        dispararAlerta(lembrete, med);
                    } else {
                         System.err.println("Agendador: Medicamento " + lembrete.getIdMedicamento() + " não encontrado.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispararAlerta(Lembrete lembrete, Medicamento med) {
        SwingUtilities.invokeLater(() -> {
            AlertaUI popup = new AlertaUI(
                    telaPrincipal,
                    true, 
                    lembrete,
                    med,
                    lembreteController
            );
            popup.setVisible(true);
            
            if (telaPrincipal != null) {
                telaPrincipal.carregarLembretesDoDia();
            }
        });
    }
}