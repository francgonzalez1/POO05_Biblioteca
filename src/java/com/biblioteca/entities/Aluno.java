package com.biblioteca.entities;

import com.biblioteca.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
    private String ra;

    public Aluno(int id, String name, String cpf, String ra, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.ra = ra;
        this.email = email;
    }
    
    public static Aluno getAluno(int id){
        Aluno aluno = null;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aluno WHERE cd_aluno = "+id+"");
            if(resultSet.next()){
                aluno = new Aluno(resultSet.getInt("cd_aluno"),
                                  resultSet.getString("nm_aluno"),
                                  resultSet.getString("cpf_aluno"),
                                  resultSet.getString("ra_aluno"),
                                  resultSet.getString("nm_email_aluno"));
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluno][catch:getAluno]: "+exception.getMessage());
        }
        return aluno;
    }
    
    public static List<Aluno> getAlunos(){
        ArrayList<Aluno> alunos = null;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aluno");
            while(resultSet.next()){
                Aluno aluno = new Aluno(resultSet.getInt("cd_aluno"),
                                  resultSet.getString("nm_aluno"),
                                  resultSet.getString("cpf_aluno"),
                                  resultSet.getString("ra_aluno"),
                                  resultSet.getString("nm_email_aluno"));
                alunos.add(aluno);
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluno][catch:getAlunos]: "+exception.getMessage());            
        }
        return alunos;
    }
    
    
    public static List<Object[]> getAllAlunos(){
        ArrayList<Object[]> alunos = new ArrayList<>();
        Object[] alunoR;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aluno");
            while(resultSet.next()){
                alunoR = new Object[5];
                for(int i = 0; i < 5; i++){
                    alunoR[i] = resultSet.getString(i + 1);
                }
                alunos.add(alunoR);
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getLivros]: "+exception.getMessage());
        }
        return alunos;
    }
    
    public static String setAluno(String name, String cpf, String ra, String email) throws SQLException{
        if(validateCpf(cpf) && verifyEmail(email)){
            String SQL = "INSERT INTO aluno VALUES (default, ?, ?, ?, ?)";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, name);
            statement.setString(2, cpf);
            statement.setString(3, ra);
            statement.setString(4, email);
            statement.execute();
            statement.close();
            return "Cadastro efetuado com sucesso";
        }else{
            if(!validateCpf(cpf))
                return "Cpf inválido";
            else
                return "Email inválido";
        }
    }
    
    public static String updateAluno(int id, String name, String cpf, String ra, String email) throws SQLException{
        if(validateCpf(cpf) && verifyEmail(email)){
            String SQL = "UPDATE aluno SET"
                    + "nm_aluno = ?"
                    + ", cpf_aluno = ?"
                    + ", ra_aluno = ?"
                    + ", nm_email_aluno = ?"
                    + "WHERE cd_aluno = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, name);
            statement.setString(2, cpf);
            statement.setString(3, ra);
            statement.setString(4, email);
            statement.execute();
            statement.close();
            return "Cadastro atualizado com sucesso";
        }else{
            if(!validateCpf(cpf))
                return "Cpf inválido";
            else
                return "Email inválido";
        }
    }
    
    private static boolean verifyEmail(String email){
        try{
            String SQL = "SELECT * FROM aluno WHERE nm_email_aluno = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                resultSet.close();
                statement.close();
                return false;
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluno][catch:verifyEmail]: "+exception.getMessage());
        }
        return true;
    }
    
    public String getRa() {
        return ra;
    }
}
