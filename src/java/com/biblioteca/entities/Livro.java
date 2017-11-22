package com.biblioteca.entities;

public class Livro {
    private String titulo;
    private String tipo;
    private String descricao;
    private String isbn;

    public Livro(String titulo, String tipo, String descricao, String isbn) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
}
