package br.unipar.programacaointernet.servicecep.util.dao;

import br.unipar.programacaointernet.servicecep.util.model.Usuario;
import br.unipar.programacaointernet.servicecep.util.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class UsuarioDaoimpl implements UsuarioDAO {

    private EntityManager em = EntityManagerUtil.getManager();

    public UsuarioDaoimpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void save (Usuario usuario){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(usuario);
        transaction.commit();
        System.out.println("Usuario "+usuario.getNome()+ " salvo com sucesso!");
    }

    @Override
    public void update (Usuario usuario){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(usuario);
        transaction.commit();
        System.out.println("Usuario "+usuario.getNome()+ " salvo com sucesso!");
    }

    @Override
    public void delete(Usuario usuario){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(usuario);
        transaction.commit();
        System.out.println("Usuario " + usuario.getNome() + " Deletado com sucesso!");

    }

    @Override
    public Usuario FindById(Long id){
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }
}
