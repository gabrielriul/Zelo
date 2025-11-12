package br.com.zelo.view;

import br.com.zelo.controller.LembreteController;
import br.com.zelo.model.Lembrete;
import br.com.zelo.model.Medicamento;
import java.awt.Toolkit; // Para fazer o som de "beep"

public class AlertaUI extends javax.swing.JDialog {

    private final Lembrete lembrete;
    private final LembreteController lembreteController;

    public AlertaUI(java.awt.Frame parent, boolean modal, Lembrete lembrete, Medicamento medicamento, LembreteController controller) {
        super(parent, modal);
        
        this.lembrete = lembrete;
        this.lembreteController = controller;
        
        initComponents();
        this.setLocationRelativeTo(null);
        
        // Preenche os dados do Alerta
        lblNomeMedicamento.setText(medicamento.getNome());
        lblHora.setText(lembrete.getHorario().toString());
        
        // Formata e exibe os detalhes
        String detalhes = String.format("Dosagem: %s\nForma: %s\nInstruções: %s",
                medicamento.getDosagem(),
                medicamento.getForma(),
                medicamento.getInstrucoes()
        );
        txtDetalhes.setText(detalhes);
        
        // Dispara o som de alerta do sistema
        Toolkit.getDefaultToolkit().beep();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        setAlwaysOnTop(true); // Faz o pop-up pular na frente de tudo

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Hora de tomar seu remédio!");

        lblNomeMedicamento.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblNomeMedicamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomeMedicamento.setText("[Nome do Remédio]");

        lblHora.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora.setText("00:00");

        txtDetalhes.setEditable(false);
        txtDetalhes.setColumns(20);
        txtDetalhes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDetalhes.setRows(3);
        txtDetalhes.setText("Dosagem:\nForma:\nInstruções:");
        jScrollPane1.setViewportView(txtDetalhes);

        btnAdiar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdiar.setText("Adiar 5 min (RF12)");
        btnAdiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdiarActionPerformed(evt);
            }
        });

        btnJaTomei.setBackground(new java.awt.Color(0, 153, 51));
        btnJaTomei.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnJaTomei.setForeground(new java.awt.Color(255, 255, 255));
        btnJaTomei.setText("Já tomei (RF10)");
        btnJaTomei.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJaTomeiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdiar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnJaTomei, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(lblNomeMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeMedicamento)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdiar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnJaTomei, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdiarActionPerformed(java.awt.event.ActionEvent evt) {
        lembreteController.adiarLembrete(lembrete.getIdLembrete());
        this.dispose(); // Fecha o pop-up
    }

    private void btnJaTomeiActionPerformed(java.awt.event.ActionEvent evt) {
        lembreteController.marcarComoTomado(lembrete.getIdLembrete());
        this.dispose(); // Fecha o pop-up
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdiar;
    private javax.swing.JButton btnJaTomei;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNomeMedicamento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtDetalhes;
    // End of variables declaration//GEN-END:variables
}