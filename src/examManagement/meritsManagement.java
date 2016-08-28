/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examManagement;

import database.dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EduhG
 */
public class meritsManagement {
    
    dbConnection dc = new dbConnection();
    ResultSet rsmem;
    int rscount;
    
    public String meritName;
    public String meritType;
    public String usedInEndTerm;

    public String getMeritName() {
        return meritName;
    }

    public void setMeritName(String meritName) {
        this.meritName = meritName;
    }

    public String getMeritType() {
        return meritType;
    }

    public void setMeritType(String meritType) {
        this.meritType = meritType;
    }

    public String getUsedInEndTerm() {
        return usedInEndTerm;
    }

    public void setUsedInEndTerm(String usedInEndTerm) {
        this.usedInEndTerm = usedInEndTerm;
    }
    
    public void addNewMerit(meritsManagement merit) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO merits(meritName, meritType, usedinEndTerm) "
                    + "VALUES(?,?,?)");

            pstmt.setString(1, merit.getMeritName());
            pstmt.setString(2, merit.getMeritType());
            pstmt.setString(3, merit.getUsedInEndTerm());

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
                Logger.getLogger(classesManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
