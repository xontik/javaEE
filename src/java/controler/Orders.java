/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import beans.ArticlePanier;
import beans.Categorie;
import beans.Client;
import beans.Panier;
import dao.ArticlePanierDao;
import dao.CategorieDao;
import dao.DAOFactory;
import dao.PanierDao;
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
public class Orders extends HttpServlet {

    
    public static final String CONF_DAO_FACTORY = "daofactory";

    private ArticlePanierDao articlePanierDao;
    private PanierDao panierDao;

    @Override
    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.articlePanierDao = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getArticlePanierDao();
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
        //TODO recuperer tout les panier then recuperer chaque panier
        HttpSession session = request.getSession();
        ArrayList<ArrayList> commandes = new ArrayList<>(); 
        ArrayList<Double> totaux = new ArrayList<>();
        
        ArrayList<Panier> paniers = this.panierDao.getOrders(((Client)session.getAttribute("clientObject")).getIdClient());
        
        for (Panier panier : paniers) {
            ArrayList<ArticlePanier> laps = (ArrayList<ArticlePanier>) this.articlePanierDao.findByCartId(panier.getIdPanier());
                                    double total = 0;
    
                        for (ArticlePanier lap : laps) {
                            total += lap.getArticle().getPrix() * lap.getQuantite();
                        }
                        
                totaux.add(total);
                commandes.add(laps);
        }
       request.setAttribute("commandes", commandes);
       request.setAttribute("totaux", totaux);       
       request.setAttribute("paniers", paniers);


       request.getRequestDispatcher("WEB-INF/orders.jsp").forward(request, response);
    }


}
