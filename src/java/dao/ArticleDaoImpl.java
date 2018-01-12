/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static dao.DAOUtilitaire.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xontik
 */
public class ArticleDaoImpl implements ArticleDao {

    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Article WHERE idArticle = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Article";
    private static final String SQL_SELECT_BY_CATEGORIES = "SELECT * FROM Article WHERE idCategorie = ? ";


    ArticleDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
    @Override
    public Article findById(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Article article = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                article = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return article;
    }

    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
    @Override
    public void creer(Article utilisateur) throws IllegalArgumentException, DAOException {
    }

    public Article map(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        article.setNomArticle(resultSet.getString("nomArticle"));
        article.setIdArticle(resultSet.getInt("idArticle"));        
        article.setImage(resultSet.getString("image"));
        article.setIdCategorie(resultSet.getInt("idCategorie"));
        article.setPrix(resultSet.getFloat("prix"));

        return article;
    }

    @Override
    public List<Article> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Article> articles = new ArrayList<Article>();


        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                Article article = map(resultSet);
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
          
          
          return articles;
    }

    @Override
    public List<Article> findByCategorie(int idCat) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Article> articles = new ArrayList<Article>();


        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_CATEGORIES, false, idCat);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                Article article = map(resultSet);
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
          
          
          return articles;
    }
}
