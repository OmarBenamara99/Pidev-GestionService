/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.entity;
import java.time.LocalDateTime ;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 *
 * @author lordg
 */
public class Mysession {
    
    private int id;
    private String nom;
  private LocalDateTime date ;
 private LocalDateTime datef ;
 private String ipAddress;
    public Mysession(Utilisateur u) {
        this.id = u.getId();
        this.nom=u.getNom();
        this.date = LocalDateTime.now();
        this.ipAddress= getIPAddress();
    }
     public Mysession(Freelancer f) {
        this.id = f.getId();
        this.nom=f.getNom();
        this.date = LocalDateTime.now();
         this.ipAddress = getIPAddress();
    }
      public Mysession(Admin a) {
        this.id = a.getId();
        this.nom=a.getNom();
        this.date = LocalDateTime.now();
         this.ipAddress = getIPAddress();
    }
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDatef() {
        return datef;
    }

    public void setDatef(LocalDateTime datef) {
        this.datef = datef;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    @Override
    public String toString() {
        return "Connection{" + "nom=" + nom + ", date=" + date + ", datef=" + datef + '}';
    }
    private String getIPAddress() {
        String ipAddress = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException ex) {
            System.out.println("Error getting IP address: " + ex.getMessage());
        }
        return ipAddress;
    }
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
