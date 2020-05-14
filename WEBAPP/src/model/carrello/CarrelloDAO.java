package model.carrello;

import model.ComponentCRUD;

import java.sql.SQLException;
import java.util.List;

public class CarrelloDAO implements ComponentCRUD<CarrelloBean, String> {
    @Override
    public CarrelloBean retrieveByKey(String key) throws SQLException {
        return null;
    }

    @Override
    public List<CarrelloBean> retrieveAll(String filter, String order) throws SQLException {
        return null;
    }

    @Override
    public void doSave(CarrelloBean objectToSave) throws SQLException {

    }

    @Override
    public void doUpdate(CarrelloBean objectToUpdate) throws SQLException {

    }

    @Override
    public void doDelete(CarrelloBean objectToDelete) throws SQLException {

    }
}
