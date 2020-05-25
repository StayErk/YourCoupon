<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Bean" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 25/05/2020
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String type = (String) request.getAttribute("type");
    if(type != null && (type.equals("Ristoranti") || type.equals("Hotel"))) {
        ArrayList<Bean> beans = (ArrayList<Bean>) request.getAttribute("componentPack");
    }
    HashMap<UUID, ArrayList<Bean>> beans = (HashMap<UUID, ArrayList<Bean>>) request.getAttribute("componentPack");
    String error = (String) request.getAttribute("error");

    //Se l'utente accede a questa jsp senza passare per la servlet, lo demandiamo alla home
    if(beans == null && type == null && error == null){
        response.sendRedirect("./index.jsp");
    }
%>
<html>
<head>
    <title>Lista <%=type%></title>
</head>
<body>
    <% if(beans != null){ %>
        <p><%=beans.toString()%></p>
    <%}%>


</body>
</html>
