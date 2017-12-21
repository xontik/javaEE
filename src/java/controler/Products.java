/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import beans.Article;
import dao.ArticleDao;
import dao.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xontik
 */
public class Products extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE              = "/WEB-INF/products.jsp";

    private ArticleDao     articleDao;

    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.articleDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getArticleDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Article> articles = (ArrayList<Article>) this.articleDao.findAll();
       
        
        request.setAttribute("articles", articles);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
