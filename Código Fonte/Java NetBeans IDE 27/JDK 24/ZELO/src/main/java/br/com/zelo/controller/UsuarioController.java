package br.com.zelo.controller;

import br.com.zelo.dao.IUsuarioDAO;
import br.com.zelo.model.Usuario;

public class UsuarioController {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioController(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean cadastrarUsuario(String nomeCompleto, String cpf, String usuarioLogin, String senha) {
        if (usuarioLogin == null || usuarioLogin.trim().isEmpty() || 
            senha == null || senha.isEmpty() ||
            cpf == null || cpf.trim().isEmpty()) {
            System.err.println("Controller: Usuário, senha ou CPF estão vazios.");
            return false;
        }

        try {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNomeCompleto(nomeCompleto.trim());
            novoUsuario.setCPF(cpf.trim());
            novoUsuario.setUsuario(usuarioLogin.trim());
            novoUsuario.setSenha(senha); 

            return usuarioDAO.salvar(novoUsuario);

        } catch (Exception e) {
            System.err.println("Controller: Erro ao tentar salvar usuário.");
            e.printStackTrace();
            return false;
        }
    }

    public Usuario tentarLogin(String usuarioLogin, String senha) {
        if (usuarioLogin == null || usuarioLogin.trim().isEmpty() || senha == null || senha.isEmpty()) {
            System.err.println("Controller: Usuário ou senha estão vazios para login.");
            return null;
        }

        try {
            return usuarioDAO.fazerLogin(usuarioLogin.trim(), senha);
            
        } catch (Exception e) {
            System.err.println("Controller: Erro ao tentar fazer login.");
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean excluirUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            System.err.println("Controller: ID de usuário inválido para exclusão.");
            return false;
        }

        try {
            return usuarioDAO.excluir(idUsuario);
        } catch (Exception e) {
            System.err.println("Controller: Erro ao excluir usuário.");
            e.printStackTrace();
            return false;
        }
    }
}