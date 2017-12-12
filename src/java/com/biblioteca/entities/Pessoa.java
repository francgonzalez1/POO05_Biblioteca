package com.biblioteca.entities;

public abstract class Pessoa {
    protected String name;
    protected String email;
    protected String cpf;

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
    
    public static boolean validateCpf(String cpf){
        return true;
    }
}
