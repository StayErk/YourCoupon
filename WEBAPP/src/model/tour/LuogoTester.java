package model.tour;

import java.util.ArrayList;

public class LuogoTester {
    public static void main (String args[]) {
        ArrayList<LuogoBean> lista;
        LuogoDAO modelDAO = new LuogoDAO();

        System.out.println("RetriveAll vuoto");
        try {
            lista = new ArrayList<>(modelDAO.retrieveAll("",""));
        }
    }
}
