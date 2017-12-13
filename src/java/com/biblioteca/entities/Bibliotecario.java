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
    private String login;
    private String password;

    public Bibliotecario(int id, String name, String cpf, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    // Retorna um objeto Bibliotecário caso login e senha existam (verificação de acesso)
    public static Bibliotecario getBibliotecario(String login, String password){
        Bibliotecario bibliotecario = null;
        try{
            String SQL = "SELECT * FROM bibliotecario WHERE nm_login_bibliotecario = ? AND cd_password_bibliotecario = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, login);
            statement.setString(2, password.hashCode()+"");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                bibliotecario = new Bibliotecario(resultSet.getInt("cd_bibliotecario"), 
                    resultSet.getString("nm_bibliotecario"), 
                    resultSet.getString("cd_cpf_bibliotecario"),
                    resultSet.getString("nm_login_bibliotecario"),
                    resultSet.getString("cd_password_bibliotecario"),
                    resultSet.getString("nm_email_bibliotecario"));
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Bibliotecario][catch:getBibliotecario]: "+exception.getMessage());
        }
        return bibliotecario;
    }
    
    // Registra um novo bibliotecário, validando login, senha e CPF por funções
    public static String setBibliotecario(String name, String login, String password, String passwordConfirm, String cpf, String email) throws SQLException{
        if(verifyLogin(login) && verifyPassword(password, passwordConfirm) && validateCpf(cpf) && verifyEmail(email)){
            String SQL = "INSERT INTO bibliotecario VALUES (default,?,?,?,?,?)";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password.hashCode()+"");
            statement.setString(4, cpf);
            statement.setString(5, email);
            statement.execute();
            statement.close();
            return "Cadastro efetuado com sucesso!";
        }else{
            if(!verifyLogin(login))
                return "Login já cadastrado.";
            else if(!verifyPassword(password, passwordConfirm))
                return "A senha digitada ou sua confirmação são inválidas. "
                        + "Por favor, insira uma senha com no mínimo de: "
                        + "1 char maiúsculo, 1 char minusculo, 1 char especial e 1 char numérico";
            else if(!verifyEmail(email))
                return "Email inválido.";
            else
                return "CPF inválido";
        }
    }
    
    public static String updateBibliotecario(int id, String name, String login, String password, String passwordConfirm, String cpf, String email) throws SQLException{
        if(verifyLogin(login) && verifyPassword(password, passwordConfirm) && validateCpf(cpf) && verifyEmail(email)){
            String SQL = "UPDATE bibliotecario SET"
                    + "nm_bibliotecario = ?"
                    + ", nm_login_bibliotecario = ?"
                    + ", cd_password_bibliotecario = ?"
                    + ", cd_cpf_bibliotecario = ?"
                    + ", nm_email_bibliotecario = ?"
                    + "WHERE cd_bibliotecario = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password.hashCode()+"");
            statement.setString(4, cpf);
            statement.setString(5, email);
            statement.setInt(6, id);
            statement.execute();
            statement.close();
            return "Atualização efetuada com sucesso!";
        }else{
            if(!verifyLogin(login))
                return "Login já cadastrado.";
            else if(!verifyPassword(password, password))
                return "Senha inválida. Mínimo requerido: 1 char maiúsculo, 1 minúsculo, 1 char especial, 1 número.";
            else if(!verifyEmail(email))
                return "Email inválido.";
            else
                return "CPF inválido";
        }
    }
    
    // Método booleano que retorna true se login não existe, falso se já existe na db.
    private static boolean verifyLogin(String login){
        try{
            String SQL = "SELECT * FROM bibliotecario WHERE nm_login_bibliotecario = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                resultSet.close();
                statement.close();
                return false;
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Bibliotecario][catch:verifyLogin]: "+exception.getMessage());
        }
        return true;
    }
    
    private static boolean verifyEmail(String email){
        try{
            String SQL = "SELECT * FROM bibliotecario WHERE nm_email_bibliotecario = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                resultSet.close();
                statement.close();
                return false;
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Bibliotecario][catch:verifyEmail]: "+exception.getMessage());
        }
        return true;
    }
    
    // Método que verifica se senha possui 1 maiusculo, 1 minusculo, 1 especial e 1 numero.
    private static boolean verifyPassword(String password, String passwordConfirm){
        if(password.matches("^.*(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*\\/_-]).*$")){
            if(password.equals(passwordConfirm))
                return true;
            else
                return false;
        }
        return false;
    }

        public static String setName(String id, String name){
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
    public static String setPassword(String id, String password, String passwordConfirm) {
        if(verifyPassword(password, passwordConfirm)){
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
    public static boolean setCPF(String id, String cpf) {
        if(validateCpf(cpf)){
            try{
                String SQL = "UPDATE bibliotecario SET cd_cpf_bibliotecario = ?"
                        + "WHERE cd_bibliotecario = ?";
                PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
                statement.setString(1, cpf);
                statement.setInt(2, Integer.parseInt(id));
                statement.execute();
                statement.close();
                return true;
            }catch(Exception exception){
                System.out.println("[class:Bibliotecario][catch:setCPF]: "+exception.getMessage());
            }
        }
        return true;
    }

    public static boolean setEmail(String id, String email) {
        if(verifyEmail(email)){
            try{
                String SQL = "UPDATE bibliotecario SET cd_email_bibliotecario = ?"
                        + "WHERE cd_bibliotecario = ?";
                PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
                statement.setString(1, email);
                statement.setInt(2, Integer.parseInt(id));
                statement.execute();
                statement.close();
                return true;
            }catch(Exception exception){
                System.out.println("[class:Bibliotecario][catch:setCPF]: "+exception.getMessage());
            }
        }
        return false;
    }
    
    public String getLogin() {
        return login;
    }
    
}
