<%-- 
    Document   : manager
    Created on : 12/12/2017, 22:14:49
    Author     : muril
--%>

<%@page import="com.biblioteca.entities.Aluno"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.biblioteca.entities.Aluguel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.biblioteca.entities.Livro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int contador;
    String erro = null;
    String action = request.getParameter("action");
    String msg = "";
    if (action.equals("getLivros") && !(action.equals(""))) {
        List<Object[]> livros = Livro.getLivros();
        if (livros == null && livros.isEmpty()) {
            if (erro == null) {
%>
{"re":"Falha ao trazer os dados dos eventos."}
<%          } else {%>
{"re":"<%=erro%>"}
<%
    }
} else {
    contador = livros.size();
%>
{"livros":[
<%for (Object[] row : livros) {%>
{"id":<%= row[0]%>,
"name":"<%=row[1]%>", 
"type":"<%=row[2]%>",
"description":"<%=row[3]%>",
"isbn":"<%=row[4]%>"
}
<%if (--contador > 0) {%>
,
<%}%>
<%}%>
]}
<%}
    }
    if (action.equals("addBook") && !(action.equals(""))) {

        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String desc = request.getParameter("desc");
        String isbn = request.getParameter("isbn");
        int livro = Livro.setLivro(title, type, desc, isbn);
        if (livro > 0) {
            System.out.println("sucesso");
            msg = "Sucesso na adição";
%>
{"re":"<%=msg%>"}
<%
} else {
    System.out.println("error");
    msg = "Erro na adição";
%>
{"re":"<%=msg%>"}
<%
        }
    }

    if (action.equals("updateBook") && !(action.equals(""))) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String desc = request.getParameter("desc");
        String isbn = request.getParameter("isbn");
        int livro = Livro.updateLivro(id, title, type, desc, isbn);
        if (livro > 0) {
            System.out.println("sucesso");
            msg = "Sucesso na atualizaçao do livro";
%>
{"re":"<%=msg%>"}
<%
} else {
    System.out.println("error");
    msg = "Erro na atualizaçao do livro";
%>
{"re":"<%=msg%>"}
<%
        }
    }
    if (action.equals("getAlugueis") && !(action.equals(""))) {
        List<Object[]> alugueis = Aluguel.getAlugueis();
        if (alugueis == null && alugueis.isEmpty()) {
            if (erro == null) {
%>
{"re":"Falha ao trazer os dados dos eventos."}
<%          } else {%>
{"re":"<%=erro%>"}
<%
    }
} else {
    contador = alugueis.size();
%>
{"alugueis":[
<%for (Object[] row : alugueis) {%>
{"id":<%= row[0]%>,
"id_aluno":"<%=row[1]%>", 
"id_livro":"<%=row[2]%>",
"id_bibliotecario":"<%=row[3]%>",
"date_emprestimo":"<%=row[4]%>",
"date_devolucao_p":"<%=row[5]%>",
"date_devolucao_r":"<%=row[6]%>",
"value_multa":"<%=row[7]%>"
}
<%if (--contador > 0) {%>
,
<%}%>
<%}%>
]}
<%}
    }
    if (action.equals("addRent") && !(action.equals(""))) {
        int aluno = Integer.parseInt(request.getParameter("aluno"));
        int livro = Integer.parseInt(request.getParameter("livro"));
        String dateE = request.getParameter("dateE");
        String dateD = request.getParameter("dateD");
        int biblio = Integer.parseInt(request.getParameter("biblio"));

        int aluguel = Aluguel.addAluguel(aluno, livro, biblio, dateE, dateD);
        if (aluguel > 0) {
            System.out.println("sucesso");
            msg = "Sucesso na adição";
%>//
{"re":"<%=msg%>"}
<%
} else {
    System.out.println("error");
    msg = "Erro na adição";
%>
{"re":"<%=msg%>"}
<%
        }
    }
    if (action.equals("addStudant") && !(action.equals(""))) {
        String name = request.getParameter("name");
        String cpf = request.getParameter("cpf");
        String ra = request.getParameter("ra");
        String email = request.getParameter("email");
        String aluguel = Aluno.setAluno(name, cpf, ra, email);
        if (aluguel == "Cadastro efetuado com sucesso") {
            System.out.println("sucesso");
            msg = "Sucesso na adição";
%>//
{"re":"<%=msg%>"}
<%
} else {
    System.out.println("error");
    msg = "Erro na adição";
%>
{"re":"<%=msg%>"}
<%
        }
    }

    if (action.equals("getStudant") && !(action.equals(""))) {
        List<Object[]> alunos = Aluno.getAllAlunos();
        if (alunos == null && alunos.isEmpty()) {
            if (erro == null) {
%>
{"re":"Falha ao trazer os dados dos eventos."}
<%          } else {%>
{"re":"<%=erro%>"}
<%
    }
} else {
    contador = alunos.size();
%>
{"alunos":[
<%for (Object[] row : alunos) {%>
{"id":<%= row[0]%>,
"nome":"<%=row[1]%>", 
"cpf":"<%=row[2]%>",
"ra":"<%=row[3]%>",
"email":"<%=row[4]%>"
}
<%if (--contador > 0) {%>
,
<%}%>
<%}%>
]}
<%}
    }
%>
