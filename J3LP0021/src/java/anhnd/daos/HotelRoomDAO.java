/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.HotelRoomDTO;
import anhnd.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class HotelRoomDAO {

    public HotelRoomDAO() {
    }

    public List<HotelRoomDTO> getHotelRooms(String hotelId, int selectQuantity) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HotelRoomDTO hotelRoomDTO = null;
        List<HotelRoomDTO> result = null;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select hotelRoomId, roomTypeId, description, quantity, currentPrice from HotelRoom where hotelId = ? and quantity >= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hotelId);
            preparedStatement.setInt(2, selectQuantity);
            resultSet = preparedStatement.executeQuery();
            result = new ArrayList<>();
            while (resultSet.next()) {
                String hotelRoomId = resultSet.getString("hotelRoomId");
                String roomTypeId = resultSet.getString("roomTypeId");
                float currentPrice = resultSet.getFloat("currentPrice");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                hotelRoomDTO = new HotelRoomDTO(hotelRoomId, hotelId, roomTypeId, description, quantity, currentPrice);
                result.add(hotelRoomDTO);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    public HotelRoomDTO getHotelRoomById(String hotelRoomId) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HotelRoomDTO hotelRoomDTO = null;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select hotelId, roomTypeId, description, quantity, currentPrice from HotelRoom where hotelRoomId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hotelRoomId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hotelId = resultSet.getString("hotelId");
                String roomTypeId = resultSet.getString("roomTypeId");
                float currentPrice = resultSet.getFloat("currentPrice");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");
                hotelRoomDTO = new HotelRoomDTO(hotelRoomId, hotelId, roomTypeId, description, quantity, currentPrice);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return hotelRoomDTO;
    }

}
