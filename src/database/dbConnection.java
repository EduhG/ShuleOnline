/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EduhG
 */
public class dbConnection {
    public String db_name = "schoolmanagement_version1";
    
    public String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public String DATABASE_URL = "jdbc:mysql://localhost/"+db_name;
    public String USERNAME = "root";
    public String PASSWORD = "mtotooh";
    
    public void createDataBase() {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection("jdbc:mysql://localhost/", USERNAME, PASSWORD);
            pstmt = con.prepareStatement("CREATE DATABASE " + db_name);
            
            pstmt.execute();
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
