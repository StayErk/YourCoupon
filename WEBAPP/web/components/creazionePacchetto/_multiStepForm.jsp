<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/13/20
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="" id="form">
	<div id="sf1" class="frm">
        <fieldset>
            <legend>Step 1</legend>
            <div class="form-group">
                <label for="durata" class="col-lg-2 control-label">Durata pacchetto</label>
                <div class="col-lg-6">
                    <input type="number" name="durata" id="durata" min="1" placeholder="durata in giorni">
                </div>
            </div>
            <div class="form-group">
                <label for="persone" class="col-lg-2 control-label">Numero Persone</label>
                <div class="col-lg-6">
                    <input type="number" name="persone" id="persone" min="1" placeholder="Partecipanti">
                </div>
            </div>
            <div class="form-group">
                <label for="citta" class="col-lg-2 control-label">Scegli Citta</label>
                <div class="col-lg-6">
                    <select name="citta" id="citta">
                        <option value="roma">Roma</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
	            <div class="col-lg-10 col-lg-offset-2">
                    <button class="btn btn-primary open1" type="button">Next</button>
                </div>
            </div>
        </fieldset>
    </div>
	<div id="sf2" class="frm">
        <fieldset>
            <legend>Step 2</legend>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button class="btn btn-secondary back2" type="button">Previus</button>
                    <button class="btn btn-primary open2" type="button">Next</button>
                </div>
            </div>
        </fieldset>
    </div>
	<div id="sf3" class="frm">
        <fieldset>
            <legend>Step 3</legend>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button class="btn btn-secondary back3" type="button">Previus</button>
                    <button class="btn btn-primary open3" type="button">Next</button>
                </div>
            </div>
        </fieldset>
    </div>
	<div id="sf4" class="frm">
        <fieldset>
            <legend>Step 4</legend>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button class="btn btn-secondary back4" type="button">Previus</button>
                    <button class="btn btn-primary open4" type="button">Submit</button>
                </div>
            </div>
        </fieldset>
    </div>
</form>




