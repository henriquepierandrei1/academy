package br.com.academy.model;

import br.com.academy.Enums.Curso;
import br.com.academy.Enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    @NotEmpty(message = "Não pode estar vazio!")
    private String nome;

    @NotNull(message = "Seleciona algum curso!")
    @Column(name = "curso")
    @Enumerated(EnumType.STRING)
    private Curso curso;

    @Column(name = "matricula")
    @NotNull(message = "Gere a matrícula!")
    private String matricula;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Seleciona algum status!")
    private Status status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
