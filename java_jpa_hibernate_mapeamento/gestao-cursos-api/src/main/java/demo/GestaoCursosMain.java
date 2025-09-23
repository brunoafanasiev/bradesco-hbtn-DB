package demo;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collections;

public class GestaoCursosMain {

    public static void main(String[] args) {
        System.out.println("🚀 Iniciando teste completo da aplicação de Gestão de Cursos...");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Criar professor
            Professor professor = new Professor();
            professor.setNomeCompleto("Prof. Ana Costa");
            em.persist(professor);

            // Criar curso
            Curso curso = new Curso();
            curso.setNome("Estrutura de Dados");
            curso.setProfessor(professor);

            // Criar material do curso
            MaterialCurso materialCurso = new MaterialCurso();
            materialCurso.setDescricao("Livro: Algoritmos em Java");
            materialCurso.setCurso(curso);
            curso.setMaterial(materialCurso);

            em.persist(curso);
            em.persist(materialCurso);

            // Criar aluno
            Aluno aluno = new Aluno();
            aluno.setNomeCompleto("Carlos Silva");

            // Criar endereço
            Endereco endereco = new Endereco();
            endereco.setLogradouro("Rua das Flores");
            endereco.setCidade("São Paulo");
            endereco.setAluno(aluno);
            aluno.getEnderecos().add(endereco);

            // Criar telefone
            Telefone telefone = new Telefone();
            telefone.setNumero("11988887777");
            telefone.setAluno(aluno);
            aluno.getTelefones().add(telefone);

            // Associar aluno ao curso
            aluno.setCursos(Collections.singletonList(curso));
            curso.setAlunos(Collections.singletonList(aluno));

            em.persist(aluno);

            em.getTransaction().commit();
            System.out.println("✅ Dados populados com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("❌ Erro ao popular dados: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}

