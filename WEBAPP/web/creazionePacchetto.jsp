<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/14/20
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/main.css">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="components/navbar/_navbar.jsp"%>
    <div class=" row  justify-content-center mt-5 mb-5">
        <div class="col-6">
            <%@include file="components/creazionePacchetto/_multiStepForm.jsp"%>
        </div>
    </div>
    <%@include file="components/footer/_footer.jsp"%>



    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="./components/creazionePacchetto/multiStepForm.js"></script>
</body>
</html>
