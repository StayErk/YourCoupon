package control;

import model.hotel.HotelDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebServlet("/PacchettiServlet")
public class PacchettiServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PacchettoDAO modelDAO = new PacchettoDAO();
        HotelDAO hotelDAO = new HotelDAO();
        String action = request.getParameter("action");
        System.out.println(action);
        switch (action){
            case "retrieve":
                try {
                    HashMap<UUID, ArrayList<String>> hashPacchetti = new HashMap<>();
                    ArrayList<String> info = new ArrayList<>();
                    ArrayList<PacchettoBean> pacchetti = new ArrayList<>(modelDAO.retrieveAll("",""));
                    for(PacchettoBean p: pacchetti){

                        info.add(p.isPredefinito() + "");
                        info.add(p.getCosto() + "");
                        info.add(p.getDurata() + "");
                        info.add(p.getPersone() + "");
                        info.add(hotelDAO.retrieveByKey((p.getId_struttura())).getNome());
                        info.add(hotelDAO.retrieveByKey((p.getId_struttura())).getCitta());
                        info.add(hotelDAO.retrieveByKey((p.getId_struttura())).getStelle() + "");
                        info.add(hotelDAO.retrieveByKey((p.getId_struttura())).getImmagine());

                        hashPacchetti.put(p.getId(),info);
                        info.clear();
                    }

                    request.setAttribute("pacchetti", hashPacchetti);
                    System.out.println(modelDAO.retrieveAll("", ""));
                } catch (SQLException e) {
                    request.setAttribute("errore", e.toString());
                }
                break;
        }

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);

    }
}
