<%--
  Created by IntelliJ IDEA.
  User: edrio
  Date: 12/05/2020
  Time: 09:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pagina Amministratore</title>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="admin.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
</head>
<body>
    <div class="wrapper">
        <nav id="sidebar" class="rounded-right " >
            <div class="sidebar-header">
                <h4>Pannello Amministratore</h4>
            </div>
            <ul class="list-unstyled components">
                <li class="nav-item"><a class="nav-link" href="./managehotel.jsp">Gestisci Hotel</a></li>
                <li class="nav-item"><a class="nav-link" href="./manageristorante.jsp">Gestisci Ristoranti</a></li>
                <li class="nav-item"><a class="nav-link" href="">Gestisci Tour</a></li>
            </ul>
            <ul class="list-unstyled buttons">
                <li class="nav-item"><a href="#" class="btn btn-outline-success ml-auto mr-auto">Crea Pacchetto</a></li>
                <li class="nav-item"><a href="#" class="btn btn-outline-danger ml-auto mr-auto">Log Out</a></li>
            </ul>

        </nav>
        <div id="content">
            <nav class="navbar navbar-expand-lg bg-primary navbar-dark mb-4 rounded item-shadow">
                <div class="container">

                    <button type="button" id="sidebarCollapse" class="btn btn-info">
                        <i class="fas fa-align-left"></i>
                        <span>Menu</span>
                    </button>

                    <h3 class="text-center ml-auto mr-auto text-light m-0">Statistiche del sito</h3>
                    <div>
                        <a href="../index.jsp" class="btn btn-success">Torna alla Home</a>
                    </div>


                </div>
            </nav>
            <div class="container">
                <div class="row row-cols-1 row-cols-md-3 mt-0">
                    <div class="col mb-4">
                        <div class="card border-primary mb-3 h-100 " style="max-width: 18rem;">
                            <div class="card-header">Fatture &amp; Guadagno</div>
                            <div class="card-body text-primary">
                                <h5 class="card-title">Guadagnati <span class="text-success">500€</span> </h5>
                                <p class="card-text">Per un totale di <strong>20</strong> fatture</p>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-4">
                        <div class="card border-primary mb-3  h-100 " style="max-width: 18rem;">
                            <div class="card-header">Strutture Alberghiere</div>
                            <div class="card-body text-primary">
                                <h5 class="card-title">L'Hotel più scelto è <span class="text-success">Hotel Roma</span> </h5>
                                <p class="card-text">Vi sono <strong>20</strong> Hotel</p>
                                <a href="#" class="btn btn-outline-info">Gestisci Hotel</a>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-4">
                        <div class="card border-primary mb-3  h-100 " style="max-width: 18rem;">
                            <div class="card-header">Strutture Ristorative</div>
                            <div class="card-body text-primary">
                                <h5 class="card-title">Il Ristorante più scelto è <span class="text-success">Da Ciccio</span> </h5>
                                <p class="card-text">Vi sono <strong>20</strong> Ristoranti</p>
                                <a href="#" class="btn btn-outline-info">Gestisci Ristoranti</a>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-4">
                        <div class="card border-primary mb-3  h-100 " style="max-width: 18rem;">
                            <div class="card-header">Tour</div>
                            <div class="card-body text-primary">
                                <h5 class="card-title">Il Tour più scelto è <span class="text-success">Colosseo</span> </h5>
                                <p class="card-text">Vi sono <strong>30</strong> Tour</p>
                                <a href="#" class="btn btn-outline-info">Gestisci Tour</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="toggle-sidebar.js"></script>
</body>
</html>
