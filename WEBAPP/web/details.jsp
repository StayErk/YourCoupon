<%@ page import="model.pacchetto.PacchettoBean" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Bean" %>
<%@ page import="model.hotel.HotelBean" %><%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/16/20
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Bean> beans =(ArrayList<Bean>) request.getAttribute("arraybeans");
    String error = (String) request.getAttribute("error");
    if(beans == null && error == null){
        response.sendRedirect(response.encodeURL("PacchettiServlet?action=bykey&key="+request.getParameter("key")));
    }
%>
<html>
<head>
    <title>Dettagli pacchetto</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <!--Navbar-->
    <%@include file="components/navbar/_navbar.jsp"%>
    <% if((beans) != null) {%>
        <% HotelBean hotelBean= (HotelBean) beans.get(1); PacchettoBean pacchettoBean= (PacchettoBean) beans.get(0); %>
        <main>
            <div class="container mt-5 mb-5">
                <div class="row align-items-center">
                    <div class="col-4">
                        <div class="row">
                            <div class="col-12">
                                <div class="card mx-auto text-white bg-info mb-3" style="max-width: 18rem;">
                                    <div class="card-header">Contenuto del pacchetto:</div>
                                    <div class="card-body">
                                        <p class="card-text">Soggiorno in hotel: <strong><%=hotelBean.getNome()%></strong> <%=hotelBean.getCostoNotte()%>€/notte</p>
                                        <p class="card-text">Pranzo/Cena per <%=pacchettoBean.getPersone()%> persone da </p>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                    <div class="col-8">
                        <div class="row">
                            <div class="col-12">
                                <h1 class="text-success text-center">Dettagli Coupon</h1>
                            </div>
                        </div>
                        <div class="row align-items-center m-2 p-0">
                            <div class="col-6 d-flex">
                                <img src="<%=hotelBean.getImmagine()%>" alt="L'Hotel <%=hotelBean.getNome()%>" class=" p-0 d-inline-block ml-auto w-75 h-75">
                            </div>
                            <div class="col-6">
                                <p>Coupon tutto compreso per <strong class="text-success"><%=pacchettoBean.getPersone()%> persone</strong> </p>
                                <p>Della durata di <strong class="text-success"><%=pacchettoBean.getDurata()%> giorni a <%=hotelBean.getCitta()%>!</strong></p>
                                <p>A soli: <strong class="text-success"><%=pacchettoBean.getCosto()%>€</strong></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    <%}%>

    <!--Footer-->
    <%@include file="components/footer/_footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
