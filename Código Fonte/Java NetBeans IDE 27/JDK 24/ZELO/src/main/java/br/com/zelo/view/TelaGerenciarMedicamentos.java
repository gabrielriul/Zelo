package br.com.zelo.view;

import br.com.zelo.controller.MedicamentoController;
import br.com.zelo.dao.IMedicamentoDAO;
import br.com.zelo.dao.MedicamentoDAO;
import br.com.zelo.model.Medicamento;
import br.com.zelo.model.Usuario;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class TelaGerenciarMedicamentos extends javax.swing.JDialog {

    private final MedicamentoController controller;
    private final Usuario usuarioLogado;
    private List<Medicamento> listaMedicamentosAtual;
    private Medicamento medicamentoSelecionado = null;
    
    public TelaGerenciarMedicamentos(java.awt.Frame parent, boolean modal, Usuario usuarioLogado) {
        super(parent, modal);
        
        IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        this.controller = new MedicamentoController(medicamentoDAO);
        this.usuarioLogado = usuarioLogado;
        
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setTitle("ZELO - Gerenciar Medicamentos");
        
        carregarTabela();
    }
    
    private void carregarTabela() {
        this.listaMedicamentosAtual = controller.listarMedicamentosDoUsuario(usuarioLogado);
        
        DefaultTableModel model = new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "Dosagem", "Forma"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblMedicamentos.setModel(model);

        for (Medicamento med : this.listaMedicamentosAtual) {
            model.addRow(new Object[]{
                med.getIdMedicamento(),
                med.getNome(),
                med.getDosagem(),
                med.getForma()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicamentos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblDosagem = new javax.swing.JLabel();
        lblForma = new javax.swing.JLabel();
        lblInstrucoes = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtDosagem = new javax.swing.JTextField();
        txtForma = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInstrucoes = new javax.swing.JTextArea();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ZELO - Gerenciar Medicamentos");

        tblMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Nome", "Dosagem", "Forma"
            }
        ));
        
        // Listener de clique CORRETO (na tabela)
        tblMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedicamentos);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Medicamento"));

        lblNome.setText("Nome (*):");

        lblDosagem.setText("Dosagem:");

        lblForma.setText("Forma:");

        lblInstrucoes.setText("Instruções:");

        txtInstrucoes.setColumns(20);
        txtInstrucoes.setRows(5);
        jScrollPane2.setViewportView(txtInstrucoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblInstrucoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblForma)
                            .addComponent(lblNome)
                            .addComponent(lblDosagem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDosagem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtForma, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDosagem)
                    .addComponent(txtDosagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForma)
                    .addComponent(txtForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInstrucoes)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnLimpar)
                    .addComponent(btnExcluir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String nome = txtNome.getText();
        String dosagem = txtDosagem.getText();
        String forma = txtForma.getText();
        String instrucoes = txtInstrucoes.getText();
        
        Medicamento medicamentoSalvo = null;
        boolean foiEdicao = false;
        
        if (this.medicamentoSelecionado == null) {
            medicamentoSalvo = controller.cadastrarMedicamento(nome, dosagem, forma, instrucoes, usuarioLogado);
            foiEdicao = false;
            
        } else {
            this.medicamentoSelecionado.setNome(nome);
            this.medicamentoSelecionado.setDosagem(dosagem);
            this.medicamentoSelecionado.setForma(forma);
            this.medicamentoSelecionado.setInstrucoes(instrucoes);
            
            boolean sucessoEdicao = controller.editarMedicamento(this.medicamentoSelecionado);
            if (sucessoEdicao) {
                medicamentoSalvo = this.medicamentoSelecionado;
                foiEdicao = true;
            }
        }
        
        if (medicamentoSalvo != null) {
            
            String feedback = foiEdicao ? "Medicamento atualizado!" : "Medicamento salvo!";
            JOptionPane.showMessageDialog(this, feedback + "\nAgora, configure os horários.");
            
            carregarTabela();
            limparCampos();
            
            TelaAgendamento telaAg = new TelaAgendamento(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this),
                    true, 
                    usuarioLogado, 
                    medicamentoSalvo
            );
            telaAg.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique se o nome está preenchido ou se já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linhaSelecionada = tblMedicamentos.getSelectedRow();
        
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um medicamento na tabela para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idParaExcluir = (int) tblMedicamentos.getValueAt(linhaSelecionada, 0);

        if (this.medicamentoSelecionado != null && idParaExcluir == this.medicamentoSelecionado.getIdMedicamento()) {
            limparCampos();
        }
        
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este medicamento?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            boolean sucesso = controller.excluirMedicamento(idParaExcluir);
            
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Medicamento excluído com sucesso!");
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir medicamento.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {
        int linhaSelecionada = tblMedicamentos.getSelectedRow();
        
        if (linhaSelecionada == -1) {
            return;
        }
        
        int idMedicamento = (int) tblMedicamentos.getValueAt(linhaSelecionada, 0);

        for (Medicamento med : this.listaMedicamentosAtual) {
            if (med.getIdMedicamento() == idMedicamento) {
                this.medicamentoSelecionado = med;
                break;
            }
        }
        
        if (this.medicamentoSelecionado != null) {
            txtNome.setText(this.medicamentoSelecionado.getNome());
            txtDosagem.setText(this.medicamentoSelecionado.getDosagem());
            txtForma.setText(this.medicamentoSelecionado.getForma());
            txtInstrucoes.setText(this.medicamentoSelecionado.getInstrucoes());
        }
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtDosagem.setText("");
        txtForma.setText("");
        txtInstrucoes.setText("");
        tblMedicamentos.clearSelection();
        this.medicamentoSelecionado = null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDosagem;
    private javax.swing.JLabel lblForma;
    private javax.swing.JLabel lblInstrucoes;
    private javax.swing.JLabel lblNome;
    private javax.swing.JTable tblMedicamentos;
    private javax.swing.JTextField txtDosagem;
    private javax.swing.JTextField txtForma;
    private javax.swing.JTextArea txtInstrucoes;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}