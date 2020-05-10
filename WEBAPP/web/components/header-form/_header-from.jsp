<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/10/20
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- header con form per la ricerca di pacchetti di default -->


<section class="header-form">
    <div class="container ">
        <div class="row header-form--body">
            <div class="col-12 header-form--title">
                <h1 class="text-center">Trova la vacanza perfetta per te</h1>
            </div>
            <div class="col-12 rounded header-form--form p-5">
                <form class="mx-auto my-auto">
                    <div class="form-row">
                        <div class="col-12 col-md-3">
                            <select class="max-size">
                                <option value="nil" selected>Scegli città</option>
                                <option value="roma">Roma</option>
                                <option value="firenze">Firenze</option>
                            </select>
                        </div>
                        <div class="col-12 col-md-3">
                            <input type="number" class="form-control max-size" placeholder="Persone">
                        </div>
                        <div class="col-12 col-md-3">
                            <input type="text" class="form-control max-size" placeholder="Prezzo massimo">
                        </div>
                        <div class="col-12 col-md-3 text-center">
                            <button class="btn btn-primary" type="submit">Cerca</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
