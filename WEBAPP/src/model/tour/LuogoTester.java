package model.tour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

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
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("nome", "DESC"));
            System.out.println("Lista luoghi dopo retrive: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retriveAll");
            e.printStackTrace();
        }

        System.out.println("retrivebykey");
         try {
            System.out.println("risultato query: " + modelDAO.retrieveByKey(UUID.fromString("9089b21c-6e76-419f-8059-aa3fdc92f9ba")));
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retrivebykey");
            e.printStackTrace();
        }
    }
}
