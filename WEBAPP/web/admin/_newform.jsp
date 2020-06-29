<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/27/20
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(tipo != null && tipo.equals("hotel")){ %>
        <form action="AdminServlet" method="post">
            <input type="hidden" name="tipo" value="<%=tipo%>">
            <input type="hidden" name="action" value="new">
            <div class="form-group">
                <label for="nome">Inserisci Nome Hotel</label>
                <input type="text" name="nome" id="nome" class="form-control" maxlength="20" required>
            </div>
            <div class="form-group">
                <label for="indirizzo">Inserisci Indirizzo</label>
                <input id="indirizzo" type="text" class="form-control" name="indirizzo" maxlength="50" required>
            </div>
            <div class="form-group">
                <label for="citta">Scegli citta</label>
                <input class="form-control" type="text" name="citta" list="nomidicitta" id="citta" maxlength="20" required>
                <datalist id="nomidicitta">
                </datalist>
            </div>
            <div class="form-group">
                <label for="costonotte">Inserisci costo notte</label>
                <input type="number" class="form-control" id="costonotte" name="costonotte" required>
            </div>
            <div class="form-group">
                <label for="numeroTelefono">Inserisci numero di telefono</label>
                <input type="text" class="form-control" id="numeroTelefono" name="numeroTelefono" maxlength="11"  required>
            </div>
            <div class="form-group">
                <label for="email">Inserisci Email</label>
                <input type="email" class="form-control" id="email" name="email" maxlength="30" required>
            </div>
            <div class="form-group">
                <label for="stelle">inserisci Stelle</label>
                <input type="number" class="form-control" id="stelle" min="1" max="5" name="stelle"  required>
            </div>
            <div class="form-group">
                <label for="immagine">Inserisci link Immagine</label>
                <input type="url" name="immagine" id="immagine" class="form-control" maxlength="200" required>
            </div>
            <div class="form-group">
                <button class="btn btn-primary" type="submit">Crea</button>
            </div>
        </form>
<%} else if (tipo != null && tipo.equals("ristoranti")) { %>
    <form action="AdminServlet" method="post">
        <input type="hidden" name="tipo" value="<%=tipo%>">
        <input type="hidden" name="action" value="new">
        <div class="form-group">
            <label for="nome">Inserisci Nome Ristorante</label>
            <input type="text" name="nome" id="nome" class="form-control" maxlength="20" required>
        </div>
        <div class="form-group">
            <label for="indirizzo">Inserisci Indirizzo Ristorante</label>
            <input id="indirizzo" type="text" class="form-control" name="indirizzo" maxlength="50" required>
        </div>
        <div class="form-group">
            <label for="citta">Scegli citta</label>
            <input class="form-control" type="text" name="citta" list="nomidicitta" id="citta" maxlength="20" required>
            <datalist id="nomidicitta">
            </datalist>
        </div>
        <div class="form-group">
            <label for="costonotte">Inserisci costo pasto</label>
            <input type="number" class="form-control" min="1" id="costonotte" name="costo" required>
        </div>
        <div class="form-group">
            <label for="numeroTelefono">Inserisci numero di telefono</label>
            <input type="text" class="form-control" id="numeroTelefono" name="numeroTelefono" maxlength="11"  required>
        </div>
        <div class="form-group">
            <label for="email">Inserisci Email</label>
            <input type="email" class="form-control" id="email" name="email" maxlength="30" required>
        </div>
        <div class="form-group">
            <label for="immagine">Inserisci link Immagine</label>
            <input type="url" name="immagine" id="immagine" class="form-control" maxlength="200" required>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Crea</button>
        </div>
    </form>
<%} else if (tipo != null && tipo.equals("tour")) {%>
    <form action="AdminServlet" method="post">
        <input type="hidden" name="tipo" value="<%=tipo%>">
        <input type="hidden" name="action" value="new">
        <input type="hidden" name="id" id="sessionid" value="<%=request.getSession().getId()%>">
        <div class="form-group">
            <label for="nome">Inserisci Luogo Tour</label>
            <select class="form-control" name="luoghi" id="luoghi"></select>
        </div>
        <div class="form-group">
            <label for="costo">Inserisci costo</label>
            <input type="number" min="1" class="form-control" id="costo" name="costo" required>
        </div>
        <div class="form-group">
            <label for="numeroPartecipanti">Inserisci Numero partecipanti medi</label>
            <input type="number" min="1" class="form-control" id="numeroPartecipanti" name="numeroPartecipanti" min="1"  required>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Crea</button>
        </div>
    </form>
<script src="../components/script/luoghi.js"></script>
<%} else if(tipo != null && tipo.equals("luogo")){%>
    <form action="AdminServlet" method="post">
        <input type="hidden" name="tipo" value="<%=tipo%>">
        <input type="hidden" name="action" value="new">
        <div class="form-group">
            <label for="nome">Inserisci Nome Luogo</label>
            <input type="text" class="form-control" maxlength="20" id="nome" name="nome" required>
        </div>
        <div class="form-group">
            <label for="indirizzo">Inserisci Indirizzo</label>
            <input type="text" class="form-control" id="indirizzo" name="indirizzo" required>
        </div>
        <div class="form-group">
            <input class="form-control" type="text" name="citta" list="nomidicitta" id="citta" maxlength="20" required>
            <datalist id="nomidicitta">
            </datalist>
        </div>
        <div class="form-group">
            <label for="descrizione">Inserisci Descrizione</label>
            <textarea name="descrizione" id="descrizione" cols="30" rows="10" class="form-control"></textarea>
        </div>
        <div class="form-group">
            <label for="immagine">Inserisci link Immagine</label>
            <input type="url" name="immagine" id="immagine" class="form-control" maxlength="200" required>
        </div>
        
        <div class="form-group">
            <button class="btn btn-primary" type="submit">Crea</button>
        </div>
    </form>
<%}%>