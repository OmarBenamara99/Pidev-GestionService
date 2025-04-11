package tn.khadamni.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.tools.MaConnection;

public class Sous_servicesService implements InterfaceService<Sous_services> {

    Connection cnx;

    public Sous_servicesService() {
        this.cnx = MaConnection.getInstance().getcnx();

    }

    @Override
    public void ajouter(Sous_services c) {
        try {
            String sql = "INSERT INTO sous_services (Sous_service_nom, Sous_service_description, Sous_service_image, Service_id,idFreelancer) VALUES (?, ?, ?, ?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, c.getSous_service_nom());
            ste.setString(2, c.getSous_service_description());
            ste.setString(3, c.getSous_service_image());
            ste.setInt(4, c.getService().getService_id());
            ste.setInt(5, c.getIdFreelancer());
            ste.executeUpdate();
            System.out.println("Sous-services ajoutée avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout des sous-services : " + e.getMessage());
        }
    }

    @Override
    public List<Sous_services> afficher() {
        List<Sous_services> souscategorys = new ArrayList<>();
        try {
            String sql = "select * from sous_services";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                int serviceId = s.getInt("service_id");
                ServicesService se = new ServicesService();
                Services servicee = se.findById(serviceId);

                Sous_services sousService = new Sous_services(
                        s.getInt("Sous_service_id"),
                        s.getString("Sous_service_nom"),
                        s.getString("Sous_service_description"),
                        s.getString("Sous_service_image"),
                        servicee,
                        s.getInt("idFreelancer")
                );
                souscategorys.add(sousService);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return souscategorys;

    }

    @Override
    public void supprimer(Sous_services c) {
        String sql = "delete from sous_services where Sous_service_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getSous_service_id());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierS(Sous_services t) {

        String sql = "update sous_services set Sous_service_nom=?,Sous_service_description=?,Sous_service_image=? where Sous_service_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getSous_service_nom());
            ste.setString(2, t.getSous_service_description());
            ste.setString(3, t.getSous_service_image());
            ste.setInt(4, t.getSous_service_id());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(String nom, Sous_services c) {
        String sql = "update sous_services set Sous_service_nom=? where Sous_service_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2, c.getSous_service_id());
            System.out.println(c.getSous_service_id());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Sous_services findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Sous_services> findByServiceId(int serviceId) {
        List<Sous_services> souscategorys = new ArrayList<>();
        try {
            String sql = "select * from sous_services where Service_id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, serviceId);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                ServicesService servicesService = new ServicesService();
                Services service = servicesService.findById(serviceId);

                Sous_services sousService = new Sous_services(
                        s.getInt(1),
                        s.getString("Sous_service_nom"),
                        s.getString("Sous_service_description"),
                        s.getString("Sous_service_image"),
                        service,
                        s.getInt("idFreelancer")
                );

                souscategorys.add(sousService);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return souscategorys;
    }

}
