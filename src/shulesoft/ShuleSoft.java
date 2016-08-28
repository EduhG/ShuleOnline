/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shulesoft;

import database.dbConnection;

/**
 *
 * @author eduhg
 */
public class ShuleSoft {

    /**
     * @param args the command line arguments
     */
    static dbConnection dc = new dbConnection();
    
    public static void main(String[] args) {
        // TODO code application logic here
        dc.createDataBase();
    }
    
}
