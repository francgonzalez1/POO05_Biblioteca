/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.entities;

import java.util.Date;

/**
 *
 * @author MuriloXavier
 */
public class Aluguel {
    private int id;
    private int id_aluno;
    private int id_livro;
    private int id_bibliotecario;
    private Date dt_emprestimo;
    private Date dt_devolucao_prevista;
    private Date dt_devolucao_real;
    private boolean ic_finalizado;
    private double vl_multa;

    // Construtor para gerar aluguel (sem dt_devolucao_real e vl_multa).
    public Aluguel(int id, int id_aluno, int id_livro, int id_bibliotecario, Date dt_emprestimo, Date dt_devolucao_prevista, boolean ic_finalizado) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
        this.id_bibliotecario = id_bibliotecario;
        this.dt_emprestimo = dt_emprestimo;
        this.dt_devolucao_prevista = dt_devolucao_prevista;
        this.ic_finalizado = ic_finalizado;
    }
    
    // Construtor com tudo
    public Aluguel(int id, int id_aluno, int id_livro, int id_bibliotecario, Date dt_emprestimo, Date dt_devolucao_prevista, Date dt_devolucao_real, boolean ic_finalizado, double vl_multa) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
        this.id_bibliotecario = id_bibliotecario;
        this.dt_emprestimo = dt_emprestimo;
        this.dt_devolucao_prevista = dt_devolucao_prevista;
        this.dt_devolucao_real = dt_devolucao_real;
        this.ic_finalizado = ic_finalizado;
        this.vl_multa = vl_multa;
    }

    
}
