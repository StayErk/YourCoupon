package model.hotel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class HotelTester {
    public static void main (String args[]) {
        ArrayList<HotelBean> lista;
        hotelDAO modelDAO = new hotelDAO();

        System.out.println("RetrieveALL vuoto");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("", ""));
            System.out.println("Lista hotel dopo retrieve: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retrieveAll");
            e.printStackTrace();
        }

        System.out.println("RetrieveALL vuoto con filter");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("nome", ""));
            System.out.println("Lista hotel dopo retrieve: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retrieveAll");
            e.printStackTrace();
        }

        System.out.println("RetrieveALL vuoto con filter e order");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("nome", "DESC"));
            System.out.println("Lista hotel dopo retrieve: " + lista);
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retrieveAll");
            e.printStackTrace();
        }

        System.out.println("RetrivebyKEY");
        try {
            System.out.println("Risultato Query: " + modelDAO.retrieveByKey(UUID.fromString("d9d2500f-5312-45a9-ad66-f4553685043c")));
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su retrieveByKey");
            e.printStackTrace();
        }

        System.out.println("doSave");
        try {
            HotelBean toInsert = new HotelBean(UUID.randomUUID(), "Hotel Test", "Via Test 1", "Roma", 249.9, 4, "https://bit.ly/2A8VdYT", "antoniofasulobbc@make69.com", "012345678");
            System.out.println(modelDAO.retrieveAll("", "").size());
            modelDAO.doSave(toInsert);
            System.out.println(toInsert + " è stato inserito correttamente? ");
            System.out.println(modelDAO.retrieveAll("", "").size());
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doSave");
            e.printStackTrace();
        }

        System.out.println("doDelete");
        try {
            System.out.println("Deleting Hotel with id: 89a6c61b-205e-4f7b-a962-d126ae97ec30"  );
            System.out.println(modelDAO.retrieveAll("", "").size());
            modelDAO.doDelete((HotelBean) modelDAO.retrieveAll("", "").get(0));
            System.out.println("Cancellato correttamente? ");
            System.out.println(modelDAO.retrieveAll("", "").size());


        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doDelete");
            e.printStackTrace();
        }

        System.out.println("doUpdate");
        try {
            HotelBean toUpdate = modelDAO.retrieveAll("", "").get(0);
            System.out.println("Prima dell update " + toUpdate);
            toUpdate.setNome("Nome cambiato");
            toUpdate.setEmail("emailcambiata@mail.com");
            modelDAO.doUpdate(toUpdate);
            System.out.println("dopo update " + modelDAO.retrieveByKey(toUpdate.getId()));
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doUpdate");
            e.printStackTrace();
        }
    }
}
