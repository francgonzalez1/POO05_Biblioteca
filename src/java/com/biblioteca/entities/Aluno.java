package com.biblioteca.entities;

import com.biblioteca.db.Database;
import java.sql.Statement;

public class Aluno extends Pessoa {
    private String ra;

    public Aluno(String name, String cpf, String ra) {
        this.name = name;
        this.cpf = cpf;
        this.ra = ra;
    }
    
    
    
    public String getRa() {
        return ra;
    }
}
