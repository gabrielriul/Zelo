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
    private final UsuarioController usuarioController;
    private DefaultListModel<String> listModel;
    private final AgendadorServico agendador;

    public TelaPrincipal(Usuario usuarioLogado) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("ZELO - Painel Principal");

        this.usuarioLogado = usuarioLogado;
        
        // Injeção de Dependência Cruzada
        this.medicamentoController = new MedicamentoController(new MedicamentoDAO());
        this.lembreteController = new LembreteController(new LembreteDAO(), this.medicamentoController);
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
        Map<Integer, String> mapaMeds = new HashMap<>();
        for (Medicamento med : todosMedicamentos) mapaMeds.put(med.getIdMedicamento(), med.getNome());
        
        int cont = 0;
        for (Lembrete l : todosLembretes) {
            if (VerificadorFrequencia.isParaHoje(l)) {
                String nome = mapaMeds.getOrDefault(l.getIdMedicamento(), "Remédio");
                listModel.addElement(String.format("%s - %s (%s)", l.getHorario(), nome, l.getStatus()));
                cont++;
            }
        }
        if (cont == 0) listModel.addElement("(Nenhum lembrete para hoje)");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblBoasVindas = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListLembretes = new javax.swing.JList<>();
        jMenuBar = new javax.swing.JMenuBar();
        menuCadastros = new javax.swing.JMenu();
        menuItemMedicamentos = new javax.swing.JMenuItem();
        menuSistema = new javax.swing.JMenu();
        menuItemLogout = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuItemExcluir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ZELO - Painel Principal");

        lblBoasVindas.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblBoasVindas.setText("Bem-vindo(a), [Usuário]");

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lembretes de Hoje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jScrollPane1.setViewportView(jListLembretes);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, -1, 376, 32767).addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup().addComponent(jScrollPane1, -1, 171, 32767).addContainerGap())
        );

        menuCadastros.setText("Cadastros");
        menuItemMedicamentos.setText("Gerenciar Medicamentos");
        menuItemMedicamentos.addActionListener(evt -> menuItemMedicamentosActionPerformed(evt));
        menuCadastros.add(menuItemMedicamentos);
        jMenuBar.add(menuCadastros);

        menuSistema.setText("Sistema");
        menuItemLogout.setText("Logout (Desconectar)");
        menuItemLogout.addActionListener(evt -> menuItemLogoutActionPerformed(evt));
        menuSistema.add(menuItemLogout);
        menuSistema.add(jSeparator1);
        menuItemExcluir.setForeground(new java.awt.Color(204, 0, 0));
        menuItemExcluir.setText("Excluir Minha Conta");
        menuItemExcluir.addActionListener(evt -> menuItemExcluirActionPerformed(evt));
        menuSistema.add(menuItemExcluir);
        jMenuBar.add(menuSistema);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, -1, -1, 32767)
            .addComponent(lblBoasVindas, -1, -1, 32767)).addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
            .addComponent(lblBoasVindas).addGap(18, 18, 18)
            .addComponent(jPanel, -1, -1, 32767).addGap(20, 20, 20))
        );
        pack();
    }// </editor-fold>                        

    private void menuItemMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        TelaGerenciarMedicamentos telaMeds = new TelaGerenciarMedicamentos(this, true, usuarioLogado);
        telaMeds.setVisible(true);
        carregarLembretesDoDia();
    }                                                    

    private void menuItemLogoutActionPerformed(java.awt.event.ActionEvent evt) {                                               
        if (JOptionPane.showConfirmDialog(this, "Sair do sistema?", "Logout", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            agendador.parar();
            this.dispose();
            new TelaLogin().setVisible(true);
        }
    }                                              

    private void menuItemExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if (JOptionPane.showConfirmDialog(this, "ATENÇÃO! Excluir conta apagará todos os dados. Continuar?", "Excluir Conta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            if (usuarioController.excluirUsuario(usuarioLogado.getIdUsuario())) {
                JOptionPane.showMessageDialog(this, "Conta excluída.");
                agendador.parar();
                this.dispose();
                new TelaLogin().setVisible(true);
            }
        }
    }                                               

    // Variables declaration - do not modify                     
    private javax.swing.JList<String> jListLembretes;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblBoasVindas;
    private javax.swing.JMenu menuCadastros;
    private javax.swing.JMenuItem menuItemExcluir;
    private javax.swing.JMenuItem menuItemLogout;
    private javax.swing.JMenuItem menuItemMedicamentos;
    private javax.swing.JMenu menuSistema;
    // End of variables declaration                   
}