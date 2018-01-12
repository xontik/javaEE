/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import beans.Client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xontik
 */
public interface ClientDao {
    Client getFromConnection(String email, String pwd) throws DAOException;
    Client map(ResultSet rs) throws SQLException;

}
