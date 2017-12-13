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
    private String dt_emprestimo;
    private String dt_devolucao_prevista;
    private String dt_devolucao_real;
    private double vl_multa;

    public Aluguel(int id, int id_aluno, int id_livro, int id_bibliotecario, String dt_emprestimo, String dt_devolucao_prevista) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_livro = id_livro;
        this.id_bibliotecario = id_bibliotecario;
        this.dt_emprestimo = dt_emprestimo;
        this.dt_devolucao_prevista = dt_devolucao_prevista;
    }
    
    // Construtor com tudo
    public Aluguel(int id, int id_aluno, int id_livro, int id_bibliotecario, String dt_emprestimo, String dt_devolucao_prevista, String dt_devolucao_real, double vl_multa) {
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
                    + "AND dt_devolucao_real_livro IS NULL");
            while(resultSet.next()){
                Aluguel aluguel = new Aluguel(resultSet.getInt("cd_aluguel_livro"),
                resultSet.getInt("cd_aluno"),
                resultSet.getInt("cd_livro"),
                resultSet.getInt("cd_bibliotecario"),
                resultSet.getString("dt_emprestimo_livro"),
                resultSet.getString("dt_devolucao_prevista_livro"));
                alugueis.add(aluguel);
            }
            resultSet.close();
            statement.close();
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
                resultSet.getString("dt_emprestimo_livro"),
                resultSet.getString("dt_devolucao_prevista_livro"),
                resultSet.getString("dt_devolucao_real_livro"),
                resultSet.getDouble("vl_multa_emprestimo"));
                alugueis.add(aluguel);
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:getAllAlugueisList]: "+exception.getMessage());
        }
        return alugueis;
    }
    
    public static List<Object[]> getAlugueis(){
        ArrayList<Object[]> alugueis = new ArrayList<>();
        Object[] aluguelR;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aluguel");
            while(resultSet.next()){
                aluguelR = new Object[8];
                for(int i = 0; i < 8; i++){
                    aluguelR[i] = resultSet.getString(i + 1);
                }
                alugueis.add(aluguelR);
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getLivros]: "+exception.getMessage());
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
                resultSet.getString("dt_emprestimo_livro"),
                resultSet.getString("dt_devolucao_prevista_livro"),
                resultSet.getString("dt_devolucao_real_livro"),
                resultSet.getDouble("vl_multa_emprestimo"));
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:getAluguel]: "+exception.getMessage());
        }
        return aluguel;
    }
    
    public static int addAluguel(int id_aluno, int id_livro, int id_bibliotecario, String dt_emprestimo, String dt_devolucao_prevista){
        int aux;
        try{
            String SQL = "INSERT INTO aluguel VALUES (default, ?, ?, ?, ?, ?, null, null)";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setInt(1, id_aluno);
            statement.setInt(2, id_livro);
            statement.setInt(3, id_bibliotecario);
            statement.setString(4, dt_emprestimo);
            statement.setString(5, dt_devolucao_prevista);
            statement.execute();
            statement.close();
            aux = 1;
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:addAluguel]: "+exception.getMessage());
            aux = 0;
        }
        return aux;
    }
    
    public static void finishAluguel(int id){
        try{
            String SQL = "UPDATE aluguel SET dt_devolucao_real_livro = ?, vl_multa_emprestimo = ?"
                    + "WHERE cd_aluguel_livro = ?";
            Date date = new Date();
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            double multa = Aluguel.defineMulta(id);
            statement.setString(1, String.valueOf(date));
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
                java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("E dd MMM yyyy HH:mm:ss");
                Date data = formato.parse(resultSet.getString("dt_devolucao_prevista_livro"));
                Date now = new Date();
                long daysMulta = Math.abs(now.getTime() - data.getTime());
                multa = (double) daysMulta * 5;
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Aluguel][catch:defineMulta]: "+exception.getMessage());
        }
        return multa;
    }
}
