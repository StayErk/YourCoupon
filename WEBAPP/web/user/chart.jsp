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
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
<!-- Navbar -->
<%@include file="../components/navbar/_navbar.jsp"%>
<!-- Una colonna dove saranno presenti propic, miei ordini e link al carrello -->
<form class="hide d-none">
    <input id="sessionid" type="hidden" name="" value="<%=request.getSession().getId()%>">
</form>
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
                    <button class="btn btn-primary" onclick="refreshCarrello()">Refresh</button>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Città di destinazione</th>
                                <th>Durata</th>
                                <th>Numero persone</th>
                                <th>Totale pacchetto</th>
                            </tr>
                        </thead>
                        <tbody id="data">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Una colonna più larga dove saranno presenti tutte le info dell'utente -->
<!-- Footer -->
<%@include file="../components/footer/_footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="../components/script/chart.js"></script>
</body>
</html>
