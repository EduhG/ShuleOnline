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
    public String admNo;
    public String admDate;
    public String firstName;
    public String middleName;
    public String lastName;
    public String gender = "Male";
    public String dateOfBirth;
    public String birthCertNo;
    public String form;
    public String stream;
    public String admType = "Boarder";
    public static String status;

    ResultSet rsmem;
    int rscount;
    String admission_number = "0";
    
    public boolean addNewStudentDetails(student student) {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            Class.forName(dc.JDBC_DRIVER);
            con = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO StudentDetails(studentId, admissionNo, admissionDate, "
                    + "surname, firstname, lastname, gender, dateofbirth, birthcertno, "
                    + "form, stream, admissionType, studentStatus) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pstmt.setInt(1, student.getStudentId());
            pstmt.setString(2, student.getAdmNo());
            pstmt.setString(3, student.getAdmDate());
            pstmt.setString(4, student.getMiddleName());
            pstmt.setString(5, student.getFirstName());
            pstmt.setString(6, student.getLastName());
            pstmt.setString(7, student.getGender());
            pstmt.setString(8, student.getDateOfBirth());
            pstmt.setString(9, student.getBirthCertNo());
            pstmt.setString(10, student.getForm());
            pstmt.setString(11, student.getStream());
            pstmt.setString(12, student.getAdmType());
            pstmt.setString(13, student.getStatus());

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
                Logger.getLogger(studentManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }
    
    public void StudentDetails() {
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
                Logger.getLogger(studentManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void Display() {
        //student student = new student();
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
            student.setStream(rsmem.getString("stream"));
            student.setAdmType(rsmem.getString("admissionType"));
            
            admNo = student.getAdmNo();
            admDate = student.getAdmDate();
            firstName = student.getFirstName();
            middleName = student.getMiddleName();
            lastName = student.getLastName();
            gender = student.getGender();
            dateOfBirth = student.getDateOfBirth();
            birthCertNo = student.getBirthCertNo();
            form = student.getForm();
            stream = student.getStream();
            admType = student.getAdmType();

        } catch (Exception e) {
            e.printStackTrace();
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
        
        admNo = student.getAdmNo();
    }
}
