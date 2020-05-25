<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Bean" %><%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 25/05/2020
  Time: 09:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Bean> beans = (ArrayList<Bean>) request.getAttribute("componentPack");
    String type = (String) request.getAttribute("type");
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
    <% if(beans != null) { %>
    <%      for(Bean b : beans) {%>
                <p><%=b.toString()%></p>
    <%      }
        }%>


</body>
</html>
