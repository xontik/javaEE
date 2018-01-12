/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import beans.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xontik
 */
public interface ArticleDao {
    void creer ( Article article) throws DAOException;
    Article findById (int id) throws DAOException ;    
    List<Article> findAll () throws DAOException ;
    List<Article> findByCategorie (int idCat) throws DAOException ;
    Article map (ResultSet rs) throws SQLException ;    

    



}
