/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import beans.ArticlePanier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Categorie;
import beans.Client;
import dao.ArticleDao;
import dao.ArticlePanierDao;
import dao.CategorieDao;
import dao.ClientDao;
import dao.DAOFactory;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xontik
 */
public class Login extends HttpServlet {

    
    
    public static final String CONF_DAO_FACTORY = "daofactory";

    private ClientDao     clientDao;  
    private ArticlePanierDao     articlePanierDao;


    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.clientDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getClientDao();
        this.articlePanierDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getArticlePanierDao();

    }
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
        
        if(logged == true){ 
            session.invalidate();

            this.getServletContext().getRequestDispatcher("/WEB-INF/logged.jsp").forward(request, response);

        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }

        //code servlet de l accueil
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean logged = session.getAttribute("logged") != null ? (Boolean)session.getAttribute("logged"):false;
        if(logged == true){
            this.getServletContext().getRequestDispatcher("/WEB-INF/logged.jsp").forward(request, response);
        }else{
            //check bd
            Client client = this.clientDao.getFromConnection(request.getParameter("email"), request.getParameter("password"));
            if( client != null){
                session.setAttribute("logged", true);
                logged = true;
                session.setAttribute("clientObject", client);
                
                session.setAttribute("sespanier", this.articlePanierDao.findByClienId(client.getIdClient()));
                session.setAttribute("nbArticle", ((ArrayList<ArticlePanier>)session.getAttribute("sespanier")).size());
            }else{
                request.setAttribute("error", "Erreur dans le couple email / mot de passe");
            }
            
            
            if(logged == true){
                this.getServletContext().getRequestDispatcher("/WEB-INF/logged.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
        
 
        
        //code servlet de l accueil
    }

    

}
