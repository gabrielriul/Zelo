package br.com.zelo.view;

import br.com.zelo.controller.LembreteController;
import br.com.zelo.dao.ILembreteDAO;
import br.com.zelo.dao.LembreteDAO;
import br.com.zelo.model.Lembrete;
import br.com.zelo.model.Medicamento;
import br.com.zelo.model.Usuario;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class TelaAgendamento extends javax.swing.JDialog {
    // (Atributos, Construtor, configurarComponentes, carregarLembretes... 
    //  tudo isso permanece igual ao que fizemos antes)
    private final LembreteController controller;
    private final Medicamento medicamento;
    private final Usuario usuarioLogado;
    private DefaultListModel<String> listModel;

    public TelaAgendamento(java.awt.Frame parent, boolean modal, Usuario usuarioLogado, Medicamento medicamento) {
        super(parent, modal);
        
        ILembreteDAO dao = new LembreteDAO();
        this.controller = new LembreteController(dao);
        
        this.usuarioLogado = usuarioLogado;
        this.medicamento = medicamento;
        
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setTitle("ZELO - Agendar Horários"); // Título
        
        lblNomeMedicamento.setText("Agendamentos para: " + this.medicamento.getNome());
        configurarComponentes();
        carregarLembretes();
    }
    
    private void configurarComponentes() {
        listModel = new DefaultListModel<>();
        jListHorarios.setModel(listModel);
        
        cmbFrequencia.removeAllItems();
        
        cmbFrequencia.addItem("Diariamente");
        cmbFrequencia.addItem("A cada 12 horas");
        cmbFrequencia.addItem("A cada 8 horas");
        cmbFrequencia.addItem("A cada 6 horas");
        cmbFrequencia.addItem("Seg/Qua/Sex");
        cmbFrequencia.addItem("Ter/Qui");
        cmbFrequencia.addItem("Sábados");
        cmbFrequencia.addItem("Domingos");
        cmbFrequencia.addItem("Fins de Semana (Sab/Dom)");
    }

    private void carregarLembretes() {
        listModel.clear();
        
        List<Lembrete> todosLembretes = controller.listarLembretesDoUsuario(usuarioLogado.getIdUsuario());
        
        int contagem = 0;
        for (Lembrete lembrete : todosLembretes) {
            if (lembrete.getIdMedicamento() == medicamento.getIdMedicamento()) {
                String item = lembrete.getHorario().toString() + " - " + lembrete.getFrequencia();
                listModel.addElement(item);
                contagem++;
            }
        }
        
        if (contagem == 0) {
            listModel.addElement("Nenhum horário cadastrado.");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNomeMedicamento = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblHorario = new javax.swing.JLabel();
        txtHorario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbFrequencia = new javax.swing.JComboBox<>();
        btnAdicionarHorario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListHorarios = new javax.swing.JList<>();
        btnConcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ZELO - Agendar Horários");

        // Fonte corrigida (48 era muito grande)
        lblNomeMedicamento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNomeMedicamento.setText("Agendamentos para: [Medicamento]");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Agendamento (RF07)")); // Título adicionado

        lblHorario.setText("Horário (HH:mm):"); // Formato corrigido

        jLabel1.setText("Frequência:");

        // cmbFrequencia será populado pelo configurarComponentes()

        btnAdicionarHorario.setText("Adicionar Horário");
        // --- CORREÇÃO DO BUG ---
        btnAdicionarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarHorarioActionPerformed(evt);
            }
        });
        // --- FIM DA CORREÇÃO ---

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHorario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFrequencia, 0, 160, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnAdicionarHorario)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHorario)
                    .addComponent(txtHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cmbFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarHorario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // jListHorarios será populado pelo carregarLembretes()
        jScrollPane1.setViewportView(jListHorarios);

        btnConcluir.setText("Concluir");
        // --- CORREÇÃO DO BUG ---
        btnConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirActionPerformed(evt);
            }
        });
        // --- FIM DA CORREÇÃO ---

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNomeMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConcluir)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblNomeMedicamento)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnConcluir)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // (O método btnAdicionarHorarioActionPerformed estava correto e não muda)
    private void btnAdicionarHorarioActionPerformed(java.awt.event.ActionEvent evt) {
        String horarioStr = txtHorario.getText();
        String frequencia = (String) cmbFrequencia.getSelectedItem();
        
        try {
            LocalTime horario = LocalTime.parse(horarioStr);
            
            boolean sucesso = controller.agendarLembrete(
                    horario, 
                    frequencia, 
                    usuarioLogado.getIdUsuario(), 
                    medicamento.getIdMedicamento()
            );
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Horário adicionado!");
                carregarLembretes();
                txtHorario.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Este medicamento já está agendado para este horário.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de horário inválido. Use HH:mm (ex: 08:30 ou 14:00).", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnConcluirActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarHorario;
    private javax.swing.JButton btnConcluir;
    private javax.swing.JComboBox<String> cmbFrequencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListHorarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHorario;
    private javax.swing.JLabel lblNomeMedicamento;
    private javax.swing.JTextField txtHorario;
    // End of variables declaration//GEN-END:variables
}