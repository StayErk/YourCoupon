<%@ page import="model.cliente.ClienteBean" %><%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/17/20
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteBean registered = (ClienteBean) request.getAttribute("registrato");
%>
<html>
<head>
    <title>Sign Up page</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body class="">
    <!-- Navbar -->
    <%@include file="components/navbar/_navbar.jsp"%>
    <!-- Main Content -->
    <div class="container mt-5 mb-5">
            <div class="row justify-content-center">
                <div class="col-6 bg-light shadow rounded p-5">
                    <% if(registered == null) {%>
                    <h1 class="text-center text-success display-4"><span class="small text-black-50">Registrati su</span><br>YourCoupon</h1>
                    <form method="post" action="ClienteServlet" id="signUpForm">
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input class="form-control" type="text" id="nome" name="nome" required>
                            <input type="hidden" value="signup" name="action">
                        </div>
                        <div class="form-group">
                            <label for="cognome">Cognome</label>
                            <input class="form-control" type="text" id="cognome" name="cognome" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" type="email" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input class="form-control" type="password" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn-primary btn">Registrati!</button>
                    </form>
                    <%} else {%>
                        <div class="row">
                            <div class="col-12">
                                <h1 class="text-center text-success display-4"><span class="small text-black-50">Registrazione completata</span></h1>
                                <h3 class="text-center" style="font-family: 'Montserrat', sans-serif">Grazie per esserti registrato <%=registered.getNome()%>!</h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <p>Ecco un resoconto dei tuoi dati:</p>
                                <ul class="list-unstyled ml-2 mr-2">
                                    <li>nome: <strong class="text-success"><%=registered.getNome()%></strong></li>
                                    <li>cognome: <strong class="text-success"><%=registered.getCognome()%></strong> </li>
                                    <li>email: <strong class="text-success"><%=registered.getEmail()%></strong></li>
                                </ul>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <a href="#" class="btn btn-success w-100">Log-In</a>
                            </div>
                            <div class="col-6">
                                <a href="./index.jsp" class="btn btn-outline-info w-100">Torna Alla Home</a>
                            </div>
                        </div>

                    <%}%>

                </div>
            </div>

    </div>
    <!-- Footer -->
    <%@include file="components/footer/_footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
