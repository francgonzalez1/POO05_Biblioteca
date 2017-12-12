package com.biblioteca.entities;

public abstract class Pessoa {
    protected int id;
    protected String name;
    protected String email;
    protected String cpf;

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
    
    public String getEmail(){
        return email;
    }
    
    public static boolean validateCpf(String cpf){
        return true;
    }
}
