<%-- 
    Document   : home
    Created on : 11/10/2017, 23:28:40
    Author     : murilo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Quiz Wild</title>
        <link rel='icon' href=''>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="resources/css/custom.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/nav-bar.jspf" %>
        <section id="ranking" class="container-fluid content" >
            <img src="" alt=""/>
            <div class="row">
                <div class="col-md-12">
                    <div class="table-ranking ">
                        <h2 class="text-center whiteC">FEITO POR NÓS PARA SUA BIBLIOTECA</h2><hr class='bottom-line3'><br><br>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class='container'>
                <div class='row container-equip'>
                    <h2 class='text-center '>Quem somos</h2> <hr class='bottom-line3'><br><br>
                    <div class='col-md-3 member-team'>
                    </div>
                    <div class='col-md-3 member-team' >
                    </div>
                    <div class='col-md-3 member-team'>

                    </div>
                </div>
            </div>
        </section>
        <section id="team" class="container-fluid " >
            <div class='container'>
                <div class='row container-equip'>
                    <h2 class='text-center '>Equipe</h2> <hr class='bottom-line3'><br><br>
                    <div class='col-md-3 member-team'>
                        <img  src='resources/img/henrique.jpg' alt=''/>
                        <h2>Henrique</h2>
                        <p>Responsável desenvolvimento das funcionalidades do quiz.</p>
                        <p>riquemelo98@gmail.com</p>

                    </div>
                    <div class='col-md-3 member-team' >
                        <img  src='resources/img/muriloXavier.jpg' alt=''/>
                        <h2>Murilo Xavier</h2>
                        <p>Responsável desenvolvimento do sistema de login.</p>
                        <p>muquinhaxl@gmail.com</p>
                    </div>
                    <div class='col-md-3 member-team'>
                        <img  src='resources/img/murilo.jpg' alt=''/>
                        <h2>Murilo Medeiros</h2>
                        <p>Responsável desenvolvimento da aparência da interface.</p>
                        <p>murilo_medeiros98@hotmail.com</p>

                    </div>
                </div>
            </div>
        </section>  

        <%@include file="WEB-INF/jspf/footer.jspf" %>
        <!-- Jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script>
        </script>
    </body>
</html>
