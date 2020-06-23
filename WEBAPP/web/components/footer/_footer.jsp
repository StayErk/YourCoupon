<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/11/20
  Time: 6:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="bg-primary text-success pt-5">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4">
                <h2>Mappa del sito</h2>
                <nav>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-light" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="#">Crea il tuo pacchetto</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="<%=response.encodeURL("./hotel.jsp?type=hotel")%>">Hotel</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="<%=response.encodeURL("./restaurant.jsp?type=ristoranti")%>">Ristoranti</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="<%=response.encodeURL("./tour.jsp?type=tour")%>">Visite Guidate</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col-12 col-md-4">
                <h2>Autori</h2>
                <nav>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-light" href="https://it.pornhub.com/">Giuseppe Cardaropoli</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="https://www.youtube.com/user/PositanoYoung">Andrea Ercolino</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="https://www.instagram.com/x.mariachiara/">Maria Chiara Nasto</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="col-12 col-md-4">
                <h2>Accessi</h2>
                <nav>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-light" href="#">Log-In</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="#">Registrazione Nuovo Utente</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="<%=response.encodeURL("admin/")%>">Pannello Admin</a>
                        </li>
                    </ul>
                </nav>
            </div>

        </div>
    </div>
</footer>
