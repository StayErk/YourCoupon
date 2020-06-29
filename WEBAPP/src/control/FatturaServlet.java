package control;

import model.carrello.CarrelloBean;
import model.carrello.CarrelloDAO;
import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;
import model.fattura.FatturaBean;
import model.fattura.FatturaDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/user/FatturaServlet")
public class FatturaServlet extends HttpServlet {
    private static final Integer VALOREPUNTO = 50;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        ClienteBean utente = (ClienteBean) session.getAttribute("user");
        FatturaDAO fatturaDAO = new FatturaDAO();
        if(action != null && action.equals("paga") && utente != null) {
            //Crea una nuova fattura sommando tutti costi dei pacchetti con il totale calcolato in base ai punti viaggio del cliente
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            try {
                CarrelloBean carrelloBean = carrelloDAO.retrieveByKey(utente.getEmail());
                String numeroPunti = request.getParameter("numeropunti");
                Double sconto = 0.0;
                if (numeroPunti.equals("15")) {
                    sconto = 5.0;
                    Integer nuoviPunti = utente.getPuntiViaggio() - 15;
                    utente.setPuntiViaggio(nuoviPunti);
                } else if (numeroPunti.equals("25")) {
                    sconto = 10.0;
                    Integer nuoviPunti = utente.getPuntiViaggio() - 25;
                    utente.setPuntiViaggio(nuoviPunti);
                } else if (numeroPunti.equals("35")) {
                    sconto = 15.0;
                    Integer nuoviPunti = utente.getPuntiViaggio() - 35;
                    utente.setPuntiViaggio(nuoviPunti);
                } else if (numeroPunti.equals("40")) {
                    sconto = 20.0;
                    Integer nuoviPunti = utente.getPuntiViaggio() - 40;
                    utente.setPuntiViaggio(nuoviPunti);
                } else if (numeroPunti.equals("0")) {
                    Integer nuoviPunti = utente.getPuntiViaggio() + Math.floorMod((int) carrelloBean.getTotale(), VALOREPUNTO);
                    utente.setPuntiViaggio(nuoviPunti);
                }
                Double totaleFattura = calcolaSconto(sconto, carrelloBean.getTotale());
                FatturaBean fatturaBean = new FatturaBean(UUID.randomUUID(), carrelloBean.getId(), totaleFattura, new Date());
                clienteDAO.doUpdate(utente);
                fatturaDAO.doSave(fatturaBean);
                PacchettoDAO pacchettoDAO = new PacchettoDAO();
                for (UUID id_pacchetto : carrelloDAO.vediCarrello(carrelloBean)) {
                    carrelloDAO.removePacchetto(carrelloBean, pacchettoDAO.retrieveByKey(id_pacchetto));
                }
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ordini.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                request.setAttribute("errore", true);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/chart.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        ClienteBean utente = (ClienteBean) session.getAttribute("user");
        FatturaDAO fatturaDAO = new FatturaDAO();
        if(action != null && (action.equals("retrievedati") || action.equals("paga"))  && utente != null) {
            switch (action) {
                case "retrievedati":
                    try {
                        List<FatturaBean> fatture = fatturaDAO.retrieveAll("", "");
                        request.setAttribute("fatture", fatture);
                        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ordini.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException throwables) {
                        request.setAttribute("errore", true);
                        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ordini.jsp");
                        dispatcher.forward(request, response);
                        throwables.printStackTrace();
                    }
                    break;
                case "paga":
                    doPost(request, response);
                    break;
            }
        } else {
            request.setAttribute("errore", true);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ordini.jsp");
            dispatcher.forward(request, response);
        }
    }

    private Double calcolaSconto(Double sconto, Double totale) {
        return totale - (totale * sconto / 100);
    }
}
