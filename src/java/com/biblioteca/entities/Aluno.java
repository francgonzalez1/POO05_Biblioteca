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

    public void setRa(String ra) {
        this.ra = ra;
    }
    //Exemplificação de como executar um insert (o método executeUpdate deve ser substituido por executeQuery
    // no caso de uma consulta! Ex: statement.executeQuery(SELECT * FROM aluno WHERE ...); ).
    public void insertAluno(String name, String cpf, String ra){
        try{
            Statement statement = Database.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO aluno VALUES (default,"
                    + "'"+name+"'"
                    + "'"+cpf +"'"
                    + "'"+ra  +"'"
                    + ");");
            statement.close();
        }catch(Exception exception){
            System.out.println("ERRO: [class:Aluno][method:insertAluno]" + exception.getMessage());
        }
    }
}
