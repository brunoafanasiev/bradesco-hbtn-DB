package models;


import entities.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CursoModel {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");

    public void create(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Curso findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public List<Curso> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void delete(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Curso cursoRef = em.find(Curso.class, curso.getId());
            if (cursoRef != null) {
                em.remove(cursoRef);
                em.getTransaction().commit();
                System.out.println("Curso removido com sucesso!");
            } else {
                System.out.println("Curso não encontrado.");
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.err.println("Erro ao remover curso: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
