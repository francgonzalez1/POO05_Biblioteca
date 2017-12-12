<%-- 
    Document   : home
    Created on : 11/12/2017, 21:44:09
    Author     : Muca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Bem vindo <%=session.getAttribute("bibliotecarioName")%>!</h1>
        <h2>Meus dados</h2>
        <span>Nome: <%=session.getAttribute("bibliotecarioName")%></span><a href="nome.jsp">Alterar Nome</a><br/>
        <span>Email: <%=session.getAttribute("bibliotecarioEmail")%></span><a href="email.jsp">Alterar Email</a><br/>
        <span>Login: <%=session.getAttribute("bibliotecarioLogin")%></span><br/>
        <span>CPF: <%=session.getAttribute("bibliotecarioCpf")%></span><a href="cpf.jsp">Alterar CPF</a><br/>
        <span>Senha: </span><a href="senha.jsp">Alterar Senha</a><br/>
    </body>
</html>
