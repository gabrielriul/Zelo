package br.com.zelo.view;

import br.com.zelo.controller.LembreteController;
import br.com.zelo.controller.MedicamentoController;
import br.com.zelo.dao.ILembreteDAO;
import br.com.zelo.dao.LembreteDAO;
import br.com.zelo.dao.MedicamentoDAO;
import br.com.zelo.model.Lembrete;
import br.com.zelo.model.Medicamento;
import br.com.zelo.model.Usuario;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class TelaAgendamento extends javax.swing.JDialog {

    private final LembreteController controller;
    private final Medicamento medicamento;
    private final Usuario usuarioLogado;
    private DefaultListModel<String> listModel;

    public TelaAgendamento(java.awt.Frame parent, boolean modal, Usuario usuarioLogado, Medicamento medicamento) {
        super(parent, modal);
        
        // Controller com injeção correta
        ILembreteDAO dao = new LembreteDAO();
        MedicamentoController medController = new MedicamentoController(new MedicamentoDAO());
        this.controller = new LembreteController(dao, medController);
        
        this.usuarioLogado = usuarioLogado;
        this.medicamento = medicamento;
        
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setTitle("ZELO - Agendar Horários");
        
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
        int cont = 0;
        for (Lembrete l : todosLembretes) {
            if (l.getIdMedicamento() == medicamento.getIdMedicamento()) {
                listModel.addElement(l.getHorario() + " - " + l.getFrequencia());
                cont++;
            }
        }
        if (cont == 0) listModel.addElement("Nenhum horário cadastrado.");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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

        lblNomeMedicamento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNomeMedicamento.setText("Agendamentos para: [Medicamento]");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Agendamento (RF07)"));

        lblHorario.setText("Horário (HH:mm):");
        jLabel1.setText("Frequência:");
        btnAdicionarHorario.setText("Adicionar Horário");
        
        // Listener Adicionado
        btnAdicionarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
            .addComponent(lblHorario).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtHorario, -2, 70, -2).addGap(18, 18, 18)
            .addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cmbFrequencia, 0, 160, 32767).addGap(18, 18, 18)
            .addComponent(btnAdicionarHorario).addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(lblHorario).addComponent(txtHorario, -2, -2, -2)
            .addComponent(jLabel1).addComponent(cmbFrequencia, -2, -2, -2)
            .addComponent(btnAdicionarHorario)).addContainerGap(-1, 32767))
        );

        jScrollPane1.setViewportView(jListHorarios);

        btnConcluir.setText("Concluir");
        // Listener Adicionado
        btnConcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup().addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, -1, -1, 32767).addComponent(lblNomeMedicamento, -1, -1, 32767)
            .addComponent(jScrollPane1).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(0, 0, 32767).addComponent(btnConcluir))).addGap(20, 20, 20)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addGap(20, 20, 20)
            .addComponent(lblNomeMedicamento).addGap(18, 18, 18)
            .addComponent(jPanel1, -2, -2, -2).addGap(18, 18, 18)
            .addComponent(jScrollPane1, -1, 130, 32767).addGap(18, 18, 18)
            .addComponent(btnConcluir).addGap(20, 20, 20)
        );
        pack();
    }// </editor-fold>                        

    private void btnAdicionarHorarioActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        try {
            LocalTime horario = LocalTime.parse(txtHorario.getText());
            if (controller.agendarLembrete(horario, (String)cmbFrequencia.getSelectedItem(), usuarioLogado.getIdUsuario(), medicamento.getIdMedicamento())) {
                JOptionPane.showMessageDialog(this, "Horário adicionado!");
                carregarLembretes();
                txtHorario.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ou duplicidade.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Horário inválido (HH:mm).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }                                                   

    private void btnConcluirActionPerformed(java.awt.event.ActionEvent evt) { this.dispose(); }                                           

    // Variables declaration - do not modify                     
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
    // End of variables declaration                   
}