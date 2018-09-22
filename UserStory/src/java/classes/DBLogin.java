
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

JDBC JNDI DataSource Servlet
 */
package classes;

import java.io.PrintWriter;
import java.sql.*; 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author hallgeir
 */
public class DBLogin {
    Connection conn; 
    Statement stmt;
    
    
    public void close() {
        
        try {
            conn.close();
        }
        catch (SQLException ex) {
            System.out.println("Klarte ikke lukke databasen " + ex);
            
        }
    }
    
    public void commit() {
        
        try {
            conn.commit();
        }
        catch (SQLException ex) {
            System.out.println("Kunne ikke lukke database " + ex);
        }
    }
    
   // @Resource DataSource LocalhostDS;
    public Connection getConnection(PrintWriter out) {
        try {
         // Step 1: Allocate a database 'Connection' object
         Context cont = new InitialContext();
         DataSource ds = (DataSource)cont.lookup("java:comp/env/jdbc/localhostDS");  
         //DataSource ds = (DataSource)cont.lookup("jdbc/LocalhostDS");
         conn =  ds.getConnection();
 
         // Step 2: Allocate a 'Statement' object in the Connection
         stmt = conn.createStatement();
        }
        catch (SQLException ex ) {
            out.println("Not connected to database " +ex);
        }
        catch (NamingException nex) {
            out.println("Not correct naming" + nex);
        }
            
        return conn;
    }
    
             
   
}// slutt 