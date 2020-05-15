package control;

import model.Bean;
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
        switch (action){
            case "retrieve":
                try {
                    HashMap<UUID, ArrayList<Bean>> hashPacchetti = new HashMap<>();
                    ArrayList<Bean> componenti = new ArrayList<>();
                    ArrayList<PacchettoBean> pacchetti = new ArrayList<>(modelDAO.retrieveAll("",""));
                    for(PacchettoBean p: pacchetti){

                        componenti.add(p);
                        componenti.add(hotelDAO.retrieveByKey(p.getId_struttura()));
                        hashPacchetti.put(p.getId(), (ArrayList<Bean>) componenti.clone());

                        System.out.println(componenti);
                        componenti.clear();
                    }

                    request.setAttribute("pacchetti", hashPacchetti);
                    System.out.println(hashPacchetti);
                } catch (SQLException e) {
                    request.setAttribute("errore", e.toString());
                }
                break;
        }

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);

    }
}
