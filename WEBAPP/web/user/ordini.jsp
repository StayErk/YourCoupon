<%@ page import="java.util.ArrayList" %>
<%@ page import="model.fattura.FatturaBean" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/20/20
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if(request.getAttribute("fatture") == null && request.getAttribute("errore") == null) {
        response.sendRedirect(response.encodeURL("FatturaServlet?action=retrievedati"));
    } %>
<html>
<head>
    <title>I miei ordini</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="style.css">
</head>

    <!-- Navbar -->
    <%@include file="../components/navbar/_navbar.jsp"%>
    <!-- Una colonna dove saranno presenti propic, miei ordini e link al carrello -->
    <div class="container-fluid">
        <div class="row">
            <%@include file="_profilesidebar.jsp"%>
            <div class="col-12 col-md-8 p-5 info-user">
                <div class="row">
                    <div class="col-12">
                        <h1 class="display-4 text-success"><span class="small text-secondary">I tuoi ordini:</span>&nbsp<%=bean.getNome()%></h1>
                    </div>
                    <div class="col-12 mt-3">
                        <table class="table table-responsive">
                            <thead class="rounded-top">
                            <tr class="bg-primary text-light ">
                                <th scope="col">Numero Fattura</th>
                                <th scope="col">Prezzo Fattura</th>
                                <th scope="col">Data di acquisto</th>
                            </tr>
                            </thead>
                            <% List<FatturaBean> fatture = (List<FatturaBean>) request.getAttribute("fatture");
                                if(fatture != null) { %>
                                    <tbody>
                                        <%for(FatturaBean fattura : fatture) {%>
                                            <tr>
                                                <td><%=fattura.getId()%></td>
                                                <td><%=fattura.getTotale()%> €</td>
                                                <td><%=fattura.getData().toString()%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                <%} else if(fatture == null && request.getAttribute("errore") != null && ((Boolean) request.getAttribute("errore"))) {%>
                                    <span class="form-text text-danger text-center border border-danger p-3 rounded m-3">
                                        Errore, riprova più tardi.
                                    </span>
                            <%}%>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Una colonna più larga dove saranno presenti tutte le info dell'utente -->
    <!-- Footer -->
    <%@include file="../components/footer/_footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="../components/script/actions.js"></script>
    </body>
</html>
