<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/19/20
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Il tuo profilo </title>
    <link rel="stylesheet" href="../css/main.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <!-- Navbar -->
    <%@include file="../components/navbar/_navbar.jsp"%>
    <!-- Una colonna dove saranno presenti propic, miei ordini e link al carrello -->
    <div class="container-fluid">
	<div class="row">
		<div class="col-12 col-md-4 p-5  sidebar-bg">
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
                            <li class="nav-item"><a href="ordini.jsp" class="nav-link text-info">I Miei Ordini</a></li>
                            <li class="nav-item"><a href="#" class="nav-link text-info">Carrello</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
		<div class="col-12 col-md-8 p-5 info-user">
            <div class="row">
                <div class="col-12">
                    <h1 class="display-3 text-success"><span class="small text-secondary">Benvenuto,</span>&nbsp;Andrea</h1>
                </div>
                <div class="col-12">
                    <p>Nome: <span class="text-bold">Andrea</span></p>
                    <p>Cognome: <span class="text-boold">Ercolino</span></p>
                    <p>Punti viaggio: <span class="text-bold">20</span></p>
                    <p><a href="#" class="btn btn-outline-warning">Cambia Password</a></p>
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
