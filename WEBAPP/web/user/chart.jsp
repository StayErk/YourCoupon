<%--
  Created by IntelliJ IDEA.
  User: andreaerk
  Date: 5/20/20
  Time: 9:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Il Mio Carrello</title>
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
    <!-- Navbar -->
    <%@include file="../components/navbar/_navbar.jsp"%>
    <!-- Una colonna dove saranno presenti propic, miei ordini e link al carrello -->
    <form class="hide d-none">
        <input id="sessionid" type="hidden" name="" value="<%=request.getSession().getId()%>">
    </form>
    <div class="container-fluid">
        <div class="row">
            <%@include file="_profilesidebar.jsp"%>
            <div class="col-12 col-md-9 p-5 info-user">
                <div class="row">
                    <div class="col-12">
                        <h1 class="display-4 text-success"><span class="small text-secondary">Il Carrello</span></h1>
                    </div>
                    <div class="col-12 mt-3">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Città di destinazione</th>
                                    <th>Durata</th>
                                    <th>Numero persone</th>
                                    <th>Totale pacchetto</th>
                                </tr>
                            </thead>
                            <tbody id="data">
                            </tbody>
                        </table>
                    </div>
                    <div class="col-12 text-left">
                        <button type="button"  id="procedialpagamento" class=" d-none btn btn-success" data-toggle="modal" data-target="#pagamento">Procedi Al Pagamento</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="pagamento" tabindex="-1" role="dialog" aria-labelledby="pagamentoLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="pagamentoLabel">Procedi al pagamento</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                     <span class="form-text text-center border border-success p-3 rounded m-3">
                         Totale: <span id="totalecarrello" class="text-success"></span>
                     </span>
                    <form action="FatturaServlet" method="post"  id="datipagamento">
                        <input type="hidden" name="action" value="paga">
                        <div class="form-group">
                            <label for="numerocarta">Inserisci numero carta</label>
                            <input type="text" name="numerocarta" class="form-control" id="numerocarta">
                            <span id="errore" class="d-none form-text text-danger text-center border border-danger p-3 rounded m-3">
                                Numero carta errato (1234-1234-1234-1234)
                            </span>
                        </div>
                        <span class="form-text text-primary text-center border border-primary p-3 rounded m-3">
                            Hai a disposizione: <%=bean.getPuntiViaggio()%> punti viaggio!
                        </span>
                        <%if (bean.getPuntiViaggio() >= 15 && bean.getPuntiViaggio() < 25) {%>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="zeropunti" value="0" checked>
                            <label class="form-check-label" for="zeropunti">
                                Non Usare Punti viaggio  <span class="text-success">Guadagni un punto viaggio ogni 50€ spesi</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="quindicipunti" value="15">
                            <label class="form-check-label" for="quindicipunti">
                                Usa 15 punti viaggio <span class="text-success">5% sconto</span>
                            </label>
                        </div>
                        <%} else if(bean.getPuntiViaggio() >= 25 && bean.getPuntiViaggio() < 35){%>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="zeropunti" value="0" checked>
                            <label class="form-check-label" for="zeropunti">
                                Non Usare Punti viaggio  <span class="text-success">Guadagni un punto viaggio ogni 50€ spesi</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="quindicipunti" value="15">
                            <label class="form-check-label" for="quindicipunti">
                                Usa 15 punti viaggio <span class="text-success">5% sconto</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="venticinquepunti" value="25">
                            <label class="form-check-label" for="venticinquepunti">
                                Usa 25 punti viaggio <span class="text-success">10% sconto</span>
                            </label>
                        </div>
                        <%} else if(bean.getPuntiViaggio() >= 35 && bean.getPuntiViaggio() < 40){%>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="zeropunti" value="0" checked>
                            <label class="form-check-label" for="zeropunti">
                                Non Usare Punti viaggio, <span class="text-success">Guadagni un punto viaggio ogni 50€ spesi</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="quindicipunti" value="15">
                            <label class="form-check-label" for="quindicipunti">
                                Usa 15 punti viaggio <span class="text-success">5% sconto</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="venticinquepunti" value="25">
                            <label class="form-check-label" for="venticinquepunti">
                                Usa 25 punti viaggio <span class="text-success">10% sconto</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="trentacinquepunti" value="35">
                            <label class="form-check-label" for="trentacinquepunti">
                                Usa 35 punti viaggio <span class="text-success">15% sconto</span>
                            </label>
                        </div>
                        <%} else if(bean.getPuntiViaggio() >= 40){%>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="zeropunti" value="0" checked>
                            <label class="form-check-label" for="zeropunti">
                                Non Usare Punti viaggio  <span class="text-success">Guadagni un punto viaggio ogni 50€ spesi</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="quindicipunti" value="15">
                            <label class="form-check-label" for="quindicipunti">
                                Usa 15 punti viaggio <span class="text-success">5% sconto</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="venticinquepunti" value="25">
                            <label class="form-check-label" for="venticinquepunti">
                                Usa 25 punti viaggio <span class="text-success">10% sconto</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="trentacinquepunti" value="35">
                            <label class="form-check-label" for="trentacinquepunti">
                                Usa 35 punti viaggio <span class="text-success">15% sconto</span>
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="numeropunti" id="quarantapunti" value="40">
                            <label class="form-check-label" for="quarantapunti">
                                Usa 40 punti viaggio <span class="text-success">20% sconto</span>
                            </label>
                        </div>
                        <%} else if(bean.getPuntiViaggio() >= 0 && bean.getPuntiViaggio() < 15){%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="numeropunti" id="zeropunti" value="0" checked>
                                <label class="form-check-label" for="zeropunti">
                                    Non Usare Punti viaggio  <span class="text-success">Guadagni un punto viaggio ogni 50€ spesi</span>
                                </label>
                            </div>
                        <%}%>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                    <button class="btn btn-success" id="btnpagamento">Paga</button>
                    <script src="../components/script/actions.js"></script>
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
    <script src="../components/script/chart.js"></script>
    <script src="../components/script/actions.js"></script>
</body>
</html>
