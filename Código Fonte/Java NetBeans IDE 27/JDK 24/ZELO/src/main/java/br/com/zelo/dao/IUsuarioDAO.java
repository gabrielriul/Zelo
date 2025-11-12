package br.com.zelo.dao;

import br.com.zelo.model.Usuario;

public interface IUsuarioDAO {
    
    boolean salvar(Usuario usuario) throws Exception;

    Usuario fazerLogin(String usuario, String senha) throws Exception;
    
    boolean excluir(int idUsuario) throws Exception;
}