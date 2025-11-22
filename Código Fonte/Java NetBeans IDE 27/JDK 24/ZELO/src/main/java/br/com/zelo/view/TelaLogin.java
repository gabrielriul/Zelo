package br.com.zelo.view;

import br.com.zelo.controller.UsuarioController;
import br.com.zelo.dao.IUsuarioDAO;
import br.com.zelo.dao.UsuarioDAO;
import br.com.zelo.model.Usuario;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {
    
    private final UsuarioController controller;

    public TelaLogin() {
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        this.controller = new UsuarioController(usuarioDAO);
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("ZELO - Acesso ao Sistema");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lbTitulo = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        lbMensagem = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ZELO - Acesso ao Sistema");

        lbTitulo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lbTitulo.setText("ZELO");

        lbUsuario.setText("Usuário:");

        lbSenha.setText("Senha:");

        lbMensagem.setText("Não tem cadastro? É só clicar no botão ao lado ->");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(142, 142, 142))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbSenha)
                            .addComponent(lbUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario)
                            .addComponent(txtSenha)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lbMensagem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCadastrar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addGap(159, 159, 159))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(94, 94, 94)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(lbMensagem))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String usuarioLogin = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        Usuario usuarioLogado = controller.tentarLogin(usuarioLogin, senha);

        if (usuarioLogado != null) {
            this.dispose(); 
            TelaPrincipal telaPrincipal = new TelaPrincipal(usuarioLogado);
            telaPrincipal.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Usuário ou senha inválidos.", 
                    "Erro de Login", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        TelaCadastro telacadastro = new TelaCadastro();
        telacadastro.setVisible(true);
    }                                            

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            
            // --- TEMA GLOBAL (ACESSIBILIDADE RNF01/02) ---
            java.awt.Font fontePadrao = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16);
            java.awt.Font fonteBold = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16);
            java.awt.Color corFundo = new java.awt.Color(245, 245, 245);
            java.awt.Color corTexto = new java.awt.Color(10, 10, 10);
            java.awt.Color corInput = java.awt.Color.WHITE;

            javax.swing.UIManager.put("Label.font", fontePadrao);
            javax.swing.UIManager.put("Label.foreground", corTexto);
            javax.swing.UIManager.put("Button.font", fonteBold);
            javax.swing.UIManager.put("TextField.font", fontePadrao);
            javax.swing.UIManager.put("TextField.background", corInput);
            javax.swing.UIManager.put("PasswordField.font", fontePadrao);
            javax.swing.UIManager.put("TextArea.font", fontePadrao);
            javax.swing.UIManager.put("Panel.background", corFundo);
            javax.swing.UIManager.put("OptionPane.messageFont", fontePadrao);
            javax.swing.UIManager.put("OptionPane.buttonFont", fonteBold);
            javax.swing.UIManager.put("Table.font", fontePadrao);
            javax.swing.UIManager.put("Table.rowHeight", 28);
            javax.swing.UIManager.put("TableHeader.font", fonteBold);
            javax.swing.UIManager.put("List.font", fontePadrao);
            javax.swing.UIManager.put("List.fixedCellHeight", 28);
            // ---------------------------------------------

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // Garante criação das tabelas
        br.com.zelo.dao.ConexaoBD.inicializarBanco();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lbMensagem;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration                   
}