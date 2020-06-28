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
        if(tipo != null && tipo.equals("hotel")){
            HotelBean daModificare = (HotelBean) request.getAttribute("damodificare");
            if(daModificare != null) { %>
                <form action="AdminServlet" method="post">
                    <input type="hidden" name="id" value="<%=daModificare.getId()%>">
                    <input type="hidden" name="tipo" value="<%=tipo%>">
                    <input type="hidden" name="action" value="edit">
                    <div class="form-group">
                        <label for="costonotte">Modifica costo notte</label>
                        <input type="number" class="form-control" id="costonotte" name="costonotte" value="<%=daModificare.getCostoNotte()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="numeroTelefono">Modifica numero di telefono</label>
                        <input type="number" class="form-control" id="numeroTelefono" name="numeroTelefono" value="<%=daModificare.getNumeroTelefono()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="stelle">Stelle</label>
                        <input type="number" class="form-control" id="stelle" min="1" max="5" name="stelle" value="<%=daModificare.getStelle()%>" required>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary" type="submit">Modifica</button>
                    </div>
                </form>
            <%}%>
    <%} else if(tipo != null && tipo.equals("ristoranti")) {
        RestaurantBean daModificare = (RestaurantBean) request.getAttribute("damodificare");
        if(daModificare != null) {%>
            <form action="AdminServlet" method="post">
                <input type="hidden" name="id" value="<%=daModificare.getId()%>">
                <input type="hidden" name="tipo" value="<%=tipo%>">
                <input type="hidden" name="action" value="edit">
                <div class="form-group">
                    <label for="costo">Modifica costo pasto</label>
                    <input type="number" class="form-control" id="costo" name="costo" min="1" value="<%=daModificare.getCosto()%>">
                </div>
                <div class="form-group">
                    <label for="numeroTelefono">Modifica numero di telefono</label>
                    <input type="text" class="form-control" id="numeroTelefono" name="numeroTelefono" value="<%=daModificare.getNumeroTelefono()%>">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="text" class="form-control" id="email" name="email" value="<%=daModificare.getEmail()%>">
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Modifica</button>
                </div>
            </form>
        <%}%>
    <%} else if(tipo != null && tipo.equals("tour")) {
        TourBean tourdaModificare = (TourBean) request.getAttribute("tourdamodificare");
        LuogoBean luogoDaMidificare = (LuogoBean) request.getAttribute("luogodamodificare");
        if(tourdaModificare != null && luogoDaMidificare != null) {%>
        <form action="">
            <div class="form-group">
                <label for="costo">Modifica costo</label>
                <input type="number" class="form-control" id="costo" name="costo" value="<%=tourdaModificare.getCosto()%>">
            </div>
            <div class="form-group">
                <label for="numeroTelefono">Modifica numero di max partecipanti</label>
                <input type="number" class="form-control" id="numeroTelefono" name="numeroTelefono" value="<%=tourdaModificare.getPartecipanti()%>">
            </div>
            <div class="form-group">
                <label for="descrizione">Descrizione Luogo</label>
               <textarea id="descrizione" name="descriione"><%=luogoDaMidificare.getDescrizione()%></textarea>
            </div>
            <div class="form-group">
                <label for="immagine">Inserisci link Immagine</label>
                <input type="url" name="immagine" id="immagine" class="form-control" maxlength="20">
            </div>
        </form>
    <%}%>
<%}%>