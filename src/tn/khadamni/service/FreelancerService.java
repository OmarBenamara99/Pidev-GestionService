
package tn.khadamni.service;

import tn.khadamni.entity.Freelancer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import tn.khadamni.tools.BcryptHasher;
import tn.khadamni.tools.MaConnection;

public class FreelancerService implements InterfaceG<Freelancer> {

    Connection cnx;
    BcryptHasher hasher;

    public FreelancerService() {
        cnx = MaConnection.getInstance().getcnx();
        hasher = new BcryptHasher();
    }

    @Override
    public void ajouter(Freelancer f) {
        try {
            String sql = "insert into utilisateur(nom, prenom, num, adresse, photo, description, rate, profession, verified, role,mdp) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, f.getNom());
            ste.setString(2, f.getPrenom());
            ste.setInt(3, f.getNumtel());
            ste.setString(4, f.getAdresse());
            ste.setString(5, f.getPhoto());
            ste.setString(6, f.getDescription());
            ste.setInt(7, f.getRate());
            ste.setString(8, f.getProfession());
            ste.setBoolean(9, f.isVerified());
            ste.setString(10, "freelancer");
            ste.setString(11,hasher.hash(f.getMdp()) );
            ste.executeUpdate();
            System.out.println("Freelancer ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Freelancer> afficher() {
        List<Freelancer> freelancers = new ArrayList<>();
        try {
            String sql = "select * from utilisateur where role = 'freelancer' ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Freelancer f = new Freelancer(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("profession"), s.getBoolean("verified"), s.getString("mdp"));
                freelancers.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return freelancers;
    }

    @Override
    public Freelancer findById(int id) {
        Freelancer f = null;
        try {
            String sql = "select * from utilisateur where id = ?";
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
 public  List<Freelancer>  findByNom(String Nom) {
    List<Freelancer> freelancers = new ArrayList<>();

            Freelancer f = null;
        try {
            String sql = "select * from utilisateur where profession=? and role='freelancer'";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, Nom);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                  f = new Freelancer(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("profession"), s.getBoolean("verified"), s.getString("mdp"));
           freelancers.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return freelancers;
        }
    @Override
    public void supprimer(int id) {
        String sql = "delete from utilisateur where id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("Freelancer supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(String nom ,int id) {
     String sql = "update utilisateur set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2,id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     public  Freelancer session(String nom ,String mdp) {
          
          Freelancer f = null;
    
          try {
            String sql = "select * from utilisateur where adresse = ?  ";
            
              PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, nom );
            // ste.setString(2, mdp );
            ResultSet s = ste.executeQuery();
            while (s.next()) {

                 f = new Freelancer(s.getInt(1), s.getInt(4), s.getString("nom"), s.getString("prenom"), s.getString("adresse"), s.getString("role"), s.getString("photo"), s.getString("description"), s.getInt("rate"), s.getString("profession"), s.getBoolean("verified"), s.getString("mdp"));
                

            }
            if (hasher.checkPassword(f.getMdp(), mdp)==false) {
               f=null;
                
            } 
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return f;
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
     
     public void modifier(Freelancer f) {
    try {
        String sql = "update utilisateur set nom = ?, prenom = ?, num = ?, adresse = ?, photo = ?, description = ?, rate = ?, profession = ?, verified = ? where id = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, f.getNom());
        ste.setString(2, f.getPrenom());
        ste.setInt(3, f.getNumtel());
        ste.setString(4, f.getAdresse());
        ste.setString(5, f.getPhoto());
        ste.setString(6, f.getDescription());
        ste.setInt(7, f.getRate());
        ste.setString(8, f.getProfession());
        ste.setBoolean(9, f.isVerified());
        //ste.setString(10,hasher.hash(f.getMdp()));
        ste.setInt(10, f.getId());
        ste.executeUpdate();
        System.out.println("Freelancer modifié");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
      
    public List<Freelancer> afficher1(int sousServiceId) {
    List<Freelancer> freelancers = new ArrayList<>();
    try {
     String sql = "SELECT u.id, u.nom, u.prenom, u.num, u.adresse, u.role, u.photo, u.description, u.rate, u.profession, u.verified, u.mdp " +
             "FROM utilisateur u " +
             "INNER JOIN sous_services ss ON u.id = ss.idFreelancer  " +
             "WHERE ss.Sous_service_id = ? " +
             "AND u.role = 'freelancer'";

        PreparedStatement ps = cnx.prepareStatement(sql);
        ps.setInt(1, sousServiceId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Freelancer f = new Freelancer(rs.getInt("id"), rs.getInt("num"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("role"), rs.getString("photo"), rs.getString("description"), rs.getInt("rate"), rs.getString("profession"), rs.getBoolean("verified"), rs.getString("mdp"));
            freelancers.add(f);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return freelancers;
}
       
}



