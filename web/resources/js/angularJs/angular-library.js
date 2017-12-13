/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var AngularLibrary = angular.module("angularLibrary", []);

AngularLibrary.run(['$rootScope', '$window', function ($rootScope, $window) {

        $rootScope.CTX_PATH = $window.CTX_PATH;


    }]);

AngularLibrary.factory('postService', ['$http', function ($http) {
        return {
            query: function (selecionado, url) {
                var serializedData = $.param(selecionado);
                return  $http({
                    method: 'POST',
                    url: url,
                    data: serializedData,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                });
            }
        };
    }]);

AngularLibrary.controller('controllerHome', function ($scope, $rootScope, postService, $http, $timeout) {

    $scope.login = function (email, pass) {
        $scope.loadingPost = true;
        var dados = {email_user: email, pass_user: pass};
        var url = "data/login.jsp?action=login";
        postService.query(dados, url).then(function (re) {
            $scope.loadingPost = false;
            if (re.data.aux === "1") {
//                $("#deleteSuccess p").empty().append(re.data.re);;
//                $("#deleteSuccess").modal("show");
//                $timeout(function () {
//                    $("#deleteSuccess").modal("hide");
//                    window.scrollTo(0, 0);
//                    location.reload();
//                }, 2000);
                location.reload();
                alert("foi o login");
            } else {
//                $scope.loadingPost = false;
//                $("#deleteSuccess p").empty().append(re.data.re);
//                $("#deleteSuccess").modal("show");
                alert(" n foi o login");
            }
        }).catch(function () {
//            $scope.loadingPost = false;
//            $("#ErrorModal").show();
//            $("#eWarningError").empty();
//            $("#eWarningError").append("Falha ao fazer envio");
//            setTimeout(function () {
//                $("#ErrorModal").hide();
//            }, 2000);
            alert(" n foi o login e deu muito ruim kkk");
        });
    };
    $scope.out = function () {
        var action = {action: "logout"};
        var url = "data/login.jsp";
        postService.query(action, url).then(function (response) {
            if (response.data.aux == "1") {
//                $("#deleteSuccess p").empty().append(re.data.re);;
//                $("#deleteSuccess").modal("show");
//                $timeout(function () {
//                    $("#deleteSuccess").modal("hide");
//                    window.scrollTo(0, 0);
//                    location.reload();
//                }, 2000);
                location.reload();
                alert("foi o logout");
            } else {
//                $scope.loadingPost = false;
//                $("#deleteSuccess p").empty().append(re.data.re);
//                $("#deleteSuccess").modal("show");
                alert(" n foi o logout");
            }
        }).catch(function () {
//            $scope.loadingPost = false;
//            $("#ErrorModal").show();
//            $("#eWarningError").empty();
//            $("#eWarningError").append("Falha ao fazer envio");
//            setTimeout(function () {
//                $("#ErrorModal").hide();
//            }, 2000);
            alert(" n foi o logout e deu muito ruim kkk");
        });
    };


});

AngularLibrary.controller('controllerLivros', function ($scope, $rootScope, postService, $http, $timeout) {

    $scope.getLivros = function () {
        $http.get("data/manager.jsp?action=getLivros")
                .then((response) => {
                    $scope.livros = [];
                    $scope.livros = response.data.livros;
                    if ($scope.livros == "") {
                        $scope.selectError = response.data.re;
                        console.log($scope.selectError);
                    } else {
                    }
                });
    };
    $scope.getLivros();

    $scope.addBook = function (book) {
        var dados = {title: book.title, type: book.type, desc: book.desc, isbn: book.isbn};
        var url = "data/manager.jsp?action=addBook";
        postService.query(dados, url).then(function (re) {
            alert("livro add");
            console.log(re.data.re);
            $scope.getLivros();
            $("#includeBooks").modal('hide');
        }).catch(function (re) {
            alert("livro naum add");
            console.log(re.data.re);
        });

    };

    $scope.updateBook = function (book) {
        $scope.BackUp = angular.copy(book);
        $scope.Ubook = $scope.BackUp;
        $("#updateBooks").modal('hide');
    };

    $scope.upBook = function (book) {
        var dados = {id: book.id, title: book.name, type: book.type, desc: book.description, isbn: book.isbn};
        var url = "data/manager.jsp?action=updateBook";
        postService.query(dados, url).then(function (re) {
            alert("livro upp");
            console.log(re.data.re);
            $scope.getLivros();
            $("#updateBooks").modal('hide');
        }).catch(function (re) {
            alert("livro naum upp");
            console.log(re.data.re);
        });
    };

});

AngularLibrary.controller('controllerAlugueis', function ($scope, $rootScope, postService, $http, $timeout) {

    $scope.getAlugueis = function () {
        $http.get("data/manager.jsp?action=getAlugueis")
                .then((response) => {
                    $scope.alugueis = [];
                    $scope.alugueis = response.data.alugueis;
                    if ($scope.alugueis == "") {
                        $scope.selectError = response.data.re;
                        console.log($scope.selectError);
                    } else {
                    }
                });
    };
    $scope.getAlugueis();

 $scope.addRent = function (rent) {
        var dados = {aluno: rent.aluno, livro: rent.livro, dateE: rent.dateE, dateD: rent.dateD, biblio: rent.biblio};
        var url = "data/manager.jsp?action=addRent";
        postService.query(dados, url).then(function (re) {
            alert("livro add");
            console.log(re.data.re);
            $scope.getAlugueis();
            $("#includeRent").modal('hide');
        }).catch(function (re) {
            alert("rent naum add");
            console.log(re.data.re);
        });

    };
});

AngularLibrary.controller('controllerStudant', function ($scope, $rootScope, postService, $http, $timeout) {

    $scope.getStudant = function () {
        $http.get("data/manager.jsp?action=getStudant")
                .then((response) => {
                    $scope.studants = [];
                    $scope.studants = response.data.alunos;
                    if ($scope.studants == "") {
                        $scope.selectError = response.data.re;
                        console.log($scope.selectError);
                    } else {
                    }
                });
    };
    $scope.getStudant();

 $scope.addStudant = function (studant) {
        var dados = {name: studant.name, cpf: studant.cpf, ra: studant.ra, email: studant.email};
        var url = "data/manager.jsp?action=addStudant";
        postService.query(dados, url).then(function (re) {
            alert("livro add");
            console.log(re.data.re);
            $scope.getLivros();
            $("#includeStudant").modal('hide');
        }).catch(function (re) {
            alert("studant naum add");
            console.log(re.data.re);
        });

    };
});

