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
import beans.Categorie;
import static controler.Products.CONF_DAO_FACTORY;
import dao.CategorieDao;
import dao.DAOFactory;

/**
 *
 * @author xontik
 */
public class Home extends HttpServlet {

    
    private CategorieDao     categorieDao;

    @Override
    public void init(){
        this.categorieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCategorieDao();

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
        ArrayList<Categorie> cats = (ArrayList<Categorie>) this.categorieDao.findAll();
        request.setAttribute("cats", cats);
        
        ArrayList<String> catsImage = (ArrayList < String >)this.categorieDao.findImages();
        request.setAttribute("catsImage", catsImage);

        List<String> css = new ArrayList<String>();
        css.add("products");
        request.setAttribute("css", css);
        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        
        //code servlet de l accueil
    }

    

}
