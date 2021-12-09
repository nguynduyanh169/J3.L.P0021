/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.BookingDetailDTO;
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
            String sql = "Select quantity from BookingDetail where hotelRoomId = ? and ((startDate between ? and ?) or (endDate between ? and ?) or (? between  startDate and endDate)) and status = 0";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomId);
            preparedStatement.setDate(2, startDate);
            preparedStatement.setDate(3, endDate);
            preparedStatement.setDate(4, startDate);
            preparedStatement.setDate(5, endDate);
            preparedStatement.setDate(6, startDate);
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

    public boolean disableBookingDetailsByBookingId(String bookingId) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Update BookingDetail set status = -1 where bookingId = ?";
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

    public List<BookingDetailDTO> getBookingDetailByBookingId(String bookingId) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BookingDetailDTO bookingDetailDTO = null;
        List<BookingDetailDTO> result = null;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select bookingDetailId, bookingId, hotelRoomId, price, quantity, startDate, endDate, status from BookingDetail where bookingId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookingId);
            resultSet = preparedStatement.executeQuery();
            
            result = new ArrayList<>();
            while (resultSet.next()) {                
                String bookingDetailId = resultSet.getString("bookingDetailId");
                String hotelRoomId = resultSet.getString("hotelRoomId");
                float price = resultSet.getFloat("price");
                int quantity = resultSet.getInt("quantity");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                int status = resultSet.getInt("status");
                bookingDetailDTO = new BookingDetailDTO(bookingDetailId, bookingId, hotelRoomId, price, quantity, startDate, endDate, status);
                result.add(bookingDetailDTO);
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

    public boolean insertBookingDetail(BookingDetailDTO bookingDetailDTO) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Insert into BookingDetail(bookingDetailId, bookingId, hotelRoomId, price, quantity, startDate, endDate, status) values(?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookingDetailDTO.getBookingDetailId());
            preparedStatement.setString(2, bookingDetailDTO.getBookingId());
            preparedStatement.setString(3, bookingDetailDTO.getHotelRoomId());
            preparedStatement.setFloat(4, bookingDetailDTO.getPrice());
            preparedStatement.setInt(5, bookingDetailDTO.getQuantity());
            preparedStatement.setDate(6, bookingDetailDTO.getStartDate());
            preparedStatement.setDate(7, bookingDetailDTO.getEndDate());
            preparedStatement.setInt(8, bookingDetailDTO.getStatus());
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
