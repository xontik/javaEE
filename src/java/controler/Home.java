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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javabeans.Categorie;

/**
 *
 * @author xontik
 */
public class Home extends HttpServlet {

    
    

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
        List<Categorie> cats = new ArrayList<Categorie>();
        cats.add(new Categorie(1, "T-Shirt"));
        cats.add(new Categorie(2, "Jeans"));
        cats.add(new Categorie(3, "Pull"));
        cats.add(new Categorie(4, "Veste"));
        cats.add(new Categorie(5, "Bonnet"));
        
        request.setAttribute("cats", cats);
        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        
        //code servlet de l accueil
    }

    

}
