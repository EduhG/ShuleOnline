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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EduhG
 */
public class subjectsManagement {
    dbConnection dc = new dbConnection();
//    subjectsManagement subject = new subjectsManagement();
    ResultSet rsmem;
    int rscount;
    
    public String subjectCode;
    public String subjectName;
    public String cartegory;
    public String subcartegory;
    public int totalMarks;

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCartegory() {
        return cartegory;
    }

    public void setCartegory(String cartegory) {
        this.cartegory = cartegory;
    }

    public String getSubcartegory() {
        return subcartegory;
    }

    public void setSubcartegory(String subcartegory) {
        this.subcartegory = subcartegory;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
    
    
    public void addNewSubject(subjectsManagement subject) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO subjects(subjectCode, subjectName, "
                    + "cartegory, subcartegory, totalMarks) "
                    + "VALUES(?,?,?,?,?)");

            pstmt.setString(1, subject.getSubjectCode());
            pstmt.setString(2, subject.getSubjectName());
            pstmt.setString(3, subject.getCartegory());
            pstmt.setString(4, subject.getSubcartegory());
            pstmt.setInt(5, subject.getTotalMarks());

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
                Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void SubjectDetails() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT COUNT(*) FROM subjects");
            
            rs = pstmt.executeQuery();
            rs.first();
            rscount=rs.getInt(1);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sqlQuery = "SELECT * FROM subjects";
            
            rsmem = stmt.executeQuery(sqlQuery);
            
            if (rsmem.last()) {
                Display();
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
                Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void Display() {
        try {
            setSubjectCode(rsmem.getString("subjectCode"));
            setSubjectName(rsmem.getString("subjectName"));
            setCartegory(rsmem.getString("cartegory"));
            setSubcartegory(rsmem.getString("subcartegory"));
            setTotalMarks(rsmem.getInt("totalMarks"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
