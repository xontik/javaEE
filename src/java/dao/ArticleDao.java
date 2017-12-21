/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import beans.Article;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xontik
 */
public interface ArticleDao {
    void creer ( Article article) throws DAOException;
    Article trouver (int id) throws DAOException ;    
    List<Article> findAll () throws DAOException ;

}
