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
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="components/navbar/_navbar.jsp" %>
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-12 col-md-6 bg-light shadow rounded p-5">
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
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
