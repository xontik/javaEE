/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import beans.Article;
import beans.Categorie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xontik
 */
public interface CategorieDao {
    void creer ( Categorie article) throws DAOException;
    Categorie findById (int id) throws DAOException ;    
    List<Categorie> findAll () throws DAOException ;

}
