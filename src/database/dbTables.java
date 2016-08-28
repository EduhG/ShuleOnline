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
    
    public void createTables() {
        dc.createDataBase();
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
}
