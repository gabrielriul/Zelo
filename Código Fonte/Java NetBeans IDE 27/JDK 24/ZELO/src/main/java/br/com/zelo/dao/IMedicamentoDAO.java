package br.com.zelo.dao;

import br.com.zelo.model.Medicamento;
import java.util.List;

public interface IMedicamentoDAO {

    Medicamento salvar(Medicamento medicamento) throws Exception;

    boolean editar(Medicamento medicamento) throws Exception;

    boolean excluir(int idMedicamento) throws Exception;

    List<Medicamento> listarPorUsuario(int idUsuario) throws Exception;
}