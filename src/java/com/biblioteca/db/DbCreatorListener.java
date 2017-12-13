/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.db;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 *
 * @author Murilo Xavier
 */
public class DbCreatorListener implements ServletContextListener{
    private void createTableAluno(Statement statement){
        try{
            statement.execute("CREATE TABLE aluno("
            + "cd_aluno INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
            + ", nm_aluno VARCHAR(255)"
            + ", cpf_aluno VARCHAR(255)"
            + ", ra_aluno VARCHAR(255)"
            + ", nm_email_aluno VARCHAR(255)"
            + ")");
        }catch(Exception exception){
            System.out.println("ERRO: [class:DbCreatorListener][method:createTableAluno]" + exception.getMessage());
        }
    }
    private void createTableLivro(Statement statement){
        try{
            statement.execute("CREATE TABLE livro("
            + "cd_livro INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
            + ", nm_livro VARCHAR(255)"
            + ", nm_tipo_livro VARCHAR(255)"
            + ", ds_livro VARCHAR(255)"
            + ", cd_isbn_livro VARCHAR(255)"
            + ")");
        }catch(Exception exception){
            System.out.println("ERRO: [class:DbCreatorListener][method:createTableLivro]: " + exception.getMessage());
        }
    }
    private void createTableBibliotecario(Statement statement){
        try{
            statement.execute("CREATE TABLE bibliotecario("
            + "cd_bibliotecario INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
            + ", nm_bibliotecario VARCHAR(255)"
            + ", nm_login_bibliotecario VARCHAR(45)"
            + ", cd_password_bibliotecario VARCHAR(255)"
            + ", cd_cpf_bibliotecario VARCHAR(255)"
            + ", nm_email_bibliotecario VARCHAR(255)"
            + ")");
            statement.execute("INSERT INTO bibliotecario VALUES (default, 'Administrador', 'admin', '"+"admin".hashCode()+"', ' - ', 'admin@admin.com')");
        }catch(Exception exception){
            System.out.println("ERRO: [class:DbCreatorListener][method:createTableBibliotecario]: " + exception.getMessage());
        }
    }
    private void createTableAluguelLivro(Statement statement){
        try{
            statement.execute("CREATE TABLE aluguel("
            + "cd_aluguel_livro INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)"
            + ", cd_aluno INTEGER"
            + ", cd_livro INTEGER"
            + ", cd_bibliotecario INTEGER"
            + ", dt_emprestimo_livro VARCHAR(255)"
            + ", dt_devolucao_prevista_livro VARCHAR(255)"
            + ", dt_devolucao_real_livro VARCHAR(255)"
            + ", vl_multa_emprestimo DECIMAL(10,2)"
            + ", CONSTRAINT alu_fkA FOREIGN KEY (cd_aluno) REFERENCES aluno(cd_aluno)"
            + ", CONSTRAINT alu_fkL FOREIGN KEY (cd_livro) REFERENCES livro(cd_livro)"
            + ", CONSTRAINT alu_fkB FOREIGN KEY (cd_bibliotecario) REFERENCES bibliotecario(cd_bibliotecario)"
                    + ")");
        }catch(Exception exception){
            System.out.println("ERRO: [class:DbCreatorListener][method:createTableAluguelLivro]: " + exception.getMessage());
        }
    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String url = "jdbc:derby:C:/Biblioteca/db;create=true";
            Connection connection =DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            createTableAluno(statement);
            createTableLivro(statement);
            createTableBibliotecario(statement);
            createTableAluguelLivro(statement);
            statement.close();
            connection.close();
            DriverManager.getConnection("jdbc:derby:C:/Biblioteca/db;shutdown=true");
        }catch(Exception exception){
            System.out.println("ERRO: [class:DbCreatorListener][method:contextInitialized]: " + exception.getMessage());
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent){
        // --
    }
}