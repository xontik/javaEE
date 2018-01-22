/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Article;
import beans.Panier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xontik
 */
public interface PanierDao {
    public final int PANIER  = 0;    
    public final int COMMANDER  = 1;
    public final int TERMINE  = 2;

    Panier map(ResultSet rs) throws SQLException;
    public boolean create(int idClient) throws DAOException;
    public String createWithToken() throws DAOException;
    public int exist(int idClient) throws DAOException;
    public int exist(String token) throws DAOException;
    public ArrayList<Panier> getOrders(int idClient) throws DAOConfigurationException;
    public boolean order(int idClient);

}

