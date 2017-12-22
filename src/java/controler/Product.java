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
public class Product extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE              = "/WEB-INF/product.jsp";

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
        
        
        Article article = null;
        if(request.getParameter("id") != null){
            try {
                article = (Article) this.articleDao.findById(Integer.parseInt(request.getParameter("id")));
                
            } catch (DAOException e) {
            }
        }
        
        request.setAttribute("article", article);
        if(article != null){
            Categorie catArticle = (Categorie) this.categorieDao.findById(article.getIdCategorie());
            request.setAttribute("catArticle", catArticle);
        }
        
        List<String> css = new ArrayList<String>();
        css.add("product");
        request.setAttribute("css", css);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
