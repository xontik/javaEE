/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import beans.Article;
import beans.Categorie;
import dao.ArticleDao;
import dao.CategorieDao;
import dao.DAOException;
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
    private CategorieDao     categorieDao;


    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.articleDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getArticleDao();
        this.categorieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCategorieDao();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Categorie> cats = (ArrayList<Categorie>) this.categorieDao.findAll();
        request.setAttribute("cats", cats);
        
        
        ArrayList<Article> articles;
        if(request.getParameter("categorie") != null){
            System.out.print(request.getParameter("categorie"));
            try {
                articles = (ArrayList<Article>) this.articleDao.findByCategorie(Integer.parseInt(request.getParameter("categorie")));
                
            } catch (DAOException e) {
                articles = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            articles = (ArrayList<Article>) this.articleDao.findAll();
        }
        
        request.setAttribute("nbArticles", articles.size());
        request.setAttribute("articles", articles);
        List<String> css = new ArrayList<String>();
        css.add("products");
        request.setAttribute("css", css);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
