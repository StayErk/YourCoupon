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
    <div id="pancack" class="card-deck">

    </div>
</div>
