<%--
  Created by IntelliJ IDEA.
  User: edrio
  Date: 27/04/2020
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>YourCoupon Homepage</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="components/header-form/header-form.css">
    <link rel="stylesheet" href="components/kinder-cards/kinder-cards.css">
  </head>
  <body>
    <!-- Navbar -->
    <%@include file="components/navbar/_navbar.jsp"%>
    <!-- Header w/ form -->
    <main>
      <%@include file="components/header-form/_header-from.jsp"%>
      <!-- default coupons -->
      <div class="container p-5">
        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" id="notification" data-delay="2000" style="position: fixed; top: 20px; left: 5px; z-index: 100">
          <div class="toast-header">
            <strong class="mr-auto">Pacchetto aggiunto correttamente</strong>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="toast-body">
            Hai aggiunto correttamente il pacchetto "visita ad <span id="aggiunto"></span>" al carrello
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <h1 class="text-center text-primary">I Pacchetti scelti da noi</h1>
          </div>
        </div>
      </div>
      <div class="container mb-3">
        <%@include file="components/kinder-cards/_kinder-cards.jsp"%>
      </div>
    </main>
    <!-- Footer w/ info -->
    <%@include file="components/footer/_footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="components/kinder-cards/kindercards.js"></script>
  </body>
</html>
