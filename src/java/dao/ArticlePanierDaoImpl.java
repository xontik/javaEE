/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.ArticlePanier;
import beans.Categorie;
import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xontik
 */
public class ArticlePanierDaoImpl implements ArticlePanierDao{

   
    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_BY_CART_ID = "SELECT * FROM `ArticlePanier` "
            + "JOIN Article USING(idArticle) "
            + "JOIN Taille USING(idTaille)"
            + "JOIN Panier USING(idPanier)"
            + "JOIN Client USING(idClient)"
            + "WHERE idPanier = ?";
private static final String SQL_SELECT_BY_CLIENT_ID = "SELECT * FROM `ArticlePanier` "
            + "JOIN Article USING(idArticle) "
            + "JOIN Taille USING(idTaille)"
            + "JOIN Panier USING(idPanier)"
            + "JOIN Client USING(idClient)"
            + "WHERE idClient = ?";
private static final String SQL_INSERT = "INSERT INTO ArticlePanier (idArticle, idPanier, idTaille, quantite) VALUES (?,?,?,?)";
private static final String SQL_DELETE = "DELETE FROM ArticlePanier WHERE idArticle = ? AND idPanier = ? AND idTaille = ?";


    ArticlePanierDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<ArticlePanier> findByCartId(int idCart) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ArticlePanier> articlesPanier = new ArrayList<>();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_CART_ID, false, idCart);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                ArticlePanier article = map(resultSet);
                articlesPanier.add(article);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return articlesPanier;
    }
    
    public ArticlePanier map(ResultSet rs) throws SQLException{
        ArticlePanier article = new ArticlePanier(rs.getInt("idArticle"),rs.getInt("idPanier"),rs.getInt("idTaille"));
        article.setArticle(this.daoFactory.getArticleDao().map(rs));
        article.setPanier(this.daoFactory.getPanierDao().map(rs));
        article.setTaille(this.daoFactory.getTailleDao().map(rs));
        article.setQuantite(rs.getInt("quantite"));
        return article;
    }

    @Override
    public List<ArticlePanier> findByClienId(int idClient) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ArticlePanier> articlesPanier = new ArrayList<>();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_CLIENT_ID, false, idClient);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                ArticlePanier article = map(resultSet);
                articlesPanier.add(article);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return articlesPanier;
    }

    @Override
    public boolean add(int idClient, int idArticle, int qte, int idTaille) {
        this.delete(idClient, idArticle, idTaille);
        PanierDao panDao = this.daoFactory.getPanierDao();
        
        if(panDao.exist(idClient) > -1){
            panDao.create(idClient);
        }
        
        int idPan = panDao.exist(idClient);
        Connection connexion = null;
    
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, false, idArticle,idPan,idTaille,qte);
            int r = preparedStatement.executeUpdate();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (r == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }

        return false;
        
    }

    @Override
    public boolean delete(int idClient, int idArticle, int idTaille) throws DAOException {
 PanierDao panDao = this.daoFactory.getPanierDao();
        
        if(panDao.exist(idClient) > -1){
            panDao.create(idClient);
        }
        
        int idPan = panDao.exist(idClient);
        Connection connexion = null;
    
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_DELETE, false, idArticle,idPan,idTaille);
            int r = preparedStatement.executeUpdate();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (r == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }

        return false;
    }


}
