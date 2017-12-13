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

    public Livro(int id, String titulo, String tipo, String descricao, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.isbn = isbn;
    }
    // Método responsável pela adição de livros a db.
    public static int setLivro(String titulo, String tipo, String descricao, String isbn){
        int aux;
        try{
           String SQL = "INSERT INTO APP.LIVRO (NM_LIVRO, NM_TIPO_LIVRO, DS_LIVRO,CD_ISBN_LIVRO) VALUES (?, ?, ?, ?)";
           PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
           statement.setString(1, titulo);
           statement.setString(2, tipo);
           statement.setString(3, descricao);
           statement.setString(4, isbn);
           statement.execute();
           statement.close();
           aux = 1;
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:setLivro]: "+exception.getMessage());
            aux = 0;
        }
        return aux;
    }
    
    // Método que retorna um livro pelo id (retorna objeto Livro ou null).
    public Livro getLivro(int id){
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
                                  resultSet.getString("cd_isbn_livro"));
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getLivro]: "+exception.getMessage());
        }
        return livro;
    }
    
    // Este método efetua uma busca pelos livros com base em um parametro.
    // Retorna um ArrayList de livros encontrados com(retorna Arraylist<Livros> ou null).
    public List<Livro> getSearchedLivro(String context){
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
                                  resultSet.getString("cd_isbn_livro"));
                livros.add(livro);
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getSearchedLivro]: "+exception.getMessage());
        }
        return livros;
    }
    
    // Método responsável por atualizar todos os itens de um livro por seu id.
    // HINT: Utilize um formulário que já tenha todos os valores setados nele (value) e
    // permita ao usuário alterar somente o que quer.
    public static int updateLivro(int id, String titulo, String tipo, String descricao, String isbn){
        int aux;
        try{
            String SQL = "UPDATE livro SET "
                    + "nm_livro = ?"
                    + ", nm_tipo_livro = ?"
                    + ", ds_livro = ?"
                    + ", cd_isbn_livro = ?"
                    + "WHERE cd_livro = ?";
            PreparedStatement statement = Database.getConnection().prepareStatement(SQL);
            statement.setString(1, titulo);
            statement.setString(2, tipo);
            statement.setString(3, descricao);
            statement.setString(4, isbn);
            statement.setInt(5, id);
            statement.execute();
            statement.close();
            aux = 1;
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:updateLivro]: "+exception.getMessage());
            aux = 0;
        }
        return aux;
    }
    
    // Método que retorna um ArrayList<Livro> de livros.
    public static List<Object[]> getLivros(){
        ArrayList<Object[]> livros = new ArrayList<>();
        Object[] livroR;
        try{
            Statement statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM livro");
            while(resultSet.next()){
                livroR = new Object[5];
                for(int i = 0; i < 5; i++){
                    livroR[i] = resultSet.getString(i + 1);
                }
                livros.add(livroR);
            }
            resultSet.close();
            statement.close();
        }catch(Exception exception){
            System.out.println("[class:Livro][catch:getLivros]: "+exception.getMessage());
        }
        return livros;
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
}
