/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.entities;
import com.biblioteca.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Muca
 */
public class Bibliotecario extends Pessoa {
    private int id;
    private String login;
    private String password;

    public Bibliotecario(int id, String name, String cpf, String login, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
    }

    // Retorna um objeto Bibliotecário caso login e senha existam (verificação de acesso)
    public static Bibliotecario getBibliotecario(String login, String password) throws SQLException{
        Bibliotecario bibliotecario = null;
        String SQL = "SELECT * FROM bibliotecario WHERE nm_login_bibliotecario=? AND cd_password_bibliotecario=?";
        PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
        statement.setString(1, login);
        statement.setString(2, password.hashCode()+"");
        System.out.println(password.hashCode()+"");
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            bibliotecario = new Bibliotecario(resultSet.getInt("cd_bibliotecario"), 
                resultSet.getString("nm_bibliotecario"), 
                resultSet.getString("cd_cpf_bibliotecario"),
                resultSet.getString("nm_login_bibliotecario"),
                resultSet.getString("cd_password_bibliotecario"));
            statement.close();
        }
        return bibliotecario;
    }
    
    // Registra um novo bibliotecário, validando login, senha e CPF por funções
    public String setBibliotecario(String name, String login, String password, String cpf) throws SQLException{
        if(verifyLogin(login) && verifyPassword(password) && verifyCPF(cpf)){
            String SQL = "INSERT INTO bibliotecario VALUES (default,?,?,?,?)";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password.hashCode()+"");
            statement.setString(4, cpf);
            statement.execute();
            statement.close();
            return "Cadastro efetuado com sucesso!";
        }else{
            if(!verifyLogin(login))
                return "Login já cadastrado.";
            else if(!verifyPassword(password))
                return "Senha inválida. Mínimo requerido: 1 char maiúsculo, 1 minúsculo, 1 char especial, 1 número.";
            else
                return "CPF inválido.";
        }
    }
    
    // Método booleano que retorna true se login não existe, falso se já existe na db.
    private boolean verifyLogin(String login){
        try{
            String SQL = "SELECT * FROM bibliotecario WHERE nm_login_bibliotecario = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
            if(resultSet.next()) return false;
        }catch(Exception exception){
            System.out.println("[class:Bibliotecario][catch:verifyLogin]: "+exception.getMessage());
        }
        return true;
    }
    
    // Método que verifica se senha possui 1 maiusculo, 1 minusculo, 1 especial e 1 numero.
    private boolean verifyPassword(String password){
        return password.matches("^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*\\/_-]).*$");
    }
    
    // Falta adicionar uma validação de CPF (se necessário)
    private boolean verifyCPF(String cpf){
        return true;
    }
    
    // Altera um nome de um bibliotecario na db.
    public String setName(String id, String name){
        try{
            String SQL = "UPDATE bibliotecario SET nm_bibliotecario = ?"
                        + "WHERE cd_bibliotecario = ?";
                PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
                statement.setString(1, name);
                statement.setInt(2, Integer.parseInt(id));
                statement.execute();
                statement.close();
        }catch(Exception exception){
            System.out.println("[class:Bibliotecario][catch:setName]: "+exception.getMessage());
        }
        return "Nome alterado com sucesso!";
    }
    
    // Valida senha e altera senha de um bibliotecario na db.
    public String setPassword(String id, String password) {
        if(verifyPassword(password)){
            try{
                String SQL = "UPDATE bibliotecario SET cd_password_bibliotecario = ?"
                        + "WHERE cd_bibliotecario = ?";
                PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
                statement.setString(1, password.hashCode()+"");
                statement.setInt(2, Integer.parseInt(id));
                statement.execute();
                statement.close();
                return "Senha alterada com sucesso!";
            }catch(Exception exception){
                System.out.println("[class:Bibliotecario][catch:setPassword]: "+exception.getMessage());
            }
        }
        return "Senha inválida. Mínimo requerido: 1 char maiúsculo, 1 minúsculo, 1 char especial, 1 número.";
    }
    
    // Altera o CPF de um bibliotecario na db.
    public String setCPF(String id, String cpf) {
        if(verifyCPF(cpf)){
            try{
                String SQL = "UPDATE bibliotecario SET cd_cpf_bibliotecario = ?"
                        + "WHERE cd_bibliotecario = ?";
                PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
                statement.setString(1, cpf);
                statement.setInt(2, Integer.parseInt(id));
                statement.execute();
                statement.close();
                return "CPF alterado com sucesso!";
            }catch(Exception exception){
                System.out.println("[class:Bibliotecario][catch:setCPF]: "+exception.getMessage());
            }
        }
        return "CPF inválido.";
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
    
}
