/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.entities;
import com.biblioteca.db.Database;
/**
 *
 * @author a
 */
public class Bibliotecario extends Pessoa {
    private String login;
    private String password;

    public Bibliotecario(String name, String cpf, String login, String password) {
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
