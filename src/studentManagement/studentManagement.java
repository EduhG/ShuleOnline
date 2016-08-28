/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentManagement;

import database.dbConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EduhG
 */
public class studentManagement {
    dbConnection dc = new dbConnection();
    student student = new student();

    ResultSet rsmem;
    int rscount;
    String admission_number = "0";
    
    private void StudentDetails() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("SELECT COUNT(*) FROM StudentDetails");
            
            rs = pstmt.executeQuery();
            rs.first();
            rscount=rs.getInt(1);

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sqlQuery = "SELECT * FROM StudentDetails";
            
            rsmem = stmt.executeQuery(sqlQuery);
            rsmem.last();

            Display();

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
    }
    
    private void Display() {
        try {
            student.admission_number = Integer.toString(rscount);
            
            student.setAdmNo(rsmem.getString("admissionNo"));
            student.setAdmDate(rsmem.getString("admissionDate"));
            student.setMiddleName(rsmem.getString("surname"));
            student.setFirstName(rsmem.getString("firstname"));
            student.setLastName(rsmem.getString("lastname"));
            student.setGender(rsmem.getString("gender"));
            student.setDateOfBirth(rsmem.getString("dateofbirth"));
            student.setBirthCertNo(rsmem.getString("birthcertno"));
            student.setForm(rsmem.getString("form"));
            student.setForm(rsmem.getString("stream"));
            student.setAdmType(rsmem.getString("admissionType"));

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void NewAdmissionNumber()
    {
        StudentDetails();
        
        admission_number = Integer.toString(rscount);
        
        int next = (Integer.parseInt(admission_number) + 1);
        admission_number = Integer.toString(next);
        int no = Integer.toString(next).length();

        if (no == 1) {
            student.setAdmNo("RA:000"+admission_number);
        } else
        if (no == 2) {
            student.setAdmNo("RA:00"+admission_number);
        } else
        if (no == 3) {
            student.setAdmNo("RA:0"+admission_number);
        } else
        if (no == 4) {
            student.setAdmNo("RA:"+admission_number);
        }
    }
}
