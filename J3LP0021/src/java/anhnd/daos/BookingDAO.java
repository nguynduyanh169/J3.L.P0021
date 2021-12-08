/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.BookingDTO;
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
public class BookingDAO {

    public BookingDAO() {
    }

    public boolean insertBookingDetail(BookingDTO bookingDTO) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Insert into Booking(bookingId, bookingBy, startDate, endDate, createDate, discount,realTotalPrice, totalPrice, status, hotelName) values(?,?,?,?,GETDATE(),?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookingDTO.getBookingId());
            preparedStatement.setString(2, bookingDTO.getBookingBy());
            preparedStatement.setDate(3, bookingDTO.getStartDate());
            preparedStatement.setDate(4, bookingDTO.getEndDate());
            preparedStatement.setInt(5, bookingDTO.getDiscount());
            preparedStatement.setFloat(6, bookingDTO.getRealTotalPrice());
            preparedStatement.setFloat(7, bookingDTO.getTotalPrice());
            preparedStatement.setInt(8, bookingDTO.getStatus());
            preparedStatement.setString(9, bookingDTO.getHotelName());
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

    public List<BookingDTO> getBookings(String email, String searchHotelName, Date bookingDate) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BookingDTO bookingDTO = null;
        List<BookingDTO> result = null;
        try {
            connection = DBUtils.makeConnection();
            if (bookingDate == null) {
                String sql = "Select bookingId, hotelName, startDate, endDate, createDate, discount, realTotalPrice, totalPrice, status from Booking where bookingBy = ? and status = 0 order by createDate desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
            } else {
                System.out.println(bookingDate);
                String sql = "Select bookingId, hotelName, startDate, endDate, createDate, discount, realTotalPrice, totalPrice, status from Booking where bookingBy = ? and cast(createDate as date) = ? and status = 0 and hotelName like '%" + searchHotelName + "%' order by createDate desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setDate(2, bookingDate);
            }
            resultSet = preparedStatement.executeQuery();
            result = new ArrayList<>();
            while (resultSet.next()) {
                String bookingId = resultSet.getString("bookingId");
                String hotelName = resultSet.getString("hotelName");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                Date createDate = resultSet.getDate("createDate");
                int discount = resultSet.getInt("discount");
                float realTotalPrice = resultSet.getFloat("realTotalPrice");
                float totalPrice = resultSet.getFloat("totalPrice");
                int status = resultSet.getInt("status");
                bookingDTO = new BookingDTO(bookingId, hotelName, email, startDate, endDate, createDate, discount, realTotalPrice, totalPrice, status);
                result.add(bookingDTO);
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

    public boolean cancelBooking(String bookingId) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Update Booking set status = -1 where bookingId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookingId);
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
