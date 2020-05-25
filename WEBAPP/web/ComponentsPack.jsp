<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Bean" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.UUID" %>
<%@ page import="model.tour.TourBean" %>
<%@ page import="model.tour.LuogoBean" %><%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 25/05/2020
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Bean> beans = null;
    HashMap<UUID, ArrayList<Bean>> beansHash = null;
    String type = (String) request.getAttribute("type");
    if(type != null && (type.equals("Ristoranti") || type.equals("Hotel"))) {
        beans = (ArrayList<Bean>) request.getAttribute("componentPack");
    }
    else if(type != null && type.equals("Tour")){
        beansHash = (HashMap<UUID, ArrayList<Bean>>) request.getAttribute("componentPack");
    }
    String error = (String) request.getAttribute("error");

    //Se l'utente accede a questa jsp senza passare per la servlet, lo demandiamo alla home
    if((beans == null || beansHash == null) && type == null && error == null){
        response.sendRedirect("./index.jsp");
    }
%>
<html>
<head>
    <title>Lista <%=type%></title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <!-- Navbar -->
    <%@include file="components/navbar/_navbar.jsp"%>

    <%if(beans != null) {
        for(Bean b : beans) {%>
            <p><%=b.toString()%></p>
    <%  }
    } else if(beansHash != null) {
        for(UUID id : beansHash.keySet()) {
            TourBean tourBean = (TourBean) beansHash.get(id).get(0);
            LuogoBean luogoBean = (LuogoBean) beansHash.get(id).get(1); %>
            <p><%= tourBean.toString()+"-------"+luogoBean.toString() %></p>
    <% }
    } %>

    <!-- Footer -->
    <%@include file="components/footer/_footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


</body>
</html>
