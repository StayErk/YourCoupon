<%@ page import="model.Bean" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.tour.TourBean" %>
<%@ page import="model.tour.LuogoBean" %>
<%@ page import="model.hotel.HotelBean" %>
<%@ page import="model.restaurant.RestaurantBean" %><%--
  Created by IntelliJ IDEA.
  User: edrio
  Date: 25/05/2020
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Object o =  request.getAttribute("componentPack");
    String type = request.getParameter("type");
    String error = (String) request.getAttribute("error");
    System.out.println("Tipo: " +  type);
    System.out.println("Errore: " + error);
    ArrayList<Bean> beans = null;
    HashMap<UUID, ArrayList<Bean>> beansHash = null;
    try {
        beans = (ArrayList<Bean>) o;
    } catch (Exception e){
        beans = null;
        beansHash = (HashMap<UUID, ArrayList<Bean>>) o;
    } finally {
        //Se l'utente accede a questa jsp senza passare per la servlet, lo demandiamo alla servlet
        if(o == null && error == null){
            response.sendRedirect(response.encodeRedirectURL("ComponentsPackServlet?component="+type));
        }

        if(type != null && (type.equals("hotel") || type.equals("ristoranti"))) {
            beans =(ArrayList<Bean>) request.getAttribute("componentPack");
        }
        if(type != null && type.equals("tour")) {
            beansHash = (HashMap<UUID, ArrayList<Bean>>) request.getAttribute("componentPack");
        }
    %>
<div class="container p-4">
    <%  String returnedType = (String) request.getAttribute("type");
        if(returnedType != null) {%>
    <div class="card-deck">
        <div class="row">
            <%if(beans != null) {
                if(returnedType.equals("hotel")) {
                    ArrayList<HotelBean> hotels = (ArrayList<HotelBean>) beans.clone();
                    for (HotelBean b: hotels) { %>
            <div class="col-lg-4  col-md-6  col-12 mb-3">
                <div class="card shadow">
                    <img src="<%=b.getImmagine()%>" class="card-img-top img-fluid"  alt="<%=b.getNome()%>">
                    <div class="card-body">
                        <h4 class="card-title" style="font-family: 'Montserrat', sans-serif">Hotel: "<em><%=b.getNome()%></em>"</h4>
                        <p class=" text-primary">Hotel situato a: "<em><%=b.getCitta()%></em>"<br>Per soli: <%=b.getCostoNotte()%>€ a notte/persona.</p>
                    </div>
                </div>
            </div>
            <%}%>
            <%} else if(returnedType.equals("ristoranti")) {
                ArrayList<RestaurantBean> restaurants = (ArrayList<RestaurantBean>) beans.clone();
                for (RestaurantBean b : restaurants) {%>
            <div class="col-lg-4 col-md-6 col-12 mb-3">
                <div class="card shadow">
                    <img src="<%=b.getImmagine()%>" alt="<%=b.getNome()%>" class="card-img-top img-fluid ">
                    <div class="card-body">
                        <h5 class="card-title" style="font-family: 'Montserrat', sans-serif">Ristorante:  "<em><%=b.getNome()%></em>"</h5>
                        <p class="card-text">Ristorante situato a: "<em><%=b.getCitta()%></em>"<br>Per soli: <%=b.getCosto()%>€ a persona</p>
                    </div>
                </div>
            </div>
            <%}%>
            <%}} else if(beansHash != null) {
                for(UUID id : beansHash.keySet()) {
                    TourBean tourBean = (TourBean) beansHash.get(id).get(0);
                    LuogoBean luogoBean = (LuogoBean) beansHash.get(id).get(1); %>
            <div class="col-lg-4 col-md-6 col-12 mb-3">
                <div class="card shadow">
                    <img src="<%=luogoBean.getImmagine()%>" alt="<%=luogoBean.getNome()%>" class="card-img-top img-fluid ">
                    <div class="card-body">
                        <h5 class="card-title" style="font-family: 'Montserrat', sans-serif">Visita al:  "<em><%=luogoBean.getNome()%></em>"</h5>
                        <p class="card-text">Esplora "<em><%=luogoBean.getCitta()%></em>"<br>A soli: <%=tourBean.getCosto()%>€ a persona</p>
                    </div>
                </div>
            </div>
            <%}%>
            <%}%>
        </div>
    </div>
    <%}
    }%>
</div>
