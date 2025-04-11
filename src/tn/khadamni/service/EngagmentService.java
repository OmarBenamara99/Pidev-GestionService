/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.khadamni.entity.Engagment;
import tn.khadamni.entity.Services;
import tn.khadamni.tools.MaConnection;

/**
 *
 * @author morta
 */
public class EngagmentService implements InterfaceService<Engagment> {

    Connection cnx;

    public EngagmentService() {
        cnx = MaConnection.getInstance().getcnx();

    }

    @Override
    public void ajouter(Engagment t) {
        try {
            String sql = "insert into engagement(nom,jour,date,heure,idF)values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setString(1, t.getNom());
            ste.setString(2, t.getJour());
            ste.setString(3, t.getDate());
            ste.setString(4, t.getHeure());
            ste.setInt(5, t.getIdF());

            ste.executeUpdate();
            System.out.println("engagment ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Engagment> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Engagment t) {
      
    }
     public void supprimer1(int idF) {
        String sql = "delete from engagement where idF=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,idF);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(String nom, Engagment t) {
    }

    @Override
    public Engagment findById(int idF) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
 
    }
 public List<Engagment> findById1(int idF) {
       
                List<Engagment> en = new ArrayList<>();

        try {
            String sql = "SELECT * FROM engagement WHERE idF=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, idF);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int engament_idE = resultSet.getInt("idE");
                String engament_nom = resultSet.getString("nom");
                String engament_jour = resultSet.getString("jour");
                String engament_date = resultSet.getString("date");
                String engament_heure = resultSet.getString("heure");
                int engament_idF = resultSet.getInt("idF");

             Engagment engagment = new Engagment(engament_idE, engament_nom, engament_jour, engament_date, engament_heure,engament_idF);
            en.add(engagment);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return en;
    }
}
