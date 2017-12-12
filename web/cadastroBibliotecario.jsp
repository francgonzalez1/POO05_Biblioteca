<%-- 
    Document   : cadastroBibliotecario
    Created on : 12/12/2017, 09:27:30
    Author     : MuriloXavier
--%>
<%@page import="com.biblioteca.entities.Bibliotecario"%>
<%
    String message = null;
    if(request.getParameter("newBibliotecario") != null){
        try{
            message = Bibliotecario.setBibliotecario(
                    request.getParameter("nome"),
                    request.getParameter("login"),
                    request.getParameter("password"),
                    request.getParameter("passwordConfirm"),
                    request.getParameter("cpf"),
                    request.getParameter("email"));
        }catch(Exception ex){
            message = ex.getMessage();
        }
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
    </head>
    <body>
        <fieldset>
            <legend>Cadastro bibliotecario</legend>
            <%if(message != null){%>
                <%=message%>
            <%}%>
            <form method="post">
                Digite seu nome: <input type="text" name="nome" required/><br/>
                Digite um login: <input type="text" name="login" required/><br/>
                Digite uma senha: <input type="password" name="password" required/><br/>
                Confirme a senha: <input type="password" name="passwordConfirm" required/><br/>
                Digite seu CPF: <input type="text" name="cpf" required/><br/>
                Digite seu email: <input type="email" name="email" required/><br/>
                <input type="submit" name="newBibliotecario" value="Cadastrar"/>
            </form>
        </fieldset>
    </body>
</html>
