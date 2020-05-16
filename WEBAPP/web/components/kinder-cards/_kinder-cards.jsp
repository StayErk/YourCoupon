<%@ page import="java.util.ArrayList" %>
<%@ page import="model.tour.LuogoBean" %>
<%@ page import="java.util.UUID" %>
<%@ page import="model.pacchetto.PacchettoBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.Bean" %>
<%@ page import="model.hotel.HotelBean" %><%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 11/05/2020
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HashMap<UUID, ArrayList<Bean>> pacchetti = (HashMap<UUID, ArrayList<Bean>>) request.getAttribute("pacchetti");
    Object errore =  request.getAttribute("errore");
    if(pacchetti == null && errore == null) {
        response.sendRedirect(response.encodeURL("PacchettiServlet?action=retrieve"));
    }
    PacchettoBean pacchettoBean = new PacchettoBean();
    HotelBean hotelBean = new HotelBean();



%>

<div class="card-deck">
    <div class="row">
        <% if(pacchetti != null) { %>
            <% for(UUID p: pacchetti.keySet()){ %>
                <% pacchettoBean = (PacchettoBean) pacchetti.get(p).get(0); hotelBean = (HotelBean) pacchetti.get(p).get(1); %>
                    <%if(pacchettoBean != null && hotelBean != null){%>
                        <% if(pacchettoBean.isPredefinito()) { %>
                        <div class="col-lg-4  col-md-6  col-12 mb-3">
                            <div class="card shadow">
                                <img src="<%=hotelBean.getImmagine()%>" class="card-img-top img-fluid h-50" alt="<%=hotelBean.getNome()%>">
                                <div class="card-body">
                                    <h4 class="card-title text-primary">Pacchetto: "<em><%=hotelBean.getCitta()%></em>"</h4>
                                    <h5 style="font-family: 'Montserrat', sans-serif">Soggiorno in struttura: "<em><%=hotelBean.getNome()%></em>"</h5>
                                    <p class="card-text"><%=pacchettoBean.getCosto()%>€</p>
                                    <a class="btn btn-primary" href="<%=response.encodeURL("PacchettiServlet?action=bykey&key="+pacchettoBean.getId())%>">Dettagli</a>
                                </div>
                            </div>
                        </div>
                        <%}%>
                    <%}%>
                <% } %>
            <%} else {%>
                <h1>pacchetti null</h1>
            <% } %>
    </div>
</div>


