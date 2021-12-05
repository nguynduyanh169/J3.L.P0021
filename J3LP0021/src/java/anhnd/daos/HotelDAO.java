/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.HotelDTO;
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
public class HotelDAO {

    public HotelDAO() {
    }

    public List<HotelDTO> getHotels(String search, String searchArea) throws SQLException, NamingException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HotelDTO dto = null;
        List<HotelDTO> result = null;
        try {
            connection = DBUtils.makeConnection();
            if (!searchArea.isEmpty()) {
                String sql = "Select hotelId, hotelName, address, area, status, phone from Hotel where hotelName LIKE N'%" + search + "%' and status = 0 and area = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, searchArea);
            } else {
                String sql = "Select hotelId, hotelName, address, area, status, phone from Hotel where hotelName LIKE N'%" + search + "%' and status = 0";
                preparedStatement = connection.prepareStatement(sql);
            }
            resultSet = preparedStatement.executeQuery();
            result = new ArrayList<>();
            while (resultSet.next()) {
                String hotelId = resultSet.getString("hotelId");
                String hotelName = resultSet.getString("hotelName");
                String address = resultSet.getString("address");
                String area = resultSet.getString("area");
                String phone = resultSet.getString("phone");
                int status = resultSet.getInt("status");
                dto = new HotelDTO(hotelId, hotelName, phone, address, area, status);
                result.add(dto);
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

    public HotelDTO GetHotelById(String hotelId) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HotelDTO dto = null;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select hotelId, hotelName, address, area, status, phone from Hotel where hotelId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, hotelId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String hotelName = resultSet.getString("hotelName");
                String address = resultSet.getString("address");
                String area = resultSet.getString("area");
                String phone = resultSet.getString("phone");
                int status = resultSet.getInt("status");
                dto = new HotelDTO(hotelId, hotelName, phone, address, area, status);
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
        return dto;
    }
}
