package model.hotel;

import model.ComponentCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class hotelDAO implements ComponentCRUD<HotelBean, UUID> {

    @Override
    public HotelBean retrieveByKey(UUID key) throws SQLException {
        return null;
    }

    @Override
    public List<HotelBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM StrutturaAlberghiera";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<HotelBean> hotels = new ArrayList<>();

        if(order != null && !order.equals("")){
            sql += " ORDER BY " + filter + " " + order;
        }
        return null;
    }

    @Override
    public void doSave(HotelBean objectToSave) throws SQLException {

    }

    @Override
    public void doUpdate(HotelBean objectToUpdate) throws SQLException {

    }

    @Override
    public void doDelete(HotelBean objectToDelete) throws SQLException {

    }
}
