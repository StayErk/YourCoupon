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
    /*ArrayList<LuogoBean> luoghi = new ArrayList<LuogoBean>();
    luoghi.add((new LuogoBean(UUID.fromString("239c2175-308b-4498-8fda-60eb1a93a78f"), "Neyhf", "6 Browning Drive", "Dingli", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Vivamus vel nulla eget eros elementum pellentesque.", "https://bit.ly/3cfVSpt")));
    luoghi.add((new LuogoBean(UUID.fromString("239c2175-308b-4498-8fda-60eb1a93a78f"), "Neyhf", "6 Browning Drive", "Dingli", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Vivamus vel nulla eget eros elementum pellentesque.", "https://bit.ly/3cfVSpt")));
    luoghi.add((new LuogoBean(UUID.fromString("239c2175-308b-4498-8fda-60eb1a93a78f"), "Neyhf", "6 Browning Drive", "Dingli", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Vivamus vel nulla eget eros elementum pellentesque.", "https://bit.ly/3cfVSpt")));
    luoghi.add((new LuogoBean(UUID.fromString("239c2175-308b-4498-8fda-60eb1a93a78f"), "Neyhf", "6 Browning Drive", "Dingli", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Vivamus vel nulla eget eros elementum pellentesque.", "https://bit.ly/3cfVSpt")));
    luoghi.add((new LuogoBean(UUID.fromString("239c2175-308b-4498-8fda-60eb1a93a78f"), "Neyhf", "6 Browning Drive", "Dingli", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Vivamus vel nulla eget eros elementum pellentesque.", "https://bit.ly/3cfVSpt")));
    luoghi.add((new LuogoBean(UUID.fromString("239c2175-308b-4498-8fda-60eb1a93a78f"), "Neyhf", "6 Browning Drive", "Dingli", "Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.Vivamus vel nulla eget eros elementum pellentesque.", "https://bit.ly/3cfVSpt")));*/
    HashMap<UUID, ArrayList<Bean>> pacchetti = (HashMap<UUID, ArrayList<Bean>>) request.getAttribute("pacchetti");
    Object errore =  request.getAttribute("errore");
    if(pacchetti == null && errore == null) {
        response.sendRedirect(response.encodeURL("PacchettiServlet?action=retrieve"));
    }



%>

<div class="card-deck">
    <div class="row">
        <% if(pacchetti != null) { %>
            <% for(UUID p: pacchetti.keySet()){ %>
                <% PacchettoBean pacchettoBean = (PacchettoBean) pacchetti.get(p).get(0); HotelBean hotelBean = (HotelBean) pacchetti.get(p).get(1); %>
                    <%if(pacchettoBean != null && hotelBean != null){%>
                        <% if(pacchettoBean.isPredefinito()) { %>
                        <div class="col-4 mb-3">
                            <div class="card">
                                <img src="<%=hotelBean.getImmagine()%>" class="card-img-top img-fluid h-50" alt="<%=hotelBean.getNome()%>">
                                <div class="card-body">
                                    <h4 class="card-title text-primary">Pacchetto "<em><%=hotelBean.getCitta()%></em>"</h4>
                                    <h3>Soggiorno in struttura: <%=hotelBean.getNome()%></h3>
                                    <p class="card-text"><%=pacchettoBean.getCosto()%></p>
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


