/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.entities;

import com.biblioteca.db.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author MuriloXavier
 */
public class Aluguel {
    private int id;
    private int id_aluno;
    private int id_livro;
    private int id_bibliotecario;
    private Date dt_emprestimo;
    private Date dt_devolucao_prevista;
    private Date dt_devolucao_real;
    private double vl_multa;

    public Aluguel(int id, int id_aluno, int id_livro, int id_bibliotecario, Date dt_emprestimo, Date dt_devolucao_prevista) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
        this.id_bibliotecario = id_bibliotecario;
        this.dt_emprestimo = dt_emprestimo;
        this.dt_devolucao_prevista = dt_devolucao_prevista;
    }
    
    // Construtor com tudo
    public Aluguel(int id, int id_aluno, int id_livro, int id_bibliotecario, Date dt_emprestimo, Date dt_devolucao_prevista, Date dt_devolucao_real, double vl_multa) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
        this.id_bibliotecario = id_bibliotecario;
        this.dt_emprestimo = dt_emprestimo;
        this.dt_devolucao_prevista = dt_devolucao_prevista;
        this.dt_devolucao_real = dt_devolucao_real;
        this.vl_multa = vl_multa;
    }
    
    public static List<Aluguel> getAluguelVigenteList(int id_aluno){
        ArrayList<Aluguel> alugueis = null;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aluguel WHERE cd_aluno = "+id_aluno+""
                    + "AND dt_devolucao_real_livro IS NOT NULL");
            while(resultSet.next()){
                Aluguel aluguel = new Aluguel(resultSet.getInt("cd_aluguel_livro"),
                resultSet.getInt("cd_aluno"),
                resultSet.getInt("cd_livro"),
                resultSet.getInt("cd_bibliotecario"),
                resultSet.getDate("dt_emprestimo_livro"),
                resultSet.getDate("dt_devolucao_prevista_livro"));
                alugueis.add(aluguel);
            }
            statement.close();
            return alugueis;
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:getAluguelList]: "+exception.getMessage());
        }
        return alugueis;
    }
    
    public static List<Aluguel> getAllAlugueisList(int id_aluno){
        ArrayList<Aluguel> alugueis = null;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aluguel WHERE cd_aluno = "+id_aluno+"");
            while(resultSet.next()){
                Aluguel aluguel = new Aluguel(resultSet.getInt("cd_aluguel_livro"),
                resultSet.getInt("cd_aluno"),
                resultSet.getInt("cd_livro"),
                resultSet.getInt("cd_bibliotecario"),
                resultSet.getDate("dt_emprestimo_livro"),
                resultSet.getDate("dt_devolucao_prevista_livro"),
                resultSet.getDate("dt_devolucao_real_livro"),
                resultSet.getDouble("vl_multa_emprestimo"));
                alugueis.add(aluguel);
            }
            statement.close();
            return alugueis;
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:getAllAlugueisList]: "+exception.getMessage());
        }
        return alugueis;
    }
    
    public static Aluguel getAluguel(int id_aluguel){
        Aluguel aluguel = null;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aluguel WHERE cd_aluguel = "+id_aluguel+"");
            if(resultSet.next()){
                aluguel = new Aluguel(resultSet.getInt("cd_aluguel_livro"),
                resultSet.getInt("cd_aluno"),
                resultSet.getInt("cd_livro"),
                resultSet.getInt("cd_bibliotecario"),
                resultSet.getDate("dt_emprestimo_livro"),
                resultSet.getDate("dt_devolucao_prevista_livro"),
                resultSet.getDate("dt_devolucao_real_livro"),
                resultSet.getDouble("vl_multa_emprestimo"));
            }
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:getAluguel]: "+exception.getMessage());
        }
        return aluguel;
    }
    
    public static void addAluguel(int id_aluno, int id_livro, int id_bibliotecario, Date dt_emprestimo, Date dt_devolucao_prevista){
        try{
            String SQL = "INSERT INTO aluguel VALUES (default, ?, ?, ?, ?, ?, null, null)";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setInt(1, id_aluno);
            statement.setInt(2, id_livro);
            statement.setInt(3, id_bibliotecario);
            statement.setDate(4, new java.sql.Date(dt_emprestimo.getTime()));
            statement.setDate(5, new java.sql.Date(dt_devolucao_prevista.getTime()));
            statement.execute();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:addAluguel]: "+exception.getMessage());
        }
    }
    
    public static void finishAluguel(int id){
        try{
            String SQL = "UPDATE aluguel SET dt_devolucao_real_livro = ?, vl_multa_emprestimo = ?"
                    + "WHERE cd_aluguel_livro = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            Date dt = new Date();
            double multa = Aluguel.defineMulta(id);
            java.sql.Date date = new java.sql.Date(dt.getTime());
            statement.setDate(1, date);
            statement.setDouble(2, multa);
            statement.setInt(3, id);
            statement.execute();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:finishAluguel]: "+exception.getMessage());
        }
    }
    
    public static double defineMulta(int id){
        double multa = 0;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT dt_devolucao_prevista_livro FROM aluguel"
                    + "WHERE cd_aluguel_livro = "+id+"");
            if(resultSet.next()){
                Date date = new Date();
                long inicio = Long.parseLong(resultSet.getString("dt_devolucao_prevista_livro"));
                long daysMulta = Math.abs(date.getTime() - inicio);
                return (double) daysMulta * 5;
            }
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:defineMulta]: "+exception.getMessage());
        }
        return multa;
    }
    public static boolean validateDevolucao(Date date){
        Date now = new Date();
        long validateDate = Math.abs(date.getTime() - now.getTime());
        if(validateDate < 0)
            return false;
        else
            return true;
    }
}
