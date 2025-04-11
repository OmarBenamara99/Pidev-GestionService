/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.service;
import tn.khadamni.entity.Mysession ;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.tools.MaConnection;
/**
 *
 * @author lordg
 */
public class SessionSercive  {
java.sql.Connection cnx;

    public SessionSercive() {
        cnx = MaConnection.getInstance().getcnx();
    }
    
    public void ajouter(Mysession t) {
        try {
            String sql = "insert into session(idu,nom,date_debut,ip)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.setString(2, t.getNom());
            
              Timestamp timestamp = Timestamp.valueOf(t.getDate());
              ste.setTimestamp(3, timestamp);
            ste.setString(4, t.getIpAddress());
            ste.executeUpdate();
            System.out.println("conx ajouter ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int[] getNombreFreelancersEtUtilisateursConnectesAujourdhui() {
    int[] result = new int[2];
    try {
        String sql = "SELECT COUNT(DISTINCT CASE WHEN u.role = 'freelancer' THEN s.idu ELSE NULL END) AS nb_freelancers, " +
                     "COUNT(DISTINCT CASE WHEN u.role = 'user' THEN u.id ELSE NULL END) AS nb_users " +
                     "FROM session s " +
                     "LEFT JOIN utilisateur u ON s.idu = u.id " +
                     "WHERE DATE(s.date_debut) = CURDATE()";
        PreparedStatement statement = cnx.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            result[0] = rs.getInt("nb_freelancers");
            result[1] = rs.getInt("nb_users");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return result;
}
    
    /*@Override
    public List<Connection> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Connection findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(String nom, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
}
