<%-- 
    Document   : manager-area
    Created on : 12/12/2017, 20:41:20
    Author     : muril
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="angularLibrary">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Minha Área</title>
        <link rel='icon' href=''>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="resources/css/custom.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/nav-bar.jspf" %>
        <section style="">
            <div>
                <h2 class="textCenter quiz-title">Área do Gestor</h2>

            </div>
        </section>
        <section class='container-user container-fluid content' >
            <div style=" min-height: 600px; max-height: 600px; background: white; border-radius: 10px; padding-top: 20px; " class="col-md-12">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#home">Livros</a></li>
                    <li><a data-toggle="tab" href="#menu1">Aluguéis</a></li>
                    <li><a data-toggle="tab" href="#menu2">Dívidas</a></li>
                    <li><a data-toggle="tab" href="#menu3">Alunos</a></li>
                </ul>

                <div class="tab-content">
                    <div id="home" class="tab-pane fade in active" ng-controller="controllerLivros">
                        <div class="panel-body" id="painel-bd">
                            <div class="form-inline form-table"> 
                                <div class="row">     
                                   <!-- <div class="search-input form-group">
                                        <label>Pesquisar:</label>
                                        <input max-lenght="100" type="text"   placeholder="Procurar..." class="form-control " >
                                        <a href="" class="search-btn btn btn-default" title="Pesquisar"></a>
                                    </div>    
                                    <div class="filter-input form-group">
                                        <label>Pesquisar por:</label>
                                        <select id="filter-unit" class="form-control">
                                            <option value="" >Selecione...</option>                                            
                                            <option value="r.name">Nome da região</option>                                         
                                            <option value="c.name">Nome do evento</option>
                                            <option value="ac.name">Agente de Inovação</option>
                                        </select>
                                    </div>                               
                                    <div class="filter-qtd form-group">
                                        <label>Mostrar:</label>
                                        <select  id="table-qtdRegion" class="form-control">
                                            <option value="" disabled>Selecione...</option> 
                                            <option>50</option>    
                                            <option>100</option>
                                            <option>200</option>                                   
                                        </select>
                                    </div>-->
                                    <a href="#includeBooks"  data-toggle="modal" class="btn-add">Adicionar</a>                                    
                                </div>
                            </div><br>
                            <div id="sectionLoadRegions">
                                <table  class="table table-hover table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID<span></span></th>
                                            <th>Nome<span></span></th>                      
                                            <th>Tipo<span ></span></th>                      
                                            <th>Descrição</th>
                                            <th>ISBN</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="livro in livros">
                                            <td>{{livro.id}}</td>
                                            <td>{{livro.name}}</td>
                                            <td>{{livro.type}}</td>
                                            <td>{{livro.description}}</td>
                                            <td>{{livro.isbn}}</td>
                                            <td><a href="#updateBooks" data-toggle="modal" class="btn-add" ng-click="updateBook(livro)">atualizar</a></td>
                                        </tr>                                     
                                    </tbody>
                                </table>
                                <div id="selectErro" ui-alert></div> 
                                <p class="size"><b>Total : </b></p>
                                <div class='table-pagination text-center'>
                                    <dir-pagination-controls on-page-change="pageChanged(newPageNumber)" max-size="7" boundary-links="true" pagination-id="region"></dir-pagination-controls>
                                </div>
                            </div>
                            <div class="text-center" >                                                
                                
                            </div> 
                            <%@include file="WEB-INF/jspf/includeBooks.jspf" %>
                            <%@include file="WEB-INF/jspf/updateBooks.jspf" %>
                        </div>
                    </div>
                    <div id="menu1" class="tab-pane fade" ng-controller="controllerAlugueis">
                        <div class="panel-body" id="painel-bd">
                            <div class="form-inline form-table"> 
                                <div class="row">     
                                    <!--<div class="search-input form-group">
                                        <label>Pesquisar:</label>
                                        <input max-lenght="100" type="text"   placeholder="Procurar..." class="form-control " >
                                        <a href="" class="search-btn btn btn-default" title="Pesquisar"></a>
                                    </div>    
                                    <div class="filter-input form-group">
                                        <label>Pesquisar por:</label>
                                        <select id="filter-unit" class="form-control">
                                            <option value="" >Selecione...</option>                                            
                                            <option value="r.name">Nome da região</option>                                         
                                            <option value="c.name">Nome do evento</option>
                                            <option value="ac.name">Agente de Inovação</option>
                                        </select>
                                    </div>                               
                                    <div class="filter-qtd form-group">
                                        <label>Mostrar:</label>
                                        <select  id="table-qtdRegion" class="form-control">
                                            <option value="" disabled>Selecione...</option> 
                                            <option>50</option>    
                                            <option>100</option>
                                            <option>200</option>                                   
                                        </select>
                                    </div>-->
                                    <a href="#includeRent" data-toggle="modal" >Adicionar</a>                                    
                                </div>
                            </div><br>
                            <div id="sectionLoadRegions">
                                <table  class="table table-hover table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID<span></span></th>
                                            <th>aluno<span></span></th>                      
                                            <th>livro<span ></span></th>                      
                                            <th>bibliotecário</th>
                                            <th>data emprestimo</th>
                                            <th>data devolução</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="aluguel in alugueis">
                                            <td>{{aluguel.id}}</td>
                                            <td>{{aluguel.id_aluno}}</td>
                                            <td>{{aluguel.id_livro}}</td>
                                            <td>{{aluguel.id_bibliotecario}}</td>
                                            <td>{{aluguel.date_emprestimo}}</td>
                                            <td>{{aluguel.date_devolucao_p}}</td>
                                            <td><a href="#updateBooks" data-toggle="modal" class="btn-add" ng-click="updateBook(livro)">atualizar</a></td>
                                        </tr>                                     
                                    </tbody>
                                </table>
                                <div id="selectErro" ui-alert></div> 
                                <p class="size"><b>Total : </b></p>
                                <div class='table-pagination text-center'>
                                    <dir-pagination-controls on-page-change="pageChanged(newPageNumber)" max-size="7" boundary-links="true" pagination-id="region"></dir-pagination-controls>
                                </div>
                            </div>
                            <div class="text-center" >                                                
                                
                            </div> 
                            <%@include file="WEB-INF/jspf/includeRent.jspf" %>
                            <%@include file="WEB-INF/jspf/updateBooks.jspf" %>
                        </div>
                    </div>
                    <div id="menu2" class="tab-pane fade">
                        <div class="panel-body" id="painel-bd">
                            <div class="form-inline form-table"> 
                                <div class="row">     
                                  <!--  <div class="search-input form-group">
                                        <label>Pesquisar:</label>
                                        <input max-lenght="100" type="text"   placeholder="Procurar..." class="form-control " >
                                        <a href="" class="search-btn btn btn-default" title="Pesquisar"></a>
                                    </div>    
                                    <div class="filter-input form-group">
                                        <label>Pesquisar por:</label>
                                        <select id="filter-unit" class="form-control">
                                            <option value="" >Selecione...</option>                                            
                                            <option value="r.name">Nome da região</option>                                         
                                            <option value="c.name">Nome do evento</option>
                                            <option value="ac.name">Agente de Inovação</option>
                                        </select>
                                    </div>                               
                                    <div class="filter-qtd form-group">
                                        <label>Mostrar:</label>
                                        <select  id="table-qtdRegion" class="form-control">
                                            <option value="" disabled>Selecione...</option> 
                                            <option>50</option>    
                                            <option>100</option>
                                            <option>200</option>                                   
                                        </select>
                                    </div>-->
                                    <a href="#includeBooks"  data-toggle="modal" class="btn-add">Adicionar</a>                                    
                                </div>
                            </div><br>
                            <div id="sectionLoadRegions">
                                <table  class="table table-hover table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID<span></span></th>
                                            <th>Nome<span></span></th>                      
                                            <th>Tipo<span ></span></th>                      
                                            <th>Descipção</th>
                                            <th>ISBN</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="livro in livros">
                                            <td>{{livro.id}}</td>
                                            <td>{{livro.name}}</td>
                                            <td>{{livro.type}}</td>
                                            <td>{{livro.description}}</td>
                                            <td>{{livro.isbn}}</td>
                                            <td><a href="#updateBooks" data-toggle="modal" class="btn-add" ng-click="updateBook(livro)">atualizar</a></td>
                                        </tr>                                     
                                    </tbody>
                                </table>
                                <div id="selectErro" ui-alert></div> 
                                <p class="size"><b>Total : </b></p>
                                <div class='table-pagination text-center'>
                                    <dir-pagination-controls on-page-change="pageChanged(newPageNumber)" max-size="7" boundary-links="true" pagination-id="region"></dir-pagination-controls>
                                </div>
                            </div>
                            <div class="text-center" >                                                
                                
                            </div> 
                            <%@include file="WEB-INF/jspf/includeBooks.jspf" %>
                            <%@include file="WEB-INF/jspf/updateBooks.jspf" %>
                        </div>
                    </div>
                        <div id="menu3" class="tab-pane fade" ng-controller="controllerStudant">
                        <div class="panel-body" id="painel-bd">
                            <div class="form-inline form-table"> 
                                <div class="row">     
                                   <!-- <div class="search-input form-group">
                                        <label>Pesquisar:</label>
                                        <input max-lenght="100" type="text"   placeholder="Procurar..." class="form-control " >
                                        <a href="" class="search-btn btn btn-default" title="Pesquisar"></a>
                                    </div>    
                                    <div class="filter-input form-group">
                                        <label>Pesquisar por:</label>
                                        <select id="filter-unit" class="form-control">
                                            <option value="" >Selecione...</option>                                            
                                            <option value="r.name">Nome da região</option>                                         
                                            <option value="c.name">Nome do evento</option>
                                            <option value="ac.name">Agente de Inovação</option>
                                        </select>
                                    </div>                               
                                    <div class="filter-qtd form-group">
                                        <label>Mostrar:</label>
                                        <select  id="table-qtdRegion" class="form-control">
                                            <option value="" disabled>Selecione...</option> 
                                            <option>50</option>    
                                            <option>100</option>
                                            <option>200</option>                                   
                                        </select>
                                    </div>-->
                                    <a href="#includeStudant"  data-toggle="modal" class="btn-add">Adicionar</a>                                    
                                </div>
                            </div><br>
                            <div id="sectionLoadRegions">
                                <table  class="table table-hover table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID<span></span></th>
                                            <th>Nome<span></span></th>                      
                                            <th>CPF<span ></span></th>                      
                                            <th>RA</th>
                                            <th>Email</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="studant in studants">
                                            <td>{{studant.id}}</td>
                                            <td>{{studant.name}}</td>
                                            <td>{{studant.cpf}}</td>
                                            <td>{{studant.ra}}</td>
                                            <td>{{studant.email}}</td>
                                            <td><a href="#updateBooks" data-toggle="modal" class="btn-add" ng-click="updateBook(livro)">atualizar</a></td>
                                        </tr>                                     
                                    </tbody>
                                </table>
                                <div id="selectErro" ui-alert></div> 
                                <p class="size"><b>Total : </b></p>
                                <div class='table-pagination text-center'>
                                    <dir-pagination-controls on-page-change="pageChanged(newPageNumber)" max-size="7" boundary-links="true" pagination-id="region"></dir-pagination-controls>
                                </div>
                            </div>
                            <div class="text-center" >                                                
                                
                            </div> 
                            <%@include file="WEB-INF/jspf/includeStudant.jspf" %>
                            <%@include file="WEB-INF/jspf/updateBooks.jspf" %>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="resources/js/custom.js" type="text/javascript"></script>
        <%@include file="WEB-INF/jspf/login.jspf" %>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
