package br.com.zelo.dao;

import br.com.zelo.model.Lembrete;
import java.util.List;

public interface ILembreteDAO {

    boolean salvar(Lembrete lembrete) throws Exception;
    
    boolean mudarStatus(int idLembrete, String novoStatus) throws Exception;

    List<Lembrete> listarPorUsuario(int idUsuario) throws Exception;
    
    boolean excluirPorMedicamento(int idMedicamento) throws Exception;
}