<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 6/26/20
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="modImg" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modifica Immagine</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="UploadServlet" enctype="multipart/form-data" id="modificaImg">
                    <div class="input-group mb-3 form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupFileAddon01">Scegli Immagine</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="inputGroupFile01" name="file" aria-describedby="inputGroupFileAddon01">
                            <label class="custom-file-label" for="inputGroupFile01">...</label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
                <button type="button" class="btn btn-primary" onclick="invia()">Salva</button>
            </div>
        </div>
    </div>
</div>
<div class="col-12 col-md-3 p-5  sidebar-bg">
    <div class="row">
        <div class="col-md-12 col-6 p-2 align-items-center">
            <div class="propic-container mx-auto">
                <img class="d-block rounded-circle propic" src="./LoadPhotoServlet" alt="propic"> <!--photo_2020-05-19_22-48-41.jpg foto locale -->
                <div class="propic-desciption rounded-circle text-center">
                    <p class="text"><button class="text-light btn btn-secondary" type="button" data-toggle="modal" data-target="#modImg">Modifica</button></p>
                </div>
            </div>
        </div>
        <div class="col-md-12 col-6 mt-3">
            <nav class="text-center">
                <ul class="list-unstyled">
                    <li class="nav-item"><a href="profile.jsp" class="nav-link text-info active">Profilo</a></li>
                    <li class="nav-item"><a href="ordini.jsp" class="nav-link text-info">I Miei Ordini</a></li>
                    <li class="nav-item"><a href="chart.jsp" class="nav-link text-info">Carrello</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>