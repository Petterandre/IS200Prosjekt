/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author pette
 */
public class writeDB {
    Connection conn;
    Statement stmt;
    //PreparedStatement sqlRequest;
    
    public void skrivDB(PrintWriter out, Connection conn){
        String sel = "select * from module";
        try {
            stmt =  conn.createStatement();
            ResultSet rset = stmt.executeQuery(sel);
            out.println("<h2>Registrerte moduler:</h2>" + "<br>");
            out.println("<table style=\"width:100%\">");
            out.println("<th>Modul nr</th>");
            out.println("<th>Modul navn</th>");
            out.println("<th>Modul beskrivelse</th>");
            out.println("<th>Fag</th>");
            int rowCount = 0;
            while (rset.next()) {
                
                String moduleID = rset.getString("moduleID");
                String moduleName = rset.getString("moduleName");
                String moduleDescription = rset.getString("moduleDescription");
                String courseID = rset.getString("courseID");
                out.println("<tr>");
                out.println("<td>"+moduleID+"</td>");
                out.println("<td>"+moduleName+"</td>");
                out.println("<td>"+moduleDescription+"</td>");
                out.println("<td>"+courseID+"</td>");
                out.println("</tr>");
                ++rowCount;
            }
            out.println("</table>");
                
            }
        catch (SQLException ex) {
            out.println("Error:" +ex);
        }
        try {conn.close();}
        catch(SQLException ex){out.println("Kunne ikke lukke DB: " + ex);}
    }
    
    
    public void leggtilmodulDB(PrintWriter out, Connection conn, String modulnr, String modulnavn, String modulbeskrivelse){
        PreparedStatement leggtilDB;
        try {
            String addString = "INSERT INTO module (moduleID, moduleName, moduleDescription, courseID) values (?, ?, ?, 'IS-200-1')";
            leggtilDB = conn.prepareStatement(addString);
            leggtilDB.setString(1,modulnr);
            leggtilDB.setString(2,modulnavn);
            leggtilDB.setString(3,modulbeskrivelse);
            leggtilDB.executeUpdate();
            
        }
        
        catch (SQLException ex){
            out.println("Nope."+ex);
        }
        try {conn.close();}
        catch(SQLException ex){out.println("Kunne ikke lukke DB: " + ex);}
        
    }
    
    public void endreDB(PrintWriter out, Connection conn, String modulnr, String modulnavn, String modulbeskrivelse) {
        PreparedStatement endreDB;
        try {
            String editString = "UPDATE module SET moduleName=?, moduleDescription=? WHERE moduleID=?;";
            endreDB = conn.prepareStatement(editString);
            endreDB.setString(1, modulnavn);
            endreDB.setString(2, modulbeskrivelse);
            endreDB.setString(3, modulnr);
            endreDB.executeUpdate();
            out.println("<h2>Modul "+modulnr+": "+ modulnavn + " er endret.</h2>");
            }
        catch (SQLException ex){
            //if (ex.getErrorCode() == 1146){
              //  out.println("<p>Modulnr "+modulnr+" eksisterer ikke. Ingen oppdatering gjort.</p>");
            //}
            out.println(ex);
        }
        try {conn.close();}
        catch(SQLException ex){out.println("Kunne ikke lukke DB: " + ex);}

    }
    
    public void slettDB(PrintWriter out, Connection conn, String modulenr){
        
        PreparedStatement slettDB;
        try {
            String slettString = "DELETE FROM module where moduleID=?";
            slettDB = conn.prepareStatement(slettString);
            slettDB.setString(1, modulenr);
            slettDB.executeUpdate();
                    
        }
        catch (SQLException ex){
            out.println("ERROR: "+ex);
        }
        try {conn.close();}
        catch(SQLException ex){out.println("Kunne ikke lukke DB: " + ex);}
        
    }
    
    
    
    
}
