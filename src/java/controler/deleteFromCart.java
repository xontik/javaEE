/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import beans.Client;
import static controler.Login.CONF_DAO_FACTORY;
import dao.ArticlePanierDao;
import dao.DAOFactory;
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
public class deleteFromCart extends HttpServlet {

    private ArticlePanierDao articlePanierDao;
    public static final String CONF_DAO_FACTORY = "daofactory";

    @Override
    public void init() throws ServletException{
        this.articlePanierDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getArticlePanierDao();
    }
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("logged") != null){
            int idClient = ((Client)session.getAttribute("clientObject")).getIdClient();
            int idArticle = Integer.parseInt(request.getParameter("idArticle"));
            int idTaille = Integer.parseInt(request.getParameter("idTaille"));
            this.articlePanierDao.delete(idClient, idArticle, idTaille);
        }
                //gerer les non connecter

        response.sendRedirect("panier");
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
