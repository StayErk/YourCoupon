<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/13/20
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="" id="form" class="rounded shadow text-center">
	<div id="sf1" class="frm p-3">
        <fieldset>
            <input type="hidden" name="id" id="sessionid" value="<%=request.getSession().getId()%>">
            <legend class="text-success">Inserisci i dati del coupon</legend>
            <div class="form-group">
                <label for="durata" class="col-lg-4 control-label">Durata pacchetto</label>
                <div class="col-lg-12">
                    <input type="number" name="durata" id="durata" min="1" placeholder="durata in giorni" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="persone" class="col-lg-4 control-label">Numero Persone</label>
                <div class="col-lg-12">
                    <input type="number" name="persone" id="persone" min="1" placeholder="Partecipanti" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label for="citta" class="col-lg-4 control-label">Scegli Citta</label>
                <div class="col-lg-12">
                    <select name="citta" id="citta" class="form-control">
                        <option value="roma">Roma</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
	            <div class="col-12">
                    <button class="btn btn-primary open1" type="button">Next</button>
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
                    <button class="btn btn-secondary back2" type="button">Previus</button>
                    <button class="btn btn-primary open2" type="button">Next</button>
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
                    <button class="btn btn-secondary back3" type="button">Previus</button>
                    <button class="btn btn-primary open3" type="button">Non Inserire Ristorante</button>
                </div>
            </div>
        </fieldset>
    </div>
	<div id="sf4" class="frm p-3">
        <fieldset>
            <legend class="text-success">Step 4</legend>
            <div class="form-group">
                <div class="col-12">
                    <button class="btn btn-secondary back4" type="button">Previus</button>
                    <button class="btn btn-primary open4" type="button">Submit</button>
                </div>
            </div>
        </fieldset>
    </div>
</form>




