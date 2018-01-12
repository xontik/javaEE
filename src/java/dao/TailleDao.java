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
public interface TailleDao {
    Taille map(ResultSet rs) throws SQLException;
}
