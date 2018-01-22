/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import beans.Client;
import dao.ArticlePanierDao;
import dao.DAOFactory;
import dao.PanierDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xontik
 */
public class Order extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";

    private PanierDao panierDao;

    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.panierDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPanierDao();

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
        HttpSession session = request.getSession();
        panierDao.order(panierDao.exist(((Client)session.getAttribute("clientObject")).getIdClient()));
        session.setAttribute("sespanier", null);
        session.setAttribute("nbArticle", 0);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
        
    }

    

    

}
