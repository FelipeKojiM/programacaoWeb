package br.unipar.programacaointernet.servicecep.util.dao;

import br.unipar.programacaointernet.servicecep.util.model.Endereco;
import br.unipar.programacaointernet.servicecep.util.model.Usuario;

import java.util.List;

public interface EnderecoDAO {

    void save(Endereco endereco);
    void update(Endereco endereco);
    void delete(Endereco endereco);
    Endereco FindById(Long id);
    List<Endereco> findAll();
}
