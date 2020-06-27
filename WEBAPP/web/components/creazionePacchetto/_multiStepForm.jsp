<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/13/20
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post"  action="PacchettiServlet" id="form"  class="rounded shadow text-center" enctype="application/x-www-form-urlencoded">
	<div id="sf1" class="frm p-3">
        <fieldset>
            <input type="hidden" name="id" id="sessionid" value="<%=request.getSession().getId()%>">
            <legend class="text-success">Inserisci i dati del coupon</legend>
            <div class="form-group">
                <label for="durata" class="col-lg-4 control-label">Durata pacchetto</label>
                <div class="col-lg-12">
                    <input type="number" name="durata" id="durata" min="1" placeholder="durata in giorni" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="persone" class="col-lg-4 control-label">Numero Persone</label>
                <div class="col-lg-12">
                    <input type="number" name="persone" id="persone" min="1" placeholder="Partecipanti" class="form-control" required>
                </div>
            </div>
            <div class="form-group">
                <label for="citta" class="col-lg-4 control-label">Scegli Citta</label>
                <div class="col-lg-12">
                    <select name="citta" id="citta" class="form-control">
                    </select>
                </div>
            </div>
            <div class="form-group">
	            <div class="col-12">
                    <button class="btn btn-primary open1" type="button">Avanti</button>
                </div>
            </div>
        </fieldset>
    </div>
	<div id="sf2" class="frm p-3">
        <fieldset >
            <legend class="text-success">Scegli Hotel</legend>
            <div class="form-group" >
                <div class="card-columns" id="hotelFormGroup"></div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <button class="btn btn-secondary back2" type="button">Indietro</button>
                    <button class="btn btn-primary open2" type="button">Avanti</button>
                </div>
            </div>
        </fieldset>
    </div>
	<div id="sf3" class="frm p-3">
        <fieldset>
            <legend class="text-success">Scegli Ristorante</legend>
            <div class="form-group">
                <div class="card-columns" id="restaurantFormGroup"></div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <button class="btn btn-secondary back3" type="button">Indietro</button>
                    <button class="btn btn-primary open3" type="button">Non Inserire Ristorante</button>
                </div>
            </div>
        </fieldset>
    </div>
	<div id="sf4" class="frm p-3">
        <fieldset>
            <legend class="text-success">Scegli Tour</legend>
            <div class="form-group" >
                <div class="card-columns" id="tourFormGroup"></div>
            </div>
            <div class="form-group">
                <div class="col-12">
                    <button class="btn btn-secondary back4" type="button">Indietro</button>
                    <button class="btn btn-primary open4" type="button">Non Inserire Tour</button>
                </div>
            </div>
        </fieldset>
    </div>
    <div id="sf5" class="frm p-3">
        <fieldset>
            <legend class="text-success">Riepilogo</legend>
            <div class="form-group">
                <table class="table">
                    <thead>
                        <th scope="col">Tipo</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Prezzo unitario</th>
                    </thead>
                    <tbody  id="riepilogo">
                    </tbody>
                </table>
                <p id="paragrafoRep"></p>
                <input type="hidden" name="pacchetto" id="pacchetto" value="">
            </div>
            <div class="form-group">
                <div class="col-12">
                    <button class="btn btn-secondary back5" type="button">Indietro</button>
                    <button class="btn btn-primary open5" type="submit">Aggiungi al carrello</button>
                </div>
            </div>
        </fieldset>
    </div>
</form>




