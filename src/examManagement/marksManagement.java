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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import studentManagement.student;

/**
 *
 * @author EduhG
 */
public class marksManagement {
    dbConnection dc = new dbConnection();
    
    public String admissionNo, fullName, form, subject, merit;
    public int score, position;
    
    public boolean addStudentMarks(marksManagement marks) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO subjectMarks(admissionNo, fullName, form, subject, merit, score, position) "
                    + "VALUES(?,?,?,?,?,?,?)");

            pstmt.setString(1, marks.admissionNo);
            pstmt.setString(2, marks.fullName);
            pstmt.setString(3, marks.form);
            pstmt.setString(4, marks.subject);
            pstmt.setString(5, marks.merit);
            pstmt.setInt(6, marks.score);
            pstmt.setInt(7, marks.position);

            pstmt.execute();
            
            return true;

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
                Logger.getLogger(marksManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }
    
    public boolean updateStudentMarks(String admNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            
            pstmt = con.prepareStatement("UPDATE subjectMarks SET admissionNo=?, fullName=?, "
                    + "score=? WHERE admissionNo = ? AND form = ? AND merit = ? AND subject = ?");

            pstmt.setString(1, admNo);
            pstmt.setString(2, fullName);
            pstmt.setInt(3, score);
            
            pstmt.setString(4, admNo);
            pstmt.setString(5, form);
            pstmt.setString(6, merit);
            pstmt.setString(7, subject);

            pstmt.execute();
            
            return true;

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
                Logger.getLogger(marksManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }
}
