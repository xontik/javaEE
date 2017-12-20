/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javabeans.Categorie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xontik
 */
public class Login extends HttpServlet {

    
    

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
        HttpSession session = request.getSession();
        Boolean logged = session.getAttribute("logged") != null ? (Boolean)session.getAttribute("logged"):false;
        
        session.setAttribute("logged",!logged);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        
        //code servlet de l accueil
    }

    

}
