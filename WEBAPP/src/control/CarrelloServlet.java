package control;

import model.carrello.CarrelloBean;
import model.carrello.CarrelloDAO;
import model.cliente.ClienteBean;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(name = "CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String azione = request.getParameter("action");
        String idpacchettoStr = request.getParameter("idpacchetto");
        UUID id_pacchetto = UUID.fromString(idpacchettoStr);
        if(session.getAttribute("user") != null) {
            ClienteBean cliente = (ClienteBean) session.getAttribute("user");
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            try {
                CarrelloBean carrello = (CarrelloBean) carrelloDAO.retrieveByKey(cliente.getEmail());
                PacchettoDAO pacchettoDAO = new PacchettoDAO();
                if(carrello != null && (azione != null && !azione.equals(""))) {
                    switch (azione) {
                        case "aggiungi":
                            PacchettoBean pacchettoDaAggiungere =  pacchettoDAO.retrieveByKey(id_pacchetto);
                            carrelloDAO.addPacchetto(carrello, pacchettoDaAggiungere);
                            break;
                        case "elimina":
                            PacchettoBean pacchettoDaRimuovere =  pacchettoDAO.retrieveByKey(id_pacchetto);
                            carrelloDAO.removePacchetto(carrello, pacchettoDaRimuovere);
                            break;
                        case "vedi":
                            ArrayList<PacchettoBean> contenutoCarr = new ArrayList<>();
                            break;
                        default:
                            break;
                    }
                }
            } catch (SQLException e) {

            }
        }
    }
}
