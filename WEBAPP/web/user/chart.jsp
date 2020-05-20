<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/20/20
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Il Mio Carrello</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">a:
</head>
<body>
<!-- Navbar -->
<%@include file="../components/navbar/_navbar.jsp"%>
<!-- Una colonna dove saranno presenti propic, miei ordini e link al carrello -->
<div class="container-fluid">
    <div class="row">
        <div class="col-12 col-md-3 p-5  sidebar-bg">
            <div class="row">
                <div class="col-md-12 col-6 p-2 align-items-center">
                    <div class="propic-container mx-auto">
                        <img class="d-block rounded-circle propic" src="https://via.placeholder.com/300" alt="propic"> <!--photo_2020-05-19_22-48-41.jpg foto locale -->
                        <div class="propic-desciption rounded-circle text-center">
                            <p class="text"><a class="text-light" href="#">Modifica</a></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-12 col-6 mt-3">
                    <nav class="text-center">
                        <ul class="list-unstyled">
                            <li class="nav-item"><a href="#" class="nav-link text-info active">Profilo</a></li>
                            <li class="nav-item"><a href="#" class="nav-link text-info">I Miei Ordini</a></li>
                            <li class="nav-item"><a href="#" class="nav-link text-info">Carrello</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <div class="col-12 col-md-9 p-5 info-user">
            <div class="row">
                <div class="col-12">
                    <h1 class="display-4 text-success"><span class="small text-secondary">Il Carrello</span></h1>
                </div>
                <div class="col-12 mt-3">
                    <div class="row  align-items-top">
                        <div class="col-12 col-lg-3">
                            <!--Immagine pacchetto -->
                            <img class="img-fluid w-100 d-block mx-auto" alt="paccheto1" src="https://via.placeholder.com/200">
                        </div>
                        <div class="col-12 col-lg-3">
                            <!-- Dettaglio pacchetto -->
                            <p>Dettagli pacchetto: </p>
                            <ul class="list-unstyled">
                                <li>Persone: 2</li>
                                <li>Durata: 3 giorni</li>
                                <li>Soggiorno a: Hotel Roma</li>
                                <li>200,50€</li>
                            </ul>
                        </div>
                        <div class="col-lg-4">
                            <p>Extra:</p>
                            <ul class="list-unstyled">
                                <li>Ristorante: la purchiacchia di Mare</li>
                                <li>Visita guidata: Colosseo</li>
                                <li>Visita Guidata: Pantheon</li>
                            </ul>
                        </div>
                        <div class="col-12 col-lg-2">
                            <!-- bottoni: elimina e modifica -->
                            <button class="d-block btn btn-warning mt-auto mb-3 w-100">Modifica</button>
                            <button class="d-block  btn btn-danger w-100 mb-auto">Elimina</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Una colonna più larga dove saranno presenti tutte le info dell'utente -->
<!-- Footer -->
<%@include file="../components/footer/_footer.jsp"%>
</body>
</html>
