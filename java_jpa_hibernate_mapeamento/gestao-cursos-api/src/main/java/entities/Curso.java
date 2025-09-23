package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Professor professor;

    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL)
    private MaterialCurso materialCurso;

    @ManyToMany
    @JoinTable(name = "curso_aluno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public MaterialCurso getMaterial() {
        return materialCurso;
    }

    public void setMaterial(MaterialCurso materialCurso) {
        this.materialCurso = materialCurso;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}

