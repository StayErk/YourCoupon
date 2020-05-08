package model.tour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class TourTester {
    public static void main(String args[]) {
        ArrayList<TourBean> lista;
        TourDAO modelDAO = new TourDAO();

        System.out.println("RetriveAll vuoto");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("", ""));
            System.out.println("Lista tour dopo retrive: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retriveAll");
            e.printStackTrace();
        }

        System.out.println("RetriveAll con filter");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("costo", ""));
            System.out.println("Lista tour dopo retrive: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retriveAll");
            e.printStackTrace();
        }

        System.out.println("RetriveAll con filter e order");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("costo", "DESC"));
            System.out.println("Lista tour dopo retrive: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retriveAll");
            e.printStackTrace();
        }

        System.out.println("retrivebykey");
        try {
            System.out.println("risultato query: " + modelDAO.retrieveByKey(UUID.fromString("174ec1ea-4448-4842-a8a1-d46c97079ad4")));
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retrivebykey");
            e.printStackTrace();
        }

       System.out.println("doSave");
        try {
            TourBean toInsert = new TourBean(UUID.randomUUID(), UUID.fromString("239c2175-308b-4498-8fda-60eb1a93a78f"), 23.5, 6);
            System.out.println(modelDAO.retrieveAll("", "").size());
            modelDAO.doSave(toInsert);
            System.out.println(toInsert + "è stato inserito");
            System.out.println(modelDAO.retrieveAll("", "").size());
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doSave");
            e.printStackTrace();
        }

       /* System.out.println("doDelete");
        try {
            System.out.println("cancello il luogo con l'id: c99f7bea-0b29-4344-be9b-4595a930a4a0");
            System.out.println(modelDAO.retrieveAll("", "").size());
            modelDAO.doDelete((LuogoBean) modelDAO.retrieveAll("", "").get(0));
            System.out.println("cancellato?");
            System.out.println(modelDAO.retrieveAll("", "").size());
            System.out.println("che culo funziona");
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doDelete");
            e.printStackTrace();
        }

        System.out.println("doUpdate");
        try {
            LuogoBean toUpdate = modelDAO.retrieveAll("", "").get(0);
            System.out.println("prima dell'update" + toUpdate);
            toUpdate.setNome("NomeCambiato");
            toUpdate.setCitta("CittaNuova");
            modelDAO.doUpdate(toUpdate);
            System.out.println("dopo l'update" + modelDAO.retrieveByKey(toUpdate.getId()));
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doUpdate");
            e.printStackTrace();
        }*/
    }
}

