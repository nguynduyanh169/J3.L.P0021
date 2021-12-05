/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.RoomTypeDTO;
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
public class RoomTypeDAO {

    public RoomTypeDAO() {
    }
    
    public List<RoomTypeDTO> getRoomTypes() throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RoomTypeDTO roomTypeDTO = null;
        List<RoomTypeDTO> result = null;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select roomTypeId, roomTypeName from RoomType";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            result = new ArrayList<>();
            while (resultSet.next()) {                
                String roomTypeId = resultSet.getString("roomTypeId");
                String roomTypeName = resultSet.getString("roomTypeName");
                roomTypeDTO = new RoomTypeDTO(roomTypeId, roomTypeName);
                result.add(roomTypeDTO);
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
    
    public RoomTypeDTO getRoomTypeById(String roomTypeId) throws NamingException, SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        RoomTypeDTO roomTypeDTO = null;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select roomTypeId, roomType from RoomType where roomTypeId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roomTypeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {                
                String roomTypeName = resultSet.getString("roomType");
                roomTypeDTO = new RoomTypeDTO(roomTypeId, roomTypeName);
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
        return roomTypeDTO;
    }
}
