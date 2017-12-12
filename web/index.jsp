<%-- 
    Document   : index
    Created on : 11/12/2017, 21:21:37
    Author     : Muca
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.biblioteca.db.Database"%>
<%@page import="com.biblioteca.entities.Bibliotecario"%>
<%
    String error = null;
    if(request.getParameter("bibliotecarioSubmit") != null){
        try{
            Bibliotecario bibliotecario = Bibliotecario.getBibliotecario(request.getParameter("bibliotecarioLogin"), request.getParameter("bibliotecarioPassword"));
            if(bibliotecario == null){
                error = "Login ou senha incorretos.";
            }
            else{
                session.setAttribute("bibliotecarioId", bibliotecario.getId());
                session.setAttribute("bibliotecarioName", bibliotecario.getName());
                session.setAttribute("bibliotecarioCpf", bibliotecario.getCpf());
                session.setAttribute("bibliotecarioLogin", bibliotecario.getLogin());
                session.setAttribute("bibliotecarioEmail", bibliotecario.getEmail());
                response.sendRedirect(request.getContextPath()+"/home.jsp");
            }
        }catch(Exception ex){
            error = ex.getMessage();
        }
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca</title>
    </head>
    <body>
        <h1>Bibliotecario, faça seu acesso aqui.</h1>
        <fieldset>
            <legend>Acesso: </legend>
            <%if(error != null){%>  
                <p><%=error%></p>
            <%}%>
            <form method="post">
                Login: <input type="text" name="bibliotecarioLogin" required/><br/>
                Senha: <input type="password" name="bibliotecarioPassword" required/><br/>
                <input type="submit" name="bibliotecarioSubmit" value="Acessar"/>
            </form>
            <a href="cadastroBibliotecario.jsp">Cadastre-se</a>
        </fieldset>
    </body>
</html>
