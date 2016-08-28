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
public class classesManagement {
    dbConnection dc = new dbConnection();
    ResultSet rsmem;
    int rscount;
    
    public String classCode;
    public String className;
    public String level;
    
    public String streamCode;
    public String streamName;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStreamCode() {
        return streamCode;
    }

    public void setStreamCode(String streamCode) {
        this.streamCode = streamCode;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }
    
    public void addNewClass(classesManagement classes) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO classes(classCode, className, level) "
                    + "VALUES(?,?,?)");

            pstmt.setString(1, classes.getClassCode());
            pstmt.setString(2, classes.getClassName());
            pstmt.setString(3, classes.getLevel());

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
    
    public void addNewStream(classesManagement stream) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO streams(streamCode, streamName) "
                    + "VALUES(?,?)");

            pstmt.setString(1, stream.getStreamCode());
            pstmt.setString(2, stream.getStreamName());

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
