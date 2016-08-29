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
import javax.swing.table.DefaultTableModel;
import studentManagement.student;
import studentManagement.studentManagement;

/**
 *
 * @author EduhG
 */
public class marksManagement {
    dbConnection dc = new dbConnection();
    
    public String admissionNo = "", fullName = "", form = "", subject = "", merit = "", term = "", year = "";
    public int score, position;
    
    public boolean addStudentMarks(marksManagement marks) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO subjectMarks(admissionNo, "
                    + "fullName, form, subject, merit, term, year, score, position) "
                    + "VALUES(?,?,?,?,?,?,?)");

            pstmt.setString(1, marks.admissionNo);
            pstmt.setString(2, marks.fullName);
            pstmt.setString(3, marks.form);
            pstmt.setString(4, marks.subject);
            pstmt.setString(5, marks.merit);
            pstmt.setString(6, marks.term);
            pstmt.setString(7, marks.year);
            pstmt.setInt(8, marks.score);
            pstmt.setInt(9, marks.position);

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
            
            pstmt = con.prepareStatement("UPDATE subjectMarks SET admissionNo=?, fullName=?, score=? WHERE "
                    + "admissionNo = ? AND form = ? AND merit = ? AND subject = ? AND term = ? AND year = ?");

            pstmt.setString(1, admNo);
            pstmt.setString(2, fullName);
            pstmt.setInt(3, score);
            
            pstmt.setString(4, admNo);
            pstmt.setString(5, form);
            pstmt.setString(6, merit);
            pstmt.setString(7, subject);
            pstmt.setString(8, term);
            pstmt.setString(9, year);

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
    
    public boolean studentsSubjectRanking(String admNo){
        Connection con = null, con1 = null;
        PreparedStatement pstmt = null, pstmt1 = null;
        
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT score, FIND_IN_SET( score, (\n" +
                "SELECT GROUP_CONCAT( score\n" +
                "ORDER BY score DESC ) \n" +
                "FROM schoolmanagement_version1.subjectMarks WHERE subject = ? AND form = ? AND merit = ? AND term = ? AND year = ?)\n" +
                ") AS Rank\n" +
                "FROM schoolmanagement_version1.subjectMarks");
            
            pstmt.setString(1, subject);
            pstmt.setString(2, form);
            pstmt.setString(3, merit);
            pstmt.setString(4, term);
            pstmt.setString(5, year);
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {  
                System.out.println(rs.getString("Rank"));
                try{
                    Class.forName(dc.JDBC_DRIVER);
                    con1 = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
                    pstmt1 = con1.prepareStatement("UPDATE subjectMarks SET position=? WHERE "
                    + "admissionNo = ? AND form = ? AND merit = ? AND subject = ? AND term = ? AND year = ?");

                    pstmt1.setString(1, rs.getString("Rank"));

                    pstmt1.setString(2, admNo);
                    pstmt1.setString(3, form);
                    pstmt1.setString(4, merit);
                    pstmt1.setString(5, subject);
                    pstmt1.setString(6, term);
                    pstmt1.setString(7, year);

                    pstmt1.execute();
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (pstmt1 != null) {
                            pstmt1.close();
                        }
                        if (con1 != null) {
                            con1.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            return true;
            
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
               
        return false;
    }
    
    public boolean initializeStudentMarks(String admNo) {
        Connection con = null, con1 = null;
        PreparedStatement pstmt = null, pstmt1 = null;
        
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT * FROM subjects");
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {  
                try{
                    Class.forName(dc.JDBC_DRIVER);
                    con1 = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
                    pstmt1 = con1.prepareStatement("INSERT INTO subjectMarks(admissionNo, "
                            + "fullName, form, subject, merit, term, year) "
                            + "VALUES(?,?,?,?,?,?,?)");

                    pstmt1.setString(1, admissionNo);
                    pstmt1.setString(2, fullName);
                    pstmt1.setString(3, form);
                    pstmt1.setString(4, rs.getString("subjectName"));
                    pstmt1.setString(5, merit);
                    pstmt1.setString(6, term);
                    pstmt1.setString(7, year);

                    pstmt1.execute();
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (pstmt1 != null) {
                            pstmt1.close();
                        }
                        if (con1 != null) {
                            con1.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            return true;
            
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
               
        return false;
    }
    
    public boolean initTotalMarks() {
        Connection con = null, con1 = null;
        PreparedStatement pstmt = null, pstmt1 = null;
        
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT * FROM subjectMarks "
                    + "WHERE form = ? AND merit = ? AND term = ? AND year = ?");
            
            pstmt.setString(1, form);
            pstmt.setString(2, merit);
            pstmt.setString(3, term);
            pstmt.setString(4, year);
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                System.out.println(rs.getString("admissionNo"));
                if (populateTotalMarks(rs.getString("admissionNo")))
                    if (studentsTotalMarksRanking(rs.getString("admissionNo")))
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
                Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean populateTotalMarks(String admNo) {
        Connection con = null, con1 = null, con2 = null;
        PreparedStatement pstmt = null, pstmt1 = null, pstmt2 = null;
        
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT fullName, SUM(score) AS totalMarks FROM subjectMarks "
                    + "WHERE admissionNo = ? AND form = ? AND merit = ? AND term = ? AND year = ?");
            
            pstmt.setString(1, admNo);
            pstmt.setString(2, form);
            pstmt.setString(3, merit);
            pstmt.setString(4, term);
            pstmt.setString(5, year);
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                fullName = rs.getString("fullName");
                try {
                    Class.forName(dc.JDBC_DRIVER);
                    con2 = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
                    pstmt2 = con2.prepareStatement("SELECT * FROM totalMarks WHERE "
                            + "form = ? AND merit = ? AND term = ? AND year = ? AND admissionNo = ?");

                    pstmt2.setString(1, form);
                    pstmt2.setString(2, merit);
                    pstmt2.setString(3, term);
                    pstmt2.setString(4, year);
                    pstmt2.setString(5, admNo);

                    rs = pstmt.executeQuery();

                    if (rs.next()) {
                        try{
                            Class.forName(dc.JDBC_DRIVER);
                            con1 = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
                            pstmt1 = con1.prepareStatement("UPDATE totalMarks SET fullName=?, totalMarks=? WHERE "
                                + "admissionNo = ? AND form = ? AND merit = ? AND term = ? AND year = ?");

                            pstmt1.setString(1, fullName);
                            pstmt1.setInt(2, Integer.parseInt(rs.getString("totalMarks")));
                            pstmt1.setString(3, admNo);
                            pstmt1.setString(4, form);
                            pstmt1.setString(4, merit);
                            pstmt1.setString(5, term);
                            pstmt1.setString(6, year);

                            pstmt1.execute();

                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                            return false;
                        } finally {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                if (pstmt1 != null) {
                                    pstmt1.close();
                                }
                                if (con1 != null) {
                                    con1.close();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                    } else {
                        try{
                            Class.forName(dc.JDBC_DRIVER);
                            con1 = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
                            pstmt1 = con1.prepareStatement("INSERT INTO totalMarks(admissionNo, "
                                    + "fullName, form, merit, term, year, totalMarks) "
                                    + "VALUES(?,?,?,?,?,?,?)");

                            pstmt1.setString(1, admNo);
                            pstmt1.setString(2, fullName);
                            pstmt1.setString(3, form);
                            pstmt1.setString(4, merit);
                            pstmt1.setString(5, term);
                            pstmt1.setString(6, year);
                            pstmt1.setInt(7, Integer.parseInt(rs.getString("totalMarks")));

                            pstmt1.execute();

                        } catch (SQLException | ClassNotFoundException ex) {
                            ex.printStackTrace();
                            return false;
                        } finally {
                            try {
                                if (stmt != null) {
                                    stmt.close();
                                }
                                if (pstmt1 != null) {
                                    pstmt1.close();
                                }
                                if (con1 != null) {
                                    con1.close();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (pstmt2 != null) {
                            pstmt2.close();
                        }
                        if (con2 != null) {
                            con2.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
            return true;
            
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
               
        return false;
    }
    
    public boolean studentsTotalMarksRanking(String admNo){
        Connection con = null, con1 = null;
        PreparedStatement pstmt = null, pstmt1 = null;
        
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT totalMarks, FIND_IN_SET( totalMarks, (\n" +
                "SELECT GROUP_CONCAT( totalMarks\n" +
                "ORDER BY totalMarks DESC ) \n" +
                "FROM totalMarks WHERE form = ? AND merit = ? AND term = ? AND year = ? AND admissionNo = ? )\n" +
                ") AS Rank\n" +
                "FROM totalMarks");
            
            pstmt.setString(1, form);
            pstmt.setString(2, merit);
            pstmt.setString(3, term);
            pstmt.setString(4, year);
            pstmt.setString(5, admNo);
            
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {  
                System.out.println(rs.getString("Rank"));
                try{
                    Class.forName(dc.JDBC_DRIVER);
                    con1 = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
                    pstmt1 = con1.prepareStatement("UPDATE totalMarks SET position=? WHERE "
                    + "admissionNo = ? AND form = ? AND merit = ? AND term = ? AND year = ?");

                    pstmt1.setString(1, rs.getString("Rank"));

                    pstmt1.setString(2, admNo);
                    pstmt1.setString(3, form);
                    pstmt1.setString(4, merit);
                    pstmt1.setString(5, term);
                    pstmt1.setString(6, year);

                    pstmt1.execute();
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    return false;
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (pstmt1 != null) {
                            pstmt1.close();
                        }
                        if (con1 != null) {
                            con1.close();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(subjectsManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            return true;
            
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
               
        return false;
    }
    
    public boolean getStudentsMarks(String admNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT * FROM subjectMarks WHERE admissionNo = ? "
                    + "AND form = ? AND merit = ? AND term = ? AND year = ?");
            
            pstmt.setString(1, admNo);
            pstmt.setString(2, form);
            pstmt.setString(3, merit);
            pstmt.setString(4, term);
            pstmt.setString(5, year);
            
            rs = pstmt.executeQuery();
            
            if (rs.next())
            {  
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
                Logger.getLogger(studentManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
}
