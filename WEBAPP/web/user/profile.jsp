<%@ page import="java.io.File" %><%--
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
        <%@include file="_profilesidebar.jsp"%>
		<div class="col-12 col-md-8 p-5 info-user">
            <div class="row">
                <div class="col-12">
                    <h1 class="page-title text-success"><span class="small text-secondary">Ciao,</span>&nbsp;<%=bean.getNome()%></h1>
                </div>
                <div class="col-12">
                    <p>Nome: <span class="text-bold"><%=bean.getNome()%></span></p>
                    <p>Cognome: <span class="text-boold"><%=bean.getCognome()%></span></p>
                    <p>Punti viaggio: <span class="text-bold"><%=bean.getPuntiViaggio()%></span></p>
                    <p><a href="#" class="btn btn-outline-warning">Cambia Password</a></p>
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
    <script src="../components/script/actions.js"></script>
</body>
</html>
