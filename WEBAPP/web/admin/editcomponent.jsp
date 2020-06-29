<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/27/20
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String tipo = (String) request.getAttribute("tipo"); %>
<html>
<head>
    <%if (tipo!= null && !tipo.equals("")) {%>
        <title>Modifica <%=tipo%>></title>
    <%}%>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="admin.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <nav id="sidebar" class="rounded-right " >
        <div class="sidebar-header">
            <%if (tipo!= null && !tipo.equals("")) {%>
                <h4>Modifica <%=tipo%>></h4>
            <%}%>
        </div>
        <ul class="list-unstyled components">
            <li class="nav-item"><a class="nav-link" href="./managehotel.jsp">Gestisci Hotel</a></li>
            <li class="nav-item"><a class="nav-link" href="./manageristorante.jsp">Gestisci Ristoranti</a></li>
            <li class="nav-item"><a class="nav-link" href="./managertour.jsp">Gestisci Tour</a></li>
            <li class="nav-item"><a class="nav-link" href="./managepacchetti.jsp">Gestisci Pacchetti</a></li>

        </ul>
        <ul class="list-unstyled buttons">
            <li class="nav-item"><a href="../creazionePacchetto.jsp" class="btn btn-outline-success ml-auto mr-auto">Crea Pacchetto</a></li>
            <li class="nav-item"><a href="../ClienteServlet?action=logout" class="btn btn-outline-danger ml-auto mr-auto">Log Out</a></li>
        </ul>

    </nav>
    <div id="content">
        <nav class="navbar navbar-expand-lg bg-primary navbar-dark mb-4 rounded item-shadow">
            <div class="container">

                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                    <span>Menu</span>
                </button>

                <h3 class="text-center ml-auto mr-auto text-light m-0">Modifica del sito</h3>
                <div>
                    <a href="./index.jsp" class="btn btn-success">Pannello Principale</a>
                </div>


            </div>
        </nav>
        <%@include file="_editform.jsp"%>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="toggle-sidebar.js"></script>
<script src="../components/catalogbean/catalogbean.js"></script>
</body>
</html>
