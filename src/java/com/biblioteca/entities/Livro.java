package com.biblioteca.entities;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.biblioteca.db.Database;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int id;
    private String titulo;
    private String tipo;
    private String descricao;
    private String isbn;
    private boolean isDisabled;

    public Livro(int id, String titulo, String tipo, String descricao, String isbn, boolean isDisabled) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.isbn = isbn;
        this.isDisabled = isDisabled;
    }
    // Método responsável pela adição de livros a db.
    public static void setLivro(String titulo, String tipo, String descricao, String isbn){
        try{
           String SQL = "INSERT INTO livro VALUES (default, ?, ?, ?, ?, ?)";
           PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
           statement.setString(1, titulo);
           statement.setString(2, tipo);
           statement.setString(3, descricao);
           statement.setString(4, isbn);
           statement.setBoolean(5, false);
           statement.execute();
           statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:setLivro]: "+exception.getMessage());
        }
    }
    
    // Método que retorna um livro pelo id (retorna objeto Livro ou null).
    public static Livro getLivro(int id){
        Livro livro = null;
        try{
            String SQL = "SELECT * FROM livro WHERE cd_livro = "+id+"";
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            if(resultSet.next()){
                livro = new Livro(resultSet.getInt("cd_livro"), 
                                  resultSet.getString("nm_livro"),
                                  resultSet.getString("nm_tipo_livro"), 
                                  resultSet.getString("ds_livro"), 
                                  resultSet.getString("cd_isbn_livro"),
                                  resultSet.getBoolean("ic_disabled"));
            }
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getLivro]: "+exception.getMessage());
        }
        return livro;
    }
    
    // Este método efetua uma busca pelos livros com base em um parametro.
    // Retorna um ArrayList de livros encontrados com(retorna Arraylist<Livros> ou null).
    public static List<Livro> getSearchedLivro(String context){
        ArrayList<Livro> livros = null;
        try{
            String SQL = "SELECT * FROM livro WHERE nm_livro LIKE '%?%'";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, context);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Livro livro = new Livro(resultSet.getInt("cd_livro"), 
                                  resultSet.getString("nm_livro"),
                                  resultSet.getString("nm_tipo_livro"), 
                                  resultSet.getString("ds_livro"), 
                                  resultSet.getString("cd_isbn_livro"),
                                  resultSet.getBoolean("ic_disabled"));
                livros.add(livro);
            }
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getSearchedLivro]: "+exception.getMessage());
        }
        return livros;
    }
    
    // Método responsável por atualizar todos os itens de um livro por seu id.
    // HINT: Utilize um formulário que já tenha todos os valores setados nele (value) e
    // permita ao usuário alterar somente o que quer.
    public static void updateLivro(int id, String titulo, String tipo, String descricao, String isbn, boolean isDisabled){
        try{
            String SQL = "UPDATE livro SET "
                    + "nm_livro = ?"
                    + ", nm_tipo_livro = ?"
                    + ", ds_livro = ?"
                    + ", cd_isbn_livro = ?"
                    + ", ic_disabled = ?"
                    + "WHERE cd_livro = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, titulo);
            statement.setString(2, tipo);
            statement.setString(3, descricao);
            statement.setString(4, isbn);
            statement.setBoolean(5, isDisabled);
            statement.setInt(6, id);
            statement.execute();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:updateLivro]: "+exception.getMessage());
        }
    }
    
    // Método que retorna um ArrayList<Livro> de livros.
    public static List<Livro> getLivros(){
        ArrayList<Livro> livros = new ArrayList<>();
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM livro WHERE ic_disabled = "+false+"");
            while(resultSet.next()){
                Livro livro = new Livro(resultSet.getInt("cd_livro"), 
                                  resultSet.getString("nm_livro"),
                                  resultSet.getString("nm_tipo_livro"), 
                                  resultSet.getString("ds_livro"), 
                                  resultSet.getString("cd_isbn_livro"),
                                  resultSet.getBoolean("ic_disabled"));
                livros.add(livro);
            }
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getLivros]: "+exception.getMessage());
        }
        return livros;
    }
    
    public static void disableLivro(int id, boolean isDisabled){
        try{
            String SQL = "UPDATE livro SET ic_disabled = ? WHERE cd_livro = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setBoolean(1, isDisabled);
            statement.setInt(2, id);
            statement.execute();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:toggleEnabledLivro]: "+exception.getMessage());
        }
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getIsbn() {
        return isbn;
    }
    public boolean getDisabled(){
        return isDisabled;
    }
}
