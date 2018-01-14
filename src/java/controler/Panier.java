/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import beans.ArticlePanier;
import beans.Categorie;
import beans.Client;
import static controler.Login.CONF_DAO_FACTORY;
import static controler.Product.CONF_DAO_FACTORY;
import dao.ArticlePanierDao;
import dao.CategorieDao;
import dao.ClientDao;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xontik
 */
public class Panier extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";

    private ArticlePanierDao articlePanierDao;
    private CategorieDao categorieDao;

    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.articlePanierDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticlePanierDao();
        this.categorieDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCategorieDao();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Categorie> cats = (ArrayList<Categorie>) this.categorieDao.findAll();
        request.setAttribute("cats", cats);
        //connecter -> panier via idclient

        //pas connecter -> panier via id cookie
        //pas de panier pour le moment
        ArrayList<ArticlePanier> laps;
        if (session.getAttribute("logged") != null) {
            laps = (ArrayList<ArticlePanier>) this.articlePanierDao.findByClienId(((Client) session.getAttribute("clientObject")).getIdClient());
            
        }else{
            laps =  (ArrayList<ArticlePanier>) this.articlePanierDao.findByToken((String) session.getAttribute("token"));
        }
        if (laps != null) {
                double total = 0;
                for (ArticlePanier lap : laps) {
                    total += lap.getArticle().getPrix() * lap.getQuantite();
                }
                request.setAttribute("total", total);
            }
        session.setAttribute("sespanier", laps);
        session.setAttribute("nbArticle", laps.size());

        this.getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);

    }

}
