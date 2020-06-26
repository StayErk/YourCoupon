package control;

import com.google.gson.Gson;
import model.carrello.CarrelloBean;
import model.carrello.CarrelloDAO;
import model.carrello.ItemBean;
import model.cliente.ClienteBean;
import model.hotel.HotelDAO;
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
import java.util.HashMap;
import java.util.UUID;

@WebServlet("/user/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String azione = request.getParameter("action");
        if(session.getAttribute("user") != null && azione != null) {
            ClienteBean cliente = (ClienteBean) session.getAttribute("user");
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            try {
                CarrelloBean carrello = (CarrelloBean) carrelloDAO.retrieveByKey(cliente.getEmail());
                PacchettoDAO pacchettoDAO = new PacchettoDAO();
                if(carrello != null && (azione != null && !azione.equals(""))) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    switch (azione) {
                        case "aggiungi":
                            String idpacchettoStr = request.getParameter("idpacchetto");
                            if(idpacchettoStr != null) {
                                UUID id_pacchetto = UUID.fromString(idpacchettoStr);
                                PacchettoBean pacchettoDaAggiungere = pacchettoDAO.retrieveByKey(id_pacchetto);
                                carrelloDAO.addPacchetto(carrello, pacchettoDaAggiungere);
                                response.setStatus(200);
                            }
                            break;
                        case "elimina":
                            idpacchettoStr = request.getParameter("idpacchetto");
                            if(idpacchettoStr != null) {
                                UUID id_pacchetto = UUID.fromString(idpacchettoStr);
                                PacchettoBean pacchettoDaRimuovere = pacchettoDAO.retrieveByKey(id_pacchetto);
                                carrelloDAO.removePacchetto(carrello, pacchettoDaRimuovere);
                                response.setStatus(200);
                            }
                            response.setStatus(401);
                            break;
                        case "vedi":
                            ArrayList<ItemBean> contenutoCarr = new ArrayList<>();
                            HotelDAO hotelDAO = new HotelDAO();
                            for (UUID id : carrelloDAO.vediCarrello(carrello)){
                                ItemBean item = new ItemBean();
                                item.setPacchetto(pacchettoDAO.retrieveByKey(id));
                                item.setCittaDestinazione(hotelDAO.retrieveByKey(item.getPacchetto().getId_struttura()).getCitta());
                                contenutoCarr.add(item);
                            }
                            Gson gson = new Gson();
                            String carrelloDaRit = gson.toJson(contenutoCarr);
                            response.setStatus(200);
                            response.getWriter().write(carrelloDaRit);
                            break;
                        default:
                            break;
                    }
                }
            } catch (SQLException e) {
                request.setAttribute("error", e.toString());
                System.out.println("Errore visualizzazione carrello");
                e.printStackTrace();

            }
        }
    }
}
