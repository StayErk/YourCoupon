package model.tour;

import java.sql.SQLException;
import java.util.ArrayList;

public class LuogoTester {
    public static void main(String args[]) {
        ArrayList<LuogoBean> lista;
        LuogoDAO modelDAO = new LuogoDAO();

        System.out.println("RetriveAll vuoto");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("", ""));
            System.out.println("Lista luoghi dopo retrive: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retriveAll");
            e.printStackTrace();
        }

        System.out.println("RetriveAll con filter");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("nome", ""));
            System.out.println("Lista luoghi dopo retrive: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retriveAll");
            e.printStackTrace();
        }

        System.out.println("RetriveAll con filter e order");
        try{lista = new ArrayList<>(modelDAO.retrieveAll("nome", "DESC"));
            System.out.println("Lista luoghi dopo retrive: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retriveAll");
            e.printStackTrace();
        }
    }
}
