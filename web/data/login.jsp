<%-- 
    Document   : login
    Created on : 11/12/2017, 23:02:33
    Author     : muril
--%>
<%@page import="com.biblioteca.db.Database"%>
<%@page import="com.biblioteca.entities.Bibliotecario"%>
<%
    String error = null;
    String action = request.getParameter("action");
    if (action != null && action.equals("login")) {
        try {
            String email_user = request.getParameter("email_user");
            String pass_user = request.getParameter("pass_user");
            Bibliotecario bibliotecario = Bibliotecario.getBibliotecario(email_user, pass_user);
            if (bibliotecario != null) {
                session.setAttribute("bibliotecarioId", bibliotecario.getId());
                session.setAttribute("bibliotecarioName", bibliotecario.getName());
                session.setAttribute("bibliotecarioCpf", bibliotecario.getCpf());
                session.setAttribute("bibliotecarioLogin", bibliotecario.getLogin());
%>
{"re":"Login realizada com sucesso.", "aux":"1"}
<%
        } else {
            error = "Login ou senha incorretos.";
        }
    } catch (Exception ex) {
        error = ex.getMessage();
    }
    if (error != null) {%>
{"re":"<%=error%>", "aux":"2"}
<%}%>
<%
} else if (action != null && action.equals("logout")) {
    try {
        session.invalidate();
%>
{"re":"Logout realizada com sucesso.", "aux":"1"}
<%
    } catch (Exception ex) {
        error = ex.getMessage();
    }
    if (error != null) {%>
{"re":"<%=error%>", "aux":"2"}
<%}%>
<%}%>
<%
    
    if (action != null && action.equals("login")) {
    }
%>
