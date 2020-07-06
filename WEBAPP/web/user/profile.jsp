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
    <link rel="stylesheet" href="./css/main.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="./user/style.css">
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
</head>
<body>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cambio Password</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="../ClienteServlet">
                    <input type="hidden" name="action" value="changepwd">
                    <div class="form-group">
                        <label for="password">Inserisci Password</label>
                        <input type="password" minlength="8" name="password" class="form-control" id="password" placeholder="Minimo 8 caratteri">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-warning">Cambia Password</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
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
                    <%if (request.getAttribute("errore") != null && (Boolean) request.getAttribute("errore") == true) {%>
                    <div class="form-text text-danger text-center border border-danger p-3 rounded m-3">
                        <p>I Dati forniti non sono corretti.</p>
                        <ul class="list-unstyled">
                            <li>Password: almeno 8 caratteri</li>
                            <li>Nome: massimo 15 caratteri</li>
                            <li>Cognome: massimo 15 caratteri</li>
                        </ul>
                    </div>
                    <%}%>
                    <%if (request.getAttribute("fotoerr") != null && (Boolean) request.getAttribute("fotoerr") == true) {%>
                    <div class="form-text text-danger text-center border border-danger p-3 rounded m-3">
                        <p>Dimensione massima della foto 10mb</p>
                    </div>
                    <%}%>
                    <p>Nome: <span class="text-bold"><%=bean.getNome()%></span></p>
                    <p>Cognome: <span class="text-boold"><%=bean.getCognome()%></span></p>
                    <p>Punti viaggio: <span class="text-bold"><%=bean.getPuntiViaggio()%></span></p>
                    <p><button type="button" data-toggle="modal" data-target="#exampleModal"  class="btn btn-outline-warning">Cambia Password</button></p>
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
