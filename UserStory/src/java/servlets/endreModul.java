/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.DBLogin;
import classes.writeDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pette
 */
@WebServlet(name = "endreModul", urlPatterns = {"/endreModul"})
public class endreModul extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" href=\"stylesheets/mother.css\">");
            out.println("<title>Servlet hentModuler</title>");            
            out.println("</head>");
            out.println("<body>");

            out.println("<div id=\"wrapper\">");
            out.println("<div id=\"header\"></div>");
            out.println("<div id=\"navbar\"><a href=\"index.html\">Hjem</a></div>");
            out.println("<div id=\"content\">");
            out.println("<br><br><br>");
            String modulnr = request.getParameter("modulnr");
            String modulnavn = request.getParameter("modulnavn");
            String modulbeskrivelse = request.getParameter("modulbeskrivelse");
            if (modulnr.isEmpty()){
                out.println("<h3>Modulnr kan ikke vaere tom");
            }
            if (modulnavn.isEmpty()){
                out.println("<h3>Modulnavn kan ikke vaere tom");
            }
            if (modulnavn.isEmpty()){
                out.println("<h3>Modulbeskrivelse kan ikke vaere tom");
            }else {
                DBLogin dbLogin = new DBLogin();
                writeDB dbWrite = new writeDB();
                Connection conn;            
                conn = dbLogin.getConnection(out);
                dbWrite.endreDB(out, conn, modulnr, modulnavn, modulbeskrivelse);
                
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
            out.println("</div>");
            out.println("<div id=\"footer\"></div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
                
        }
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}