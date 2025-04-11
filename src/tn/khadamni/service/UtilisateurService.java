/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import tn.khadamni.entity.Freelancer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.khadamni.entity.Utilisateur;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import tn.khadamni.entity.Admin;
import tn.khadamni.tools.JavaMail;
import tn.khadamni.tools.MaConnection;
import tn.khadamni.tools.BcryptHasher;

/**
 *
 * @author lordg
 */
public class UtilisateurService implements InterfaceG<Utilisateur> {

    Connection cnx;
    BcryptHasher hasher;

    public UtilisateurService() {
        cnx = MaConnection.getInstance().getcnx();
         hasher = new BcryptHasher();
    }

    @Override
    public void ajouter(Utilisateur t) {
        try {
            String sql = "insert into utilisateur(nom,prenom,num,adresse,role,mdp)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setString(2, t.getPrenom());
            ste.setInt(3, t.getNumtel());
            ste.setString(4, t.getAdresse());
            ste.setString(5, "user");
           
            ste.setString(6, hasher.hash(t.getMdp()));
            ste.executeUpdate();
            System.out.println("Personne ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
  
    }

    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> users = new ArrayList<>();
        try {
            String sql = "select * from utilisateur where role = 'user' ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Utilisateur u = new Utilisateur(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("mdp"));
                users.add(u);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public Utilisateur findById(int id) {

        Utilisateur u = null;

        try {
            String sql = "select * from utilisateur where id = ? and role = 'user' ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            ResultSet s = ste.executeQuery();
            while (s.next()) {

                u = new Utilisateur(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("mdp"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    public Utilisateur session(String nom, String mdp) {

        Utilisateur u = null;

        try {
            String sql = "select * from utilisateur where adresse = ?   ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            //ste.setString(2, mdp);
           // String m = hasher.hash(mdp)
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                u = new Utilisateur(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("mdp"));

            }
            if (hasher.checkPassword(u.getMdp(), mdp)==false) {
               u=null;
                
            } 

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    @Override
    public void supprimer(int id) {
        String sql = "delete from utilisateur where id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("Personne supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(String nom, int id) {

        String sql = "update utilisateur set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
      public void modifiermdp(String mdp, int id) {

        String sql = "update utilisateur set mdp=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,hasher.hash(mdp));
            ste.setInt(2, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void logout(int id) {
        LocalDateTime now = LocalDateTime.now(); // current date and time
        ; // replace with the IDU you're searching for

// build the query to find the most recent session for the given IDU
        try {
            String query1 = "SELECT id, date_debut FROM session WHERE idu = ? AND date_debut <= ? ORDER BY date_debut DESC LIMIT 1";
            PreparedStatement ps = cnx.prepareStatement(query1);

            ps.setInt(1, id);
            Timestamp timestamp = Timestamp.valueOf(now);
            ps.setTimestamp(2, timestamp);

            // execute the query
            try (ResultSet rs = ps.executeQuery()) {

                // if we found a session, update its end date to the current date and time
                if (rs.next()) {
                    int sessionId = rs.getInt("id");
                    LocalDateTime sessionStart = rs.getTimestamp("date_debut").toLocalDateTime();

                    String query2 = "UPDATE session SET date_fin = ? WHERE id = ?";
                    try (PreparedStatement ps2 = cnx.prepareStatement(query2)) {
                        ps2.setTimestamp(1, Timestamp.valueOf(now));
                        ps2.setInt(2, sessionId);
                        ps2.executeUpdate();
                    }

                } else {
                    System.out.println("No session found for IDU ");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

 
        public Freelancer convert(int id) {

        Freelancer f = null;
        
        try {
            String sql = "select * from utilisateur where id = ?  ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            ResultSet s = ste.executeQuery();
            while (s.next()) {

               f = new Freelancer(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("profession"), s.getBoolean("verified"), s.getString("mdp"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;
    }
         public Admin converta(int id) {
        Admin a = null;
        try {
            String sql = "select * from utilisateur where id = ? ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
              a = new Admin(s.getInt(1), s.getInt(4),s.getString("nom"), s.getString("prenom"),s.getString("adresse"),s.getString("role"), s.getString("mdp"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
    public void modifier(Utilisateur utilisateur) {
    try {
        String sql = "UPDATE utilisateur SET nom=?, prenom=?, adresse=? WHERE id=?";
        PreparedStatement statement = cnx.prepareStatement(sql);
        statement.setString(1, utilisateur.getNom());
        statement.setString(2, utilisateur.getPrenom());
        statement.setString(3, utilisateur.getAdresse());
        //statement.setString(4,hasher.hash(utilisateur.getMdp()) );
        statement.setInt(4, utilisateur.getId());
        int rowsUpdated = statement.executeUpdate();
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        
    }
    }

public void recupermdp(String mail) throws Exception{
     Utilisateur u = null;

        try {
            String sql = "select * from utilisateur where adresse = ?   ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, mail);
           
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                u = new Utilisateur(s.getInt(1), s.getInt(4),
                        s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("mdp"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (u!=null){
          JavaMail.sendMail(mail,u.getMdp());  
        }else{
            System.out.println(" mail invalide");
        }
        
}

public void resetmdp(String mdp,String mail) throws Exception{
     String sql = "update utilisateur set mdp=? where adresse=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, hasher.hash(mdp));
            ste.setString(2,mail );
            ste.executeUpdate();
            JavaMail.sendMail(mail,mdp);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}

}
