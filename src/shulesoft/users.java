/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shulesoft;

import database.dbConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EduhG
 */
public class users {
    dbConnection dc = new dbConnection();
    
    public boolean Login(String username, String password) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT * FROM systemUsers WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
