/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.dtos.AreaDTO;
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
public class AreaDAO {

    public AreaDAO() {
    }
    
    public List<AreaDTO> getAllArea() throws SQLException, NamingException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AreaDTO areaDTO = null;
        List<AreaDTO> result = null;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select areaId, areaName from Area";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            result = new ArrayList<>();
            while (resultSet.next()) {                
                String areaId = resultSet.getString("areaId");
                String areaName = resultSet.getString("areaName");
                areaDTO = new AreaDTO(areaId, areaName);
                result.add(areaDTO);
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
