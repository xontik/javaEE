/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Categorie;
import beans.Client;
import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xontik
 */
public class ClientDaoImpl implements ClientDao {
    private DAOFactory daoFactory;
    private static final String SQL_CONNECTION_TEST = "SELECT * FROM Client WHERE email = ? AND password = ? ";


    ClientDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public Client getFromConnection(String email, String pwd) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_CONNECTION_TEST, false, email, pwd);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return null;
    }
    
    public Client map(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setDateNaissance(resultSet.getDate("dateNaissance"));
        client.setNomClient(resultSet.getString("nomClient"));
        client.setPrenomClient(resultSet.getString("prenomClient"));
        client.setIdClient(resultSet.getInt("idClient"));
        return client;
    }
    
}
