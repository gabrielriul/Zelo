package br.com.zelo.view;

import br.com.zelo.controller.MedicamentoController;
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
        
        MedicamentoDAO medicamentoDAO = new MedicamentoDAO(); 
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
            new String [] {"ID", "Nome", "Dosagem", "Estoque"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblMedicamentos.setModel(model);
        for (Medicamento med : this.listaMedicamentosAtual) {
            model.addRow(new Object[]{
                med.getIdMedicamento(), med.getNome(), med.getDosagem(), med.getQuantidadeEstoque()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
        lblEstoque = new javax.swing.JLabel();
        lblAlerta = new javax.swing.JLabel();
        txtEstoque = new javax.swing.JTextField();
        txtAlerta = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ZELO - Gerenciar Medicamentos");

        tblMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {}, new String [] { "ID", "Nome", "Dosagem", "Estoque" }
        ));

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
        lblEstoque.setText("Qtd. em Estoque:");
        lblAlerta.setText("Alertar com (unid.):");

        txtInstrucoes.setColumns(20);
        txtInstrucoes.setRows(5);
        jScrollPane2.setViewportView(txtInstrucoes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblForma).addComponent(lblNome).addComponent(lblDosagem)
                    .addComponent(lblEstoque).addComponent(lblAlerta).addComponent(lblInstrucoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addComponent(txtDosagem).addComponent(txtForma)
                        .addComponent(txtEstoque).addComponent(txtAlerta)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblNome).addComponent(txtNome, -2, -2, -2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblDosagem).addComponent(txtDosagem, -2, -2, -2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblForma).addComponent(txtForma, -2, -2, -2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblEstoque).addComponent(txtEstoque, -2, -2, -2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(lblAlerta).addComponent(txtAlerta, -2, -2, -2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, -2, 100, -2).addComponent(lblInstrucoes))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> btnSalvarActionPerformed(evt));
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(evt -> btnExcluirActionPerformed(evt));
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(evt -> btnLimparActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addComponent(jScrollPane1, -1, 304, 32767).addGap(18, 18, 18).addComponent(jPanel1, -2, -2, -2))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(btnSalvar).addGap(18, 18, 18).addComponent(btnExcluir).addGap(18, 18, 18).addComponent(btnLimpar))).addContainerGap());
        layout.setVerticalGroup(
            layout.createSequentialGroup().addGap(40, 40, 40).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane1, -2, 0, 32767).addComponent(jPanel1, -1, -1, 32767)).addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnSalvar).addComponent(btnExcluir).addComponent(btnLimpar)).addContainerGap(-1, 32767));
        pack();
    }// </editor-fold>                        

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        String nome = txtNome.getText();
        String dosagem = txtDosagem.getText();
        String forma = txtForma.getText();
        String instrucoes = txtInstrucoes.getText();
        int estoque = 0, alerta = 0;
        
        try {
            if (!txtEstoque.getText().trim().isEmpty()) estoque = Integer.parseInt(txtEstoque.getText());
            if (!txtAlerta.getText().trim().isEmpty()) alerta = Integer.parseInt(txtAlerta.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Estoque/Alerta devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Medicamento medicamentoSalvo = null;
        boolean foiEdicao = false;
        
        if (this.medicamentoSelecionado == null) {
            medicamentoSalvo = controller.cadastrarMedicamento(nome, dosagem, forma, instrucoes, usuarioLogado, estoque, alerta);
        } else {
            this.medicamentoSelecionado.setNome(nome);
            this.medicamentoSelecionado.setDosagem(dosagem);
            this.medicamentoSelecionado.setForma(forma);
            this.medicamentoSelecionado.setInstrucoes(instrucoes);
            this.medicamentoSelecionado.setQuantidadeEstoque(estoque);
            this.medicamentoSelecionado.setQuantidadeAlerta(alerta);
            if(controller.editarMedicamento(this.medicamentoSelecionado)) {
                medicamentoSalvo = this.medicamentoSelecionado;
                foiEdicao = true;
            }
        }
        
        if (medicamentoSalvo != null) {
            String msg = foiEdicao ? "Atualizado!" : "Salvo!";
            JOptionPane.showMessageDialog(this, msg + "\nConfigure os horários agora.");
            carregarTabela(); limparCampos();
            TelaAgendamento telaAg = new TelaAgendamento((java.awt.Frame) SwingUtilities.getWindowAncestor(this), true, usuarioLogado, medicamentoSalvo);
            telaAg.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar. Verifique duplicidade.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }                                         

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        int linha = tblMedicamentos.getSelectedRow();
        if (linha == -1) return;
        int id = (int) tblMedicamentos.getValueAt(linha, 0);
        if (JOptionPane.showConfirmDialog(this, "Excluir?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (controller.excluirMedicamento(id)) {
                JOptionPane.showMessageDialog(this, "Excluído!");
                if (medicamentoSelecionado != null && id == medicamentoSelecionado.getIdMedicamento()) limparCampos();
                carregarTabela();
            }
        }
    }                                          

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) { limparCampos(); }                                          

    private void tblMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {
        int linha = tblMedicamentos.getSelectedRow();
        if (linha == -1) return;
        int id = (int) tblMedicamentos.getValueAt(linha, 0);
        for (Medicamento med : this.listaMedicamentosAtual) {
            if (med.getIdMedicamento() == id) {
                this.medicamentoSelecionado = med;
                txtNome.setText(med.getNome());
                txtDosagem.setText(med.getDosagem());
                txtForma.setText(med.getForma());
                txtInstrucoes.setText(med.getInstrucoes());
                txtEstoque.setText(String.valueOf(med.getQuantidadeEstoque()));
                txtAlerta.setText(String.valueOf(med.getQuantidadeAlerta()));
                break;
            }
        }
    }
    
    private void limparCampos() {
        txtNome.setText(""); txtDosagem.setText(""); txtForma.setText(""); txtInstrucoes.setText("");
        txtEstoque.setText(""); txtAlerta.setText("");
        tblMedicamentos.clearSelection();
        this.medicamentoSelecionado = null;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAlerta;
    private javax.swing.JLabel lblDosagem;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JLabel lblForma;
    private javax.swing.JLabel lblInstrucoes;
    private javax.swing.JLabel lblNome;
    private javax.swing.JTable tblMedicamentos;
    private javax.swing.JTextField txtAlerta;
    private javax.swing.JTextField txtDosagem;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtForma;
    private javax.swing.JTextArea txtInstrucoes;
    private javax.swing.JTextField txtNome;
    // End of variables declaration                   
}