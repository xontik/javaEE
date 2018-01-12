/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Taille;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xontik
 */
public class TailleDaoImpl implements TailleDao{

    private  DAOFactory daoFactory;
    
    TailleDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Taille map(ResultSet rs) throws SQLException {
           Taille t = new Taille();
           t.setIdTaille(rs.getInt("idTaille"));
           t.setNom(rs.getString("code"));
           return t;
    }
    
    
}
