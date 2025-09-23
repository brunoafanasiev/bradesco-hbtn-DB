package models;

import entities.Aluno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AlunoModel {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");

    public void create(Aluno aluno) {

        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            em.close();
            System.err.println("Erro ao criar um aluno !!!" + e.getMessage());
        } finally {
            em.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close();
        }
    }

    public List<Aluno> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Aluno aluno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Aluno alunoRef = em.find(Aluno.class, aluno.getId());
            if (alunoRef != null) {
                em.remove(alunoRef);
                em.getTransaction().commit();
                System.out.println("Aluno removido com sucesso!");
            } else {
                System.out.println("Aluno não encontrado.");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Erro ao remover aluno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
