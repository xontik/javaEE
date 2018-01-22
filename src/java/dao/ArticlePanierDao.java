/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.ArticlePanier;
import java.util.List;

/**
 *
 * @author xontik
 */
public interface ArticlePanierDao {
    public List<ArticlePanier> findByCartId(int idCart) throws DAOException;
    public List<ArticlePanier> findByClienId(int idClient) throws DAOException;
    public List<ArticlePanier> findByToken(String token) throws DAOException;
    public boolean addByClient(int idClient, int idArticle, int qte, int idTaille) throws DAOException;
    public String addByToken(String token, int idArticle, int qte, int idTaille) throws DAOException;
    public boolean delete(int idClient, int idArticle, int taille) throws DAOException;

    
}
