/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.khadamni.entity.Reclamation;
import tn.khadamni.tools.MaConnection;

/**
 *
 * @author USER
 */
public class ReclamationService implements InterfaceService<Reclamation>{

    Connection cnx;

    public ReclamationService() {
        cnx = MaConnection.getInstance().getcnx();
    }
    
    @Override
    public void ajouter(Reclamation t) {
        try {
            String sql = "insert into reclamations(reclamation_titre,reclamation_subject,date_reclamation,utilisateur_id)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getTitle());
            ste.setString(2, t.getSubject());
            ste.setDate(3, (Date) t.getDate());
            ste.setInt(4, t.getIduser());
            ste.executeUpdate();
            System.out.println("Reclamation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() {
 List<Reclamation> reclamation = new ArrayList<>();
        try {
            String sql = "select * from reclamations";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Reclamation p = new Reclamation(s.getInt(1), s.getString("Title"),
                        s.getString("Subject"), s.getDate("date"));
                reclamation.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamation;
    }

    /*@Override
    public List<Reclamation> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    public void supprimer(Reclamation r) {
        String sql = "delete from reclamations where reclamation_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, r.getId());
            
            System.out.println(r.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void modifier(String title,String subject,Reclamation r) {
        String sql = "update reclamations set reclamation_titre=? , reclamation_subject=? where reclamation_id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, title);
            ste.setString(2, subject);
            //ste.setDate(4,d);
            ste.setInt(3,r.getId());
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public void ajout (Reclamation r){
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String title ,sub ,i;
        Scanner sc =new Scanner(System.in) ;
        System.out.println("chose option :"
                + "1-service..."
                + "2-service..."
                + "3-other");
        i=sc.nextLine() ;
        if(i.equals("1"))
            r.setTitle("serv");
        if(i.equals("2"))
            r.setTitle("service");
        if(i.equals("3")){
            System.out.println("what's your probleme about :");
        title=sc.nextLine() ;
        r.setTitle(title);
        }
        sub=sc.nextLine() ;
        r.setSubject(sub);
        
        r.setDate(date);
    
        try {
            String sql = "insert into reclamations(reclamation_titre,reclamation_subject,date_reclamation)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, r.getTitle());
            ste.setString(2, r.getSubject());
            ste.setDate(3, (Date) r.getDate());
            ste.executeUpdate();
            System.out.println("Reclamation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(String nom, Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reclamation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   

    
}
