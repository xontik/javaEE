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
import java.util.Date;
import java.util.List;

/**
 *
 * @author xontik
 */
public class PanierDaoImpl implements PanierDao {

    private DAOFactory daoFactory;
    private static final String SQL_SELECT_BY_CLIENT_ID = "SELECT * FROM Panier WHERE idClient = ? AND etat = 0";
    private static final String SQL_INSERT = "INSERT INTO Panier (idClient,etat) VALUES(?,0)";
    private static final String SQL_INSERT_TOKEN = "INSERT INTO Panier (tokenPanier,etat) VALUES(?,0)";
    private static final String SQL_SELECT_BY_TOKEN = "SELECT * FROM Panier WHERE tokenPanier = ? AND etat = 0";
    private static final String SQL_SELECT_ORDERS = "SELECT * FROM Panier JOIN Client USING(idClient) WHERE idClient = ? AND etat != 0 ORDER BY dateCommande DESC";
    private static final String SQL_COMMAND = "UPDATE Panier SET etat = 1 , dateCommande = CURDATE(), dateLivraison = DATE_ADD(CURDATE(), INTERVAL 3 DAY) WHERE idPanier = ?";

    
    PanierDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    //TODO ajout au panier cassé 
    //tester liste commandes
    @Override
    public Panier map(ResultSet rs) throws SQLException {
        Panier pan = new Panier();
        pan.setIdPanier(rs.getInt("idPanier"));
        pan.setTokenPanier(rs.getString("tokenPanier"));
        pan.setIdClient(daoFactory.getClientDao().map(rs));
        pan.setEtat(rs.getInt("etat"));
        Date date = rs.getDate("dateCommande");
        if(date == null){
            pan.setDateCommande(null);
        }else{
            pan.setDateCommande(date);
        }
        date = rs.getDate("dateLivraison");
        if(date == null){
            pan.setDateLivraison(null);
        }else{
            pan.setDateLivraison(date);
        }
        pan.setLivre();
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
    @Override
    public int exist(String token) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BY_TOKEN, false, token);
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

    @Override
    public String createWithToken() throws DAOException {
    Connection connexion = null;
    String hash = null;
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            hash = String.valueOf(connexion.hashCode());
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT_TOKEN, false, hash);
            int r = preparedStatement.executeUpdate();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (r != 1) {
                hash = null;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }

        return hash;
    }

    @Override
    public ArrayList<Panier> getOrders(int idClient) throws DAOConfigurationException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Panier> paniers =  new ArrayList<>();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_ORDERS, false, idClient);
            ResultSet rs = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while(rs.next()){
                paniers.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }

        return paniers;
    }

    @Override
    public boolean order(int idCart) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        boolean isok = true;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            
            preparedStatement = initialisationRequetePreparee(connexion, SQL_COMMAND, false, idCart);
            int r = preparedStatement.executeUpdate();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (r != 1) {
                isok = false;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion);
        }
        
        return isok;
    }
    
}
