/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.daos;

import anhnd.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author anhnd
 */
public class DiscountDAO {

    public DiscountDAO() {

    }

    public int checkDiscount(String discountId, String email) throws NamingException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Select discountPercent from Discount where expirationDate >= cast(sysdatetime() as date) and discountId = ? and discountFor = ? and status = 0";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, discountId);
            preparedStatement.setString(2, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("discountPercent");
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

    public boolean usedDiscount(String discountId) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean check = false;
        try {
            connection = DBUtils.makeConnection();
            String sql = "Update Discount set status = -1 where discountId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, discountId);
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
