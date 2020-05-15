package model.cliente;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteTester {
    public static void main(String[] args) {
        ArrayList<ClienteBean> clienti;
        ClienteDAO clienteDAO = new ClienteDAO();

        System.out.println("Aggiungiamo 10 clienti");
        try {
            clienteDAO.doSave(new ClienteBean("nome1", "cognome1", 1, "email@unisa.it1", "pippo1", true, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome2", "cognome2", 2, "email@unisa.it2", "pippo2", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome3", "cognome3", 3, "email@unisa.it3", "pippo3", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome4", "cognome4", 4, "email@unisa.it4", "pippo4", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome5", "cognome5", 5, "email@unisa.it5", "pippo5", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome6", "cognome6", 6, "email@unisa.it6", "pippo6", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome7", "cognome7", 7, "email@unisa.it7", "pippo7", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome8", "cognome8", 8, "email@unisa.it8", "pippo8", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome9", "cognome9", 9, "email@unisa.it9", "pippo9", false, "https://bit.ly/3cpDfPV"));
            clienteDAO.doSave(new ClienteBean("nome10", "cognome10", 10, "email@unisa.it10", "pippo10", true, "https://bit.ly/3cpDfPV"));
        }
        catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doSave");
            e.printStackTrace();
        }

        System.out.println("\ndoUpdate");
        try {
            ClienteBean toUpdate = clienteDAO.retrieveAll("", "").get(0);
            System.out.println("Prima dell'update: " + toUpdate);
            toUpdate.setPuntiViaggio(toUpdate.getPuntiViaggio()+10);
            toUpdate.setPassword("pippo baudo");
            toUpdate.setNome("Antonio");
            toUpdate.setCognome("Fasulo");
            toUpdate.setAdmin(true);
            System.out.println("Dopo l'update: " + toUpdate);
        }
        catch (SQLException e) {
            System.out.println("SQL EXCEPTION su doUpdate");
            e.printStackTrace();
        }

        System.out.println("\nRetrieveAll vuoto");
        try {
            clienti = new ArrayList<>(clienteDAO.retrieveAll("",""));
            for(ClienteBean c : clienti)
                System.out.println(c);
        }
        catch (SQLException e) {
            System.out.println("SQL EXCEPTION su RetrieveAll");
            e.printStackTrace();
        }

        System.out.println("\nRetrieveAll con filtro (nome)");
        try {
            clienti = new ArrayList<>(clienteDAO.retrieveAll("nome",""));
            for(ClienteBean c : clienti)
                System.out.println(c);
        }
        catch (SQLException e) {
            System.out.println("SQL EXCEPTION su RetrieveAll");
            e.printStackTrace();
        }

        System.out.println("\nRetrieveAll con filtro (nome) e ordine (DESC)");
        try {
            clienti = new ArrayList<>(clienteDAO.retrieveAll("nome","DESC"));
            for(ClienteBean c : clienti)
                System.out.println(c);
        }
        catch (SQLException e) {
            System.out.println("SQL EXCEPTION su RetrieveAll");
            e.printStackTrace();
        }

        System.out.println("\nRetrieveKey");
        try {
            System.out.println(clienteDAO.retrieveByKey("email@unisa.it8"));
        }
        catch (SQLException e) {
            System.out.println("SQL EXCEPTION su RetrieveKey");
            e.printStackTrace();
        }

        /*System.out.println("\nCancelliamo tutti i clienti");
        try {
            while(clienteDAO.retrieveAll("", "").size() > 0){
                System.out.println(clienteDAO.retrieveAll("", "").size());
                clienteDAO.doDelete(clienteDAO.retrieveAll("","").get(0));
            }
        }
        catch (SQLException e) {
            System.out.println("SQL EXCEPTION su cancellazione");
            e.printStackTrace();
        }*/
    }
}
