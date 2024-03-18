package br.unipar.programacaointernet.servicecep.util.dao;
import br.unipar.programacaointernet.servicecep.util.model.Endereco;
import br.unipar.programacaointernet.servicecep.util.util.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;

public class EnderecoDaoimpl implements EnderecoDAO{

    private EntityManager em = EntityManagerUtil.getManager();

    public EnderecoDaoimpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void save (Endereco endereco){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(endereco);
        transaction.commit();
        System.out.println("Endereco " + endereco.toString() + " salvo com sucesso!");
    }

    @Override
    public void update (Endereco endereco){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(endereco);
        transaction.commit();
        System.out.println("Endereco " + endereco.toString() + " salvo com sucesso!");
    }

    @Override
    public void delete(Endereco endereco){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(endereco);
        transaction.commit();
        System.out.println("Endereco " + endereco.toString() + " Deletado com sucesso!");

    }

    @Override
    public Endereco FindById(Long id){
        return em.find(Endereco.class, id);
    }

    @Override
    public List<Endereco> findAll() {
        return em.createQuery("SELECT u FROM Endereco u", Endereco.class).getResultList();
    }

    @Override
    public Endereco findByCep(String cep) {
        try {
            return em.createQuery("SELECT e FROM Endereco e WHERE cep = :c", Endereco.class).setParameter("c", cep).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
