/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.ArticlePanier;
import beans.Panier;
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
public class PanierDaoImpl implements PanierDao {

    private DAOFactory daoFactory;
    private static final String SQL_SELECT_BY_CLIENT_ID = "SELECT * FROM Panier WHERE idClient =  ?";
    private static final String SQL_INSERT = "INSERT INTO Panier (idClient) VALUES(?)";
    
    PanierDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Panier map(ResultSet rs) throws SQLException {
        Panier pan = new Panier();
        pan.setIdPanier(rs.getInt("idPanier"));
        pan.setTokenPanier(rs.getString("tokenPanier"));
        
        this.daoFactory.getClientDao().map(rs);
        return pan;
    }

    @Override
    public boolean create(int idClient) throws DAOException {  
        Connection connexion = null;
    
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, false, idClient);
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
    public int exist(int idClient) throws DAOException {
         Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_CLIENT_ID, false, idClient);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                return resultSet.getInt("idPanier");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return -1;
    }
    
}
