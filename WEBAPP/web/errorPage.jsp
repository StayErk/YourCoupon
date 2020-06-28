<%--
  Created by IntelliJ IDEA.
  User: peppe
  Date: 28/06/2020
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage = "true"%>
<% System.out.println(request.getPathInfo()); %>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Maven+Pro:400,900" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/errorPage.css">
    <link type="text/css" rel="stylesheet" href="../css/errorPage.css">
    <title>Error</title>
</head>
<body>
    <div id="notfound">
        <div class="notfound">
            <div class="notfound-404">
                <h1>404</h1>
            </div>
            <h2>Ci scusiamo, Pagina non trovata!</h2>
            <p>La pagina che stai cercando potrebbe essere stata rimossa o temporaneamente non disponibile.</p>
            <a href="http://localhost:8080/YourCoupon2_war_exploded/index.jsp">Torna alla Homepage</a>
        </div>
    </div>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</body>
</html>