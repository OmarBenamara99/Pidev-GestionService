package tn.khadamni.service;

import tn.khadamni.entity.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.khadamni.tools.MaConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.khadamni.entity.Postule;
import tn.khadamni.entity.Utilisateur;



public class OffreService implements Interface<Offre> {
    
    Connection cnx;
    String sql = "";
            
    
    public OffreService() {
                cnx = MaConnection.getInstance().getcnx();
    }

    @Override
    public void ajouterOffre(Offre o) {
        try {
            sql="insert into offres (Offre_adresse,Offre_date,Offre_description,Offre_image,service_id,Sous_service_id,Service_nom,Sous_service_nom) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setString(1,o.getAdr());
            ste.setDate(2,o.getDate_offre());
            ste.setString(3,o.getDesc());
            ste.setString(4,o.getImg());
            ste.setInt(5,o.getCat());
            ste.setInt(6,o.getScat());
            ste.setString(7,o.getService());
            ste.setString(8,o.getSservice());
            ste.executeUpdate();
                        System.out.println("offre ajoutée");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void ajouterOffree(Offre o) {
        try {
            sql="insert into offres (Offre_adresse,Offre_date,Offre_description,Offre_image,service_id,Sous_service_id,Service_nom,Sous_service_nom,id_client) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setString(1,o.getAdr());
            ste.setDate(2,o.getDate_offre());
            ste.setString(3,o.getDesc());
            ste.setString(4,o.getImg());
            ste.setInt(5,o.getCat());
            ste.setInt(6,o.getScat());
            ste.setString(7,o.getService());
            ste.setString(8,o.getSservice());
            ste.setInt(9,o.getIdC());
            ste.executeUpdate();
                        System.out.println("offre ajoutée");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    @Override
    public List<Offre> getAllOffre() {
        List<Offre> offres = new ArrayList<>();
        try {
            sql = "select * from offres";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                //01 12 23
                Offre o = new Offre(rs.getInt(1), rs.getInt(6), rs.getInt(7), rs.getString("Offre_adresse"), rs.getDate("Offre_date"), rs.getString("Offre_description"), rs.getString("Offre_image"), rs.getString("Service_nom"), rs.getString("Sous_service_nom"));
                offres.add(o);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }
    
    public List<Offre> getAllOffree(int id) {

        List<Offre> offres = new ArrayList<>();

        try {
            String sql = "select * from offres where id_client = ? ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

               Offre o = new Offre(rs.getInt(1), rs.getInt(6), rs.getInt(7), rs.getString("Offre_adresse"), rs.getDate("Offre_date"), rs.getString("Offre_description"), rs.getString("Offre_image"), rs.getString("Service_nom"), rs.getString("Sous_service_nom"));
               offres.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }
    
    
    @Override
    public void supprimerOffre(Offre o) {
        try {
            sql = "delete from offres where Offre_id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, o.getId_offre());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public void modifierOffre(String desc, Offre o) {
     String sql = "update Offres set Offre_description =? where Offre_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, desc);
            ste.setInt(2, o.getId_offre());
            ste.executeUpdate();
            System.out.println("description modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierS(Offre o) {

        String sql = "update offres set Offre_adresse=? , Offre_date=? , Offre_description=? , Offre_image=? , service_id=? , Sous_service_id=?, Service_nom=?, Sous_service_nom=? where Offre_id =?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,o.getAdr());
            ste.setDate(2,o.getDate_offre());
            ste.setString(3,o.getDesc());
            ste.setString(4,o.getImg());
            ste.setInt(5,o.getCat());
            ste.setInt(6,o.getScat());
            ste.setString(7,o.getService());
            ste.setString(8,o.getSservice());
            ste.setInt(9, o.getId_offre());
            
            ste.executeUpdate();
            System.out.println("offre modifié");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
    public void ajouterPostule(Offre o) {
        try {
            sql="insert into offres (Offre_adresse,Offre_date,Offre_description,Offre_image,service_id,Sous_service_id,Service_nom,Sous_service_nom) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ste=cnx.prepareStatement(sql);
            ste.setString(1,o.getAdr());
            ste.setDate(2,o.getDate_offre());
            ste.setString(3,o.getDesc());
            ste.setString(4,o.getImg());
            ste.setInt(5,o.getCat());
            ste.setInt(6,o.getScat());
            ste.setString(7,o.getService());
            ste.setString(8,o.getSservice());
            ste.executeUpdate();
                        System.out.println("offre ajoutée");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    */
    
    
    public List<Offre> getClient(int id) {

        List<Offre> offres = new ArrayList<>();

        try {
            String sql = " SELECT o.Offre_id,o.Offre_adresse,o.Offre_date,o.Offre_description,o.Offre_image,o.Service_id,o.Sous_service_id,o.Service_nom,o.Sous_service_nom,o.id_client, u.nom, u.prenom, u.num, u.adresse FROM offres o JOIN utilisateur u ON o.id_client = u.id WHERE o.Offre_id = ?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
               Utilisateur client = new Utilisateur(rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getInt("num"), rs.getInt(1));
                System.out.println(client);
               Offre o = new Offre(rs.getInt(1), rs.getInt(6), rs.getInt(7), rs.getString("Offre_adresse"), rs.getDate("Offre_date"), rs.getString("Offre_description"), rs.getString("Offre_image"), rs.getString("Service_nom"), rs.getString("Sous_service_nom"), rs.getInt(10), client);
               System.out.println(o);
               offres.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public List<Offre> getFilteredOffers(String service, String sousService) {
    String query = "SELECT * FROM offres WHERE Service_nom = ? AND Sous_service_nom = ?";
    List<Offre> filteredOffers = new ArrayList<>();
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, service);
        statement.setString(2, sousService);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Offre o = new Offre(rs.getInt(1), rs.getInt(6), rs.getInt(7), rs.getString("Offre_adresse"), rs.getDate("Offre_date"), rs.getString("Offre_description"), rs.getString("Offre_image"), rs.getString("Service_nom"), rs.getString("Sous_service_nom"));
            filteredOffers.add(o);
        }
    } catch (SQLException ex) {
        Logger.getLogger(OffreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return filteredOffers;
}
    

    
    
}
