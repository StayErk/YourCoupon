<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/10/20
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- header con form per la ricerca di pacchetti di default -->


<section class="section-bg">
    <div class="container header-form d-flex align-items-center">
        <div class="row header-form--body w-100">
            <div class="col-12 p-3">
                <div class="row">
                    <div class="col-12 header-form--title mb-5 ">
                        <h1 class="text-center text-light display-3">Trova la vacanza perfetta per te</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12 rounded header-form--form p-4">
                        <form class="mx-auto my-auto">
                            <div class="form-row">
                                <div class="col-12 my-3 my-md-0 col-md-3">
                                    <select class="max-size form-control">
                                        <option value="nil" selected>Scegli città</option>
                                        <option value="roma">Roma</option>
                                        <option value="firenze">Firenze</option>
                                    </select>
                                </div>
                                <div class="col-12 my-3 my-md-0 col-md-3">
                                    <input type="number" class="form-control max-size" min="1" placeholder="Persone">
                                </div>
                                <div class="col-12 my-3 my-md-0 col-md-3">
                                    <input type="number" class="form-control max-size" min="1" placeholder="Durata">
                                </div>
                                <div class="col-12 my-3 my-md-0 col-md-3 text-center">
                                    <button class="btn btn-primary" type="submit">Cerca</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
