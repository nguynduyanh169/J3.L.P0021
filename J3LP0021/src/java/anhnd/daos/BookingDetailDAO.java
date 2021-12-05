/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
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
public class BookingDetailDAO {

    public BookingDetailDAO() {
    }

    public int getQuantityOfBookedRoom(String roomId, Date startDate, Date endDate, int totalRoomQuantity) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = totalRoomQuantity;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select quantity from BookingDetail where hotelRoomId = ? and (startDate < ? or endDate > ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomId);
            preparedStatement.setDate(2, endDate);
            preparedStatement.setDate(3, startDate);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int quantity = resultSet.getInt("quantity");
                result = result - quantity;
                System.out.println("result of " + roomId + " " + result);
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

}
