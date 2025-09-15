package models;

import entities.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProdutoModel {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");

    public void create(Produto p) {
       /* EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação produto");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Produto criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação produto");
        }*/

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            System.out.println("Iniciando a transação produto");
            tx.begin();
            em.persist(p);
            tx.commit();
            System.out.println("Produto criado com sucesso !!!");
        }  catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar o produto !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação produto");
        }
    }

    public void update(Produto p) {
        System.out.println("Iniciando a transação update produto");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(p);
            tx.commit();
            System.out.println("Produto alterado com sucesso !!!");
        } finally {
            em.close();
            System.out.println("Finalizando a update transação produto");
        }
    }

    public void delete(Produto p) {
        System.out.println("Iniciando a transação delete produto");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            Produto produto = em.find(Produto.class, p.getId());
            if (produto != null) {
                tx.begin();
                em.remove(produto);
                tx.commit();
            }
        } finally {
            em.close();
            System.out.println("Finalizando a transação delete produto");
        }
    }

    public Produto findById(Produto p) {
        System.out.println("Iniciando a transação findById produto");
        EntityManager em = emf.createEntityManager();
        try {
            if (p.getId() != null) {
                return em.find(Produto.class, p.getId());
            } else {
                System.out.println("ID não pode ser nulo ao buscar entidade.");
                return null;
            }
        } finally {
            em.close();
            System.out.println("Finalizando a transação findById produto");
        }
    }

    public List<Produto> findAll() {
        System.out.println("Iniciando a transação findAll produto");
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
        } finally {
            em.close();
            System.out.println("Finalizando a transação findAll produto");
        }
    }
}
