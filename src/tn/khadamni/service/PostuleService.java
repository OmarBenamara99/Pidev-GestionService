package tn.khadamni.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.entity.Offre;
import tn.khadamni.entity.Postule;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.tools.MaConnection;

public class PostuleService {
    
    Connection cnx;
    String sql = "";
    
    public PostuleService() {
                cnx = MaConnection.getInstance().getcnx();
    }
    
    public void ajouterPostule(Postule p) {
        try {
            sql="insert into postulation (Postulation_date,Offre_id,Freelancer_id) values (?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setDate(1,p.getPostulation_date());
            ste.setInt(2,p.getOffre_id());
            ste.setInt(3,p.getFreelancer_id());
            ste.executeUpdate();
                        System.out.println("postule ajoutée");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Postule> getAllpostulation(int id) {

        List<Postule> Postules = new ArrayList<>();

        try {
            String sql = " SELECT p.Postulation_id, p.Offre_id, p.Postulation_date, u.nom, u.prenom, u.num, u.adresse FROM postulation p JOIN utilisateur u ON p.Freelancer_id = u.id WHERE p.Offre_id = ?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
               Utilisateur freelancer = new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getInt("num"), rs.getInt(1));
                System.out.println(freelancer);
               Postule p = new Postule(rs.getInt(1), rs.getInt(3), freelancer, rs.getDate("Postulation_date"));
               Postules.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Postules;
    }
    
    public void Refu(Postule p) {
        try {
            sql = "delete from postulation where Postulation_id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getPostulation_id());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
}
