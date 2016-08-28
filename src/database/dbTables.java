/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author EduhG
 */
public class dbTables {
    static dbConnection dc = new dbConnection();
    
    public void streams() {
        try {
            Connection connection;
            connection = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);

            String tableName = "streams";
            
            String table_query = "CREATE TABLE IF NOT EXISTS "
                    + tableName + " ("
                    + "streamId INTEGER ( 6 ) PRIMARY KEY AUTO_INCREMENT ,"
                    + "streamcode VARCHAR( 20 ) NULL ,"
                    + "streamName VARCHAR( 20 ) NULL)";

            Statement sta = connection.createStatement();
            int count = sta.executeUpdate(table_query);

            sta.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void subjects() {
        try {
            Connection connection;
            connection = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);

            String tableName = "subjects";
            
            String table_query = "CREATE TABLE IF NOT EXISTS "
                    + tableName + " ("
                    + "subjectId INTEGER ( 6 ) PRIMARY KEY AUTO_INCREMENT ,"
                    + "subjectCode VARCHAR( 5 ) NULL ,"
                    + "subjectName VARCHAR( 20 ) NULL ,"
                    + "cartegory VARCHAR( 200 ) NULL ,"
                    + "subcartegory VARCHAR( 200 ) NULL ,"
                    + "totalMarks INTEGER ( 2 ) NULL)";

            Statement sta = connection.createStatement();
            int count = sta.executeUpdate(table_query);

            sta.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void forms() {
        try {
            Connection connection;
            connection = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);

            String tableName = "classes";
            
            String table_query = "CREATE TABLE IF NOT EXISTS "
                    + tableName + " ("
                    + "classId INTEGER ( 6 ) PRIMARY KEY AUTO_INCREMENT ,"
                    + "classCode VARCHAR( 20 ) NULL ,"
                    + "className VARCHAR( 20 ) NULL ,"
                    + "level VARCHAR( 200 ) NULL)";

            Statement sta = connection.createStatement();
            int count = sta.executeUpdate(table_query);

            sta.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void systemUsersTables() {
        try {
            Connection connection;
            connection = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);

            String tableName = "systemUsers";
            
            String table_query = "CREATE TABLE IF NOT EXISTS "
                    + tableName + " ("
                    + "userId INTEGER ( 6 ) PRIMARY KEY AUTO_INCREMENT ,"
                    + "username VARCHAR( 200 ) NULL ,"
                    + "firstname VARCHAR( 200 ) NULL ,"
                    + "lastname VARCHAR( 200 ) NULL ,"
                    + "password VARCHAR( 200 ) NULL ,"
                    + "privilledges VARCHAR( 200 ) NULL)";

            Statement sta = connection.createStatement();
            int count = sta.executeUpdate(table_query);

            sta.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void StudentDetails() {
        try {
            Connection connection;
            connection = DriverManager.getConnection(dc.DATABASE_URL, dc.USERNAME, dc.PASSWORD);

            String tableName = "StudentDetails";
            
            String table_query = "CREATE TABLE IF NOT EXISTS "
                + tableName + " ("
                + "studentId INTEGER ( 6 ) PRIMARY KEY AUTO_INCREMENT ,"
                + "admissionNo VARCHAR( 100 ) NOT NULL ,"
                + "admissionDate VARCHAR( 200 ) NULL ,"
                + "surname VARCHAR( 200 ) NULL ,"
                + "firstname VARCHAR( 200 ) NULL ,"
                + "lastname VARCHAR( 200 ) NULL ,"
                + "gender VARCHAR( 200 ) NULL ,"
                + "dateofbirth VARCHAR( 200 ) NULL ,"
                + "birthcertno VARCHAR( 200 ) NULL ,"
                + "age INTEGER( 2 ) NULL ,"
                + "bloodgroup VARCHAR( 200 ) NULL ,"
                + "form VARCHAR( 200 ) NULL ,"
                + "stream VARCHAR( 200 ) NULL ,"
                + "admissionType VARCHAR( 200 ) NULL ,"
                + "studentStatus VARCHAR( 200 ) NULL)";

            Statement sta = connection.createStatement();
            int count = sta.executeUpdate(table_query);

            sta.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void createTables() {
        dc.createDataBase();
        
        systemUsersTables();
        StudentDetails();
    }
}
