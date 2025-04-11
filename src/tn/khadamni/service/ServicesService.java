package tn.khadamni.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.khadamni.entity.Services;
import tn.khadamni.tools.MaConnection;

/**
 *
 * @author Fayechi
 */
public class ServicesService implements InterfaceService<Services> {

    Connection cnx;

    public ServicesService() {
        cnx = MaConnection.getInstance().getcnx();
    }

    @Override
    public void ajouter(Services t) {
        try {
            String sql = "insert into services(service_nom ,service_description,service_image,nb_sous_services)values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getService_nom());
            ste.setString(2, t.getService_description());
            ste.setString(3, t.getService_image());
            ste.setInt(4, t.getNb_sous_services());
            ste.executeUpdate();
            System.out.println("Service ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Services> afficher() {
        List<Services> serv = new ArrayList<>();
        try {
            String sql = "select * from services";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Services p = new Services(s.getInt(1), s.getString("service_nom"), s.getString("service_description"), s.getString("service_image"), s.getInt(5));
                serv.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return serv;
    }

    @Override
    public void supprimer(Services t) {
        String sql = "delete from services where service_id =?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getService_id());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(String nom, Services t) {

        String sql = "update services set service_nom=? where service_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2, t.getService_id());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierS( Services t) {

        String sql = "update services set service_nom=?,service_description=?,service_image=?,nb_sous_services=? where service_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getService_nom());
            ste.setString(2, t.getService_description());
            ste.setString(3, t.getService_image());
            ste.setInt(4, t.getNb_sous_services());
            ste.setInt(5, t.getService_id());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Services findById(int id) {
Services service = null;
    try {
        String sql = "SELECT * FROM services WHERE service_id=?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int serviceId = resultSet.getInt("service_id");
            String serviceNom = resultSet.getString("service_nom");
            String serviceDescription = resultSet.getString("service_description");
            String serviceImage = resultSet.getString("service_image");
            int nbSousServices = resultSet.getInt("nb_sous_services");
            service = new Services(serviceId, serviceNom, serviceDescription, serviceImage, nbSousServices);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return service;
    }

}
