<%@ page import="model.hotel.HotelBean" %>
<%@ page import="model.restaurant.RestaurantBean" %>
<%@ page import="model.tour.TourBean" %>
<%@ page import="model.tour.LuogoBean" %><%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/27/20
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        String tipo = (String) request.getAttribute("tipo");
        if(tipo != null && tipo.equals("hotel")){
            HotelBean daModificare = (HotelBean) request.getAttribute("damodificare");
            if(daModificare != null) { %>
                <form action="">
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="immagine">
                        <label class="custom-file-label" for="immagine">Cambia immagine</label>
                    </div>
                    <div class="form-group">
                        <label for="nome">Modifica costo notte</label>
                        <input type="number" class="form-control" id="nome" name="nome" value="<%=daModificare.getCostoNotte()%>">
                    </div>
                    <div class="form-group">
                        <label for="numeroTelefono">Modifica numero di telefono</label>
                        <input type="number" class="form-control" id="numeroTelefono" name="numeroTelefono" value="<%=daModificare.getNumeroTelefono()%>">
                    </div>
                    <div class="form-group">
                        <label for="stelle">Stelle</label>
                        <input type="number" class="form-control" id="stelle" name="stelle" value="<%=daModificare.getStelle()%>">
                    </div>
                </form>
            <%}%>
    <%} else if(tipo != null && tipo.equals("ristorante")) {
        RestaurantBean daModificare = (RestaurantBean) request.getAttribute("damodificare");
        if(daModificare != null) {%>
            <form action="">
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="immagine">
                    <label class="custom-file-label" for="immagine">Cambia immagine</label>
                </div>
                <div class="form-group">
                    <label for="nome">Modifica costo notte</label>
                    <input type="number" class="form-control" id="nome" name="nome" value="<%=daModificare.getCosto()%>">
                </div>
                <div class="form-group">
                    <label for="numeroTelefono">Modifica numero di telefono</label>
                    <input type="number" class="form-control" id="numeroTelefono" name="numeroTelefono" value="<%=daModificare.getNumeroTelefono()%>">
                </div>
                <div class="form-group">
                    <label for="email">Stelle</label>
                    <input type="number" class="form-control" id="email" name="stelle" value="<%=daModificare.getEmail()%>">
                </div>
            </form>
        <%}%>
    <%} else if(tipo != null && tipo.equals("tour")) {
        TourBean tourdaModificare = (TourBean) request.getAttribute("tourdamodificare");
        LuogoBean luogoDaMidificare = (LuogoBean) request.getAttribute("luogodamodificare");
        if(tourdaModificare != null && luogoDaMidificare != null) {%>
        <form action="">
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="immagine">
                <label class="custom-file-label" for="immagine">Cambia immagine Luogo</label>
            </div>
            <div class="form-group">
                <label for="nome">Modifica costo notte</label>
                <input type="number" class="form-control" id="nome" name="nome" value="<%=tourdaModificare.getCosto()%>">
            </div>
            <div class="form-group">
                <label for="numeroTelefono">Modifica numero di max partecipanti</label>
                <input type="number" class="form-control" id="numeroTelefono" name="numeroTelefono" value="<%=tourdaModificare.getPartecipanti()%>">
            </div>
            <div class="form-group">
                <label for="descrizione">Descrizione Luogo</label>
               <textarea id="descrizione" name="descriione"><%=luogoDaMidificare.getDescrizione()%></textarea>
            </div>
        </form>
    <%}%>
<%}%>