package models;

import entities.Pessoa;
import jakarta.persistence.*;

import java.util.List;

public class PessoaModel {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");

    public void create(Pessoa pessoa) {
        System.out.println("Iniciando a transação create pessoa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(pessoa);
            tx.commit();
        } catch (PersistenceException e) {
            if (e.getCause() instanceof org.sqlite.SQLiteException &&
                    e.getCause().getMessage().contains("UNIQUE constraint failed")) {
                System.out.println("Erro: CPF já cadastrado.");
            } else {
                throw e;
            }
        } finally {
            em.close();
            System.out.println("Finalizando a transação create pessoa");
        }
    }

    public Pessoa findById(Pessoa p) {
        System.out.println("Iniciando a transação findById pessoa");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pessoa.class, p.getId());
        } finally {
            em.close();
            System.out.println("Finalizando a transação findById pessoa");
        }
    }

    public List<Pessoa> findAll() {
        System.out.println("Iniciando a transação findAll pessoa");
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        } finally {
            em.close();
            System.out.println("Finalizando a transação findAll pessoa");
        }
    }

    public void update(Pessoa p) {
        System.out.println("Iniciando a transação uptdate pessoa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(p);
            tx.commit();
        } finally {
            em.close();
            System.out.println("Finalizando a transação uptdate pessoa");
        }
    }

    public void delete(Pessoa p) {
        System.out.println("Iniciando a transação uptdate pessoa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            Pessoa pessoa = em.find(Pessoa.class, p.getId());
            if (pessoa != null) {
                tx.begin();
                em.remove(pessoa);
                tx.commit();
            }
        } finally {
            em.close();
            System.out.println("Finalizando a transação uptdate pessoa");
        }
    }
}
