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

        System.out.println("doSave");
        try {
            LuogoBean toInsert = new LuogoBean(UUID.fromString("c99f7bea-0b29-4344-be9b-4595a930a4a0"), "Luogo Test", "Via Test", "MSS", "vabbe balliamo", "https://bit.ly/2A8VdYT");
            System.out.println(modelDAO.retrieveAll("", "").size());
            modelDAO.doSave(toInsert);
            System.out.println(toInsert + "è stato inserito");
            System.out.println(modelDAO.retrieveAll("", "").size());
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doSave");
            e.printStackTrace();
        }

        System.out.println("doDelete");
        try {
            System.out.println("cancello il luogo con l'id: c99f7bea-0b29-4344-be9b-4595a930a4a0");
            System.out.println(modelDAO.retrieveAll("", "").size());
            modelDAO.doDelete((LuogoBean) modelDAO.retrieveAll("","").get(0));
            System.out.println("cancellato?");
            System.out.println(modelDAO.retrieveAll("", "").size());
            System.out.println("che culo funziona");
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doDelete");
            e.printStackTrace();
        }


    }
}
