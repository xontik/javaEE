/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Article;
import beans.Categorie;
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
public class CategorieDaoImpl implements CategorieDao {

    /* Implémentation de la méthode trouver() définie dans l'interface UtilisateurDao */
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Categorie WHERE idCategorie = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Categorie ORDER BY idCategorie";
    private static final String SQL_SELECT_IMAGES_BY_CATS = "SELECT image FROM Article group by idCategorie order by idCategorie";


    CategorieDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
    @Override
    public Categorie findById(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Categorie cat = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                cat = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return cat;
    }

    /* Implémentation de la méthode creer() définie dans l'interface UtilisateurDao */
    @Override
    public void creer(Categorie utilisateur) throws IllegalArgumentException, DAOException {
    }

    private static Categorie map(ResultSet resultSet) throws SQLException {
        Categorie cat = new Categorie();
        cat.setNom(resultSet.getString("nom"));
        cat.setIdCategorie(resultSet.getInt("idCategorie"));
        return cat;
    }

    @Override
    public List<Categorie> findAll() throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null; 
        ResultSet resultSet = null;
        ArrayList<Categorie> cats = new ArrayList<Categorie>();


        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                Categorie cat = map(resultSet);
                cats.add(cat);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
          
          
          return cats;
    }
    
    public List<String> findImages() throws DAOException {
        
        List<String> images = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_IMAGES_BY_CATS, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                images.add(resultSet.getString("image"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        
        return images;
    }

}
