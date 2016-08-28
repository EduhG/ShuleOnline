/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentManagement;

/**
 *
 * @author EduhG
 */
public class student {
    public static String admission_number;
    
    private static int studentId;
    private String admNo;
    private String admDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String birthCertNo;
    private String form;
    private String stream;
    private String admType;
    private static String status;

    
    public void setStudentId(int t) {
        studentId = t;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public String getAdmNo() {
        return admNo;
    }

    public void setAdmNo(String admNo) {
        this.admNo = admNo;
    }

    public String getAdmDate() {
        return admDate;
    }

    public void setAdmDate(String admDate) {
        this.admDate = admDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBirthCertNo() {
        return birthCertNo;
    }

    public void setBirthCertNo(String birthCertNo) {
        this.birthCertNo = birthCertNo;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getAdmType() {
        return admType;
    }

    public void setAdmType(String admType) {
        this.admType = admType;
    }
    
    public void setStatus(String t) {
        status = t;
    }
    
    public String getStatus() {
        return status;
    }
}
