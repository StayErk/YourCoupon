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
        <% if(request.getRequestURI().contains("user") || bean != null) { %>
            <a class="navbar-brand" style="font-family: 'Dancing Script', serif" href="../index.jsp">YourCoupon</a>
        <% } else { %>
            <a class="navbar-brand" style="font-family: 'Dancing Script', serif" href="index.jsp">YourCoupon</a>
        <% } %>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <% if(request.getRequestURI().contains("user") || request.getRequestURI().contains("admin") && bean != null) { %>
                        <a class="nav-link" href="../index.jsp">Home <span class="sr-only">(current)</span></a>
                    <% } else { %>
                        <a class="nav-link" href="./index.jsp">Home <span class="sr-only">(current)</span></a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if(request.getRequestURI().contains("user") || request.getRequestURI().contains("admin") && bean != null) { %>
                        <a class="btn btn-outline-success" href="<%=response.encodeURL("../creazionePacchetto.jsp")%>">Crea il Tuo Pacchetto</a>
                    <% } else { %>
                        <a class="btn btn-outline-success" href="<%=response.encodeURL("./creazionePacchetto.jsp")%>">Crea il Tuo Pacchetto</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if(request.getRequestURI().contains("user") || request.getRequestURI().contains("admin") && bean != null) { %>
                        <a class="nav-link" href="<%=response.encodeURL("../hotel.jsp?type=hotel")%>">Hotel</a>
                    <% } else { %>
                    <a class="nav-link" href="<%=response.encodeURL("./hotel.jsp?type=hotel")%>">Hotel</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if(request.getRequestURI().contains("user") || request.getRequestURI().contains("admin") && bean != null) { %>
                        <a class="nav-link" href="<%=response.encodeURL("../restaurant.jsp?type=ristoranti")%>">Ristoranti</a>
                    <% } else { %>
                        <a class="nav-link" href="<%=response.encodeURL("./restaurant.jsp?type=ristoranti")%>">Ristoranti</a>
                    <% } %>
                </li>
                <li class="nav-item">
                    <% if(request.getRequestURI().contains("user") || request.getRequestURI().contains("admin") && bean != null) { %>
                        <a class="nav-link" href="<%=response.encodeURL("../tour.jsp?type=tour")%>">Visite Guidate</a>
                    <% } else { %>
                        <a class="nav-link" href="<%=response.encodeURL("./tour.jsp?type=tour")%>">Visite Guidate</a>
                    <% } %>
                </li>

            </ul>
            <ul class="navbar-nav ml-auto">
                <% if(bean == null) {%>
                    <li class="nav-item ">
                        <a class="nav-link text-success" href="<%=response.encodeURL("./login.jsp")%>">Log-In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=response.encodeURL("./signup.jsp")%>">Sign-Up</a>
                    </li>
                <%} else {%>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="small text-muted">Ciao, </span> <span class="text-success"><%=bean.getNome()%></span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <% if(request.getRequestURI().contains("user") && bean != null) { %>
                            <a class="dropdown-item" href="<%=response.encodeURL("./profile.jsp")%>">Il mio profilo</a>
                            <a class="dropdown-item" href="<%=response.encodeURL("./ordini.jsp")%>">I miei ordini</a>
                        <% } else { %>
                            <a class="dropdown-item" href="<%=response.encodeURL("./user/profile.jsp")%>">Il mio profilo</a>
                            <a class="dropdown-item" href="<%=response.encodeURL("./user/ordini.jsp")%>">I miei ordini</a>
                        <% } %>
                        <a class="dropdown-item" href="#">Logout</a>
                    </div>
                </li>
                    <li class="nav-item">
                        <% if(request.getRequestURI().contains("user") && bean != null) { %>
                            <a class="nav-link" href="<%=response.encodeURL("./chart.jsp")%>">Carrello</a>
                        <% } else {%>
                            <a class="nav-link" href="<%=response.encodeURL("./user/chart.jsp")%>">Carrello</a>
                        <% } %>
                    </li>
                <%}%>
            </ul>
        </div>
    </nav>
</header>