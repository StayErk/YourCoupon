<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/28/20
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String tipo = "";
    if(request.getRequestURI().contains("managehotel")) {
        tipo = "hotel";
    } else if (request.getRequestURI().contains("manageristorante")) {
        tipo = "ristoranti";
    } else if (request.getRequestURI().contains("managetour")) {
        tipo = "tour";
    }
%>
<div class="container mt-5">
	<div class="row">
		<div class="col">
            <form onsubmit="return false" id="form-filter" class="d-flex flex-column flex-md-row justify-content-between mx-auto p-3 border border-primary rounded">
                <div class="form-check form-check-inline">
                    <input type="hidden" value="<%=request.getSession().getId()%>" name="sessionid">
                    <label class="form-check-label mr-3" for="prezzo_ASC">Prezzo crescente</label>
                    <input class="form-check-input" type="radio" id="prezzo_ASC" name="prezzo">
                </div>
                <div class="form-check form-check-inline">
                    <label class="form-check-label mr-3" for="prezzo_DESC">Prezzo descrescente</label>
                    <input class="form-check-input" type="radio" id="prezzo_DESC" name="prezzo" checked>
                </div>
                <select id="citta" name="citta">
                    <option value="all" selected>Seleziona Citta</option>
                </select>
                <button type="submit" id="btn" class="btn btn-primary d-inline-block ml-md-auto">Applica Filtro</button>
                <% if (request.getRequestURI().contains("admin")) {%>
                    <a id="btn" class="btn btn-success d-inline-block ml-md-auto" href="AdminServlet?component=<%=tipo%>&action=new">Aggiungi Nuovo</a>
                <%} if(request.getRequestURI().contains("admin") && request.getRequestURI().contains("managetour")){%>
                    <a id="btn" class="btn btn-success d-inline-block ml-md-auto" href="AdminServlet?component=luogo&action=new">Aggiungi Nuovo Luogo</a>
                <%}%>
            </form>
        </div>
	</div>
</div>

