package br.com.zelo.view;

import br.com.zelo.controller.LembreteController;
import br.com.zelo.model.Lembrete;
import br.com.zelo.model.Medicamento;
import java.awt.Toolkit;

public class AlertaUI extends javax.swing.JDialog {

    private final Lembrete lembrete;
    private final LembreteController lembreteController;

    public AlertaUI(java.awt.Frame parent, boolean modal, Lembrete lembrete, Medicamento medicamento, LembreteController controller) {
        super(parent, modal);
        this.lembrete = lembrete;
        this.lembreteController = controller;
        initComponents();
        this.setLocationRelativeTo(null);
        
        lblNomeMedicamento.setText(medicamento.getNome());
        lblHora.setText(lembrete.getHorario().toString());
        txtDetalhes.setText(String.format("Dosagem: %s\nForma: %s\nInstruções: %s", medicamento.getDosagem(), medicamento.getForma(), medicamento.getInstrucoes()));
        
        Toolkit.getDefaultToolkit().beep();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNomeMedicamento = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetalhes = new javax.swing.JTextArea();
        btnAdiar = new javax.swing.JButton();
        btnJaTomei = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ZELO - HORA DO REMÉDIO!");
        setAlwaysOnTop(true);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Hora de tomar seu remédio!");

        lblNomeMedicamento.setFont(new java.awt.Font("Segoe UI", 1, 36));
        lblNomeMedicamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomeMedicamento.setText("[Nome]");

        lblHora.setFont(new java.awt.Font("Segoe UI", 0, 36));
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora.setText("00:00");

        txtDetalhes.setEditable(false);
        txtDetalhes.setColumns(20);
        txtDetalhes.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtDetalhes.setRows(3);
        jScrollPane1.setViewportView(txtDetalhes);

        btnAdiar.setFont(new java.awt.Font("Segoe UI", 0, 18));
        btnAdiar.setText("Adiar 5 minutos");
        btnAdiar.addActionListener(evt -> btnAdiarActionPerformed(evt));

        btnJaTomei.setBackground(new java.awt.Color(0, 153, 51));
        btnJaTomei.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnJaTomei.setForeground(new java.awt.Color(255, 255, 255));
        btnJaTomei.setText("Já tomei");
       
        btnJaTomei.setOpaque(true);
        btnJaTomei.setBorderPainted(false);
        btnJaTomei.addActionListener(evt -> btnJaTomeiActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(btnAdiar, -2, 200, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, 32767).addComponent(btnJaTomei, -2, 200, -2))
            .addComponent(lblTitulo, -1, -1, 32767).addComponent(jScrollPane1).addComponent(lblNomeMedicamento, -1, -1, 32767).addComponent(lblHora, -1, -1, 32767)).addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup().addGap(20, 20, 20).addComponent(lblTitulo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(lblHora).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblNomeMedicamento).addGap(18, 18, 18)
            .addComponent(jScrollPane1, -2, 80, -2).addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnAdiar, -2, 50, -2).addComponent(btnJaTomei, -2, 50, -2)).addContainerGap(20, 32767)
        );
        pack();
    }// </editor-fold>                        

    private void btnAdiarActionPerformed(java.awt.event.ActionEvent evt) {                                         
        lembreteController.adiarLembrete(lembrete.getIdLembrete());
        this.dispose();
    }                                        

    private void btnJaTomeiActionPerformed(java.awt.event.ActionEvent evt) {                                           
        lembreteController.marcarComoTomado(lembrete.getIdLembrete(), lembrete.getIdMedicamento());
        this.dispose();
    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAdiar;
    private javax.swing.JButton btnJaTomei;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNomeMedicamento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtDetalhes;
    // End of variables declaration                   
}