/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.BookingDTO;
import anhnd.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class BookingDAO {

    public BookingDAO() {
    }

    public boolean insertBookingDetail(BookingDTO bookingDTO) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Insert into Booking(bookingId, bookingBy, startDate, endDate, createDate, discount,realTotalPrice, totalPrice, status) values(?,?,?,?,GETDATE(),?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookingDTO.getBookingId());
            preparedStatement.setString(2, bookingDTO.getBookingBy());
            preparedStatement.setDate(3, bookingDTO.getStartDate());
            preparedStatement.setDate(4, bookingDTO.getEndDate());
            preparedStatement.setInt(5, bookingDTO.getDiscount());
            preparedStatement.setFloat(6, bookingDTO.getRealTotalPrice());
            preparedStatement.setFloat(7, bookingDTO.getTotalPrice());
            preparedStatement.setInt(8, bookingDTO.getStatus());
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                check = true;
            }
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return check;
    }
    
}
