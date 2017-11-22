/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biblioteca.db;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Murilo Xavier
 */
public class Database {
    private static Connection conexao;
    private static String excecao;
    
    public static Connection getConnection(){
        if(conexao == null){
            try{
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                conexao = DriverManager.getConnection("jdbc:derby:C:/Biblioteca/db;create=true");
            }catch(Exception exception){
                conexao = null;
                excecao = exception.getMessage();
                System.out.println("ERRO [class:Database][catch:conexao]: " + exception.getMessage());
            }
        }
        return conexao;
    }
}
