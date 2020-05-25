<%@ page import="model.cliente.ClienteBean" %><%--
  Created by IntelliJ IDEA.
  User: edrio
  Date: 11/05/2020
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteBean bean = (ClienteBean) session.getAttribute("user");

%>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" style="font-family: 'Dancing Script', serif" href="index.jsp">YourCoupon</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="./index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-success" href="#">Crea il Tuo Pacchetto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ComponentsPackServlet?component=hotel">Hotel</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ComponentsPackServlet?component=ristoranti">Ristoranti</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ComponentsPackServlet?component=tour">Visite Guidate</a>
                </li>

            </ul>
            <ul class="navbar-nav ml-auto">
                <% if(bean == null) {%>
                    <li class="nav-item ">
                        <a class="nav-link text-success" href="./login.jsp">Log-In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./signup.jsp">Sign-Up</a>
                    </li>
                <%} else {%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="small text-muted">Benvenuto, </span> <span class="text-success"><%=bean.getNome()%></span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Il mio profilo</a>
                        <a class="dropdown-item" href="#"></a>
                        <a class="dropdown-item" href="#">Logout</a>
                    </div>
                </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Carrello</a>
                    </li>
                <%}%>
            </ul>
        </div>
    </nav>
</header>