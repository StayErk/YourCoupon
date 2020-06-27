<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/18/20
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <%@include file="components/navbar/_navbar.jsp" %>
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-6 bg-light shadow rounded p-5">
                <h1 class="text-center text-success display-4"><span class="small text-black-50">Login</span><br>YourCoupon</h1>
                <form method="post" action="ClienteServlet" id="loginForm">
                    <% if((request.getAttribute("errore-login") != null && (Boolean) request.getAttribute("errore-login") == true) || (request.getAttribute("non-esistente") != null && (Boolean) request.getAttribute("non-esistente") == true)) { %>
                        <span class="form-text text-danger text-center border border-danger p-3 rounded m-3">
                            Password o Email errate, oppure prova a <a href="<%=response.encodeURL("./signup.jsp")%>">Registrarti</a>
                        </span>
                    <%}%>
                    <% if((request.getAttribute("cambiopwd") != null && (Boolean) request.getAttribute("cambiopwd") == true)) { %>
                    <span class="form-text text-primary text-center border border-primary p-3 rounded m-3">
                            Accedi con la nuova password
                        </span>
                    <%}%>
                    <div class="form-group">
                        <label for="email">email</label>
                        <input class="form-control" type="email" id="email" name="email" required>
                        <input type="hidden" value="login" name="action">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input class="form-control" type="password" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn-primary btn">LogIn!</button>
                </form>
            </div>
        </div>
    </div>
    <%@include file="components/footer/_footer.jsp"%>
</body>
</html>
