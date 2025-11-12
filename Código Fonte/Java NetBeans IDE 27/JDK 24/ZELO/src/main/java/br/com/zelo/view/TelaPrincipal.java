package br.com.zelo.view;

import br.com.zelo.controller.LembreteController;
import br.com.zelo.controller.MedicamentoController;
import br.com.zelo.controller.UsuarioController;
import br.com.zelo.dao.LembreteDAO;
import br.com.zelo.dao.MedicamentoDAO;
import br.com.zelo.dao.UsuarioDAO;
import br.com.zelo.model.Lembrete;
import br.com.zelo.model.Medicamento;
import br.com.zelo.model.Usuario;
import br.com.zelo.util.VerificadorFrequencia;
import br.com.zelo.service.AgendadorServico;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelaPrincipal extends javax.swing.JFrame {

    private Usuario usuarioLogado;
    
    private final LembreteController lembreteController;
    private final MedicamentoController medicamentoController;
    private DefaultListModel<String> listModel;
    private final AgendadorServico agendador;
    
    private final UsuarioController usuarioController; // Para a exclusão

    public TelaPrincipal(Usuario usuarioLogado) {
        initComponents();
        this.setLocationRelativeTo(null);

        this.usuarioLogado = usuarioLogado;
        
        this.lembreteController = new LembreteController(new LembreteDAO());
        this.medicamentoController = new MedicamentoController(new MedicamentoDAO());
        
        this.usuarioController = new UsuarioController(new UsuarioDAO());
        
        this.listModel = new DefaultListModel<>();
        this.jListLembretes.setModel(listModel);
        
        lblBoasVindas.setText("Bem-vindo(a), " + this.usuarioLogado.getNomeCompleto() + "!");
        
        carregarLembretesDoDia();
        
        this.agendador = new AgendadorServico(usuarioLogado, this);
        this.agendador.iniciar();
    }
    
    public void carregarLembretesDoDia() {
        listModel.clear();
        
        List<Lembrete> todosLembretes = lembreteController.listarLembretesDoUsuario(usuarioLogado.getIdUsuario());
        List<Medicamento> todosMedicamentos = medicamentoController.listarMedicamentosDoUsuario(usuarioLogado);

        Map<Integer, String> mapaMedicamentos = new HashMap<>();
        for (Medicamento med : todosMedicamentos) {
            mapaMedicamentos.put(med.getIdMedicamento(), med.getNome());
        }
        
        int contagem = 0;
        
        for (Lembrete lembrete : todosLembretes) {
            if (VerificadorFrequencia.isParaHoje(lembrete)) {
                
                String nomeMed = mapaMedicamentos.getOrDefault(lembrete.getIdMedicamento(), "Remédio Desconhecido");
                
                String item = String.format("%s - %s (%s)", 
                        lembrete.getHorario().toString(), 
                        nomeMed, 
                        lembrete.getStatus()
                );
                
                listModel.addElement(item);
                contagem++;
            }
        }
        
        if (contagem == 0) {
            listModel.addElement("(Nenhum lembrete agendado para hoje)");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        lblBoasVindas = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListLembretes = new javax.swing.JList<>();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        menuItemMedicamentos = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuItemLogout = new javax.swing.JMenuItem();
        menuItemExcluir = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ZELO - Painel Principal");

        lblBoasVindas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblBoasVindas.setText("\"[Message]\"");

        jPanel.setToolTipText("");

        jListLembretes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListLembretes);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu2.setText("File");

        jMenu1.setText("Cadastros");

        menuItemMedicamentos.setText("Gerenciar Medicamentos");
        menuItemMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemMedicamentosActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemMedicamentos);

        jMenu2.add(jMenu1);

        jMenu4.setText("Sistema");

        menuItemLogout.setText("Logout");
        menuItemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemLogoutActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemLogout);

        menuItemExcluir.setText("Excluir Conta");
        menuItemExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExcluirActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemExcluir);

        jMenu2.add(jMenu4);

        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBoasVindas)
                        .addGap(0, 223, Short.MAX_VALUE))
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblBoasVindas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemMedicamentosActionPerformed
        TelaGerenciarMedicamentos telaMeds = new TelaGerenciarMedicamentos(this, true, usuarioLogado);
        telaMeds.setVisible(true);
        
        carregarLembretesDoDia();
    }//GEN-LAST:event_menuItemMedicamentosActionPerformed

    private void menuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemLogoutActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, 
                "Tem certeza que deseja desconectar?", 
                "Confirmar Logout", 
                JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            // 1. Parar o serviço de agendamento
            agendador.parar();
            
            // 2. Fechar a tela principal
            this.dispose();
            
            // 3. Abrir uma nova tela de login
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        }
    }//GEN-LAST:event_menuItemLogoutActionPerformed

    private void menuItemExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExcluirActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, 
                "ATENÇÃO!\nEsta ação é irreversível e excluirá todos os seus dados (medicamentos, agendamentos).\nTem certeza que deseja excluir sua conta?", 
                "Confirmar Exclusão de Conta", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            // Chama o controller para excluir
            boolean sucesso = usuarioController.excluirUsuario(usuarioLogado.getIdUsuario());

            if (sucesso) {
                JOptionPane.showMessageDialog(this, 
                        "Sua conta foi excluída com sucesso.", 
                        "Conta Excluída", 
                        JOptionPane.INFORMATION_MESSAGE);

                // Executa a mesma lógica do Logout
                agendador.parar();
                this.dispose();
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, 
                        "Ocorreu um erro ao tentar excluir sua conta.", 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_menuItemExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jListLembretes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JMenuItem menuItemExcluir;
    private javax.swing.JMenuItem menuItemLogout;
    private javax.swing.JMenuItem menuItemMedicamentos;
    // End of variables declaration//GEN-END:variables
}