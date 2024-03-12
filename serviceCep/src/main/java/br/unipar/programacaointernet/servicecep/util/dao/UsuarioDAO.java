package br.unipar.programacaointernet.servicecep.util.dao;

import br.unipar.programacaointernet.servicecep.util.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(Usuario usuario);
    Usuario FindById(Long id);
    List<Usuario> findAll();
}
