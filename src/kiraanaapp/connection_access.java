/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraanaapp;

import connection.connectionclass;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author satyajit_patil
 */
public class connection_access {
    connectionclass con_class=new connectionclass();
    Connection con;
    
    
    public connection_access() throws SQLException {
        this.con = con_class.getConnection();
    }
    
}
