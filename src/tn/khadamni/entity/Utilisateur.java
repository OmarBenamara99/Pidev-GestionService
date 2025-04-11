/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.entity;

/**
 *
 * @author lordg
 */

public class Utilisateur {
    private int id, numtel;
    private String nom, prenom, adresse;
     private String mdp;
    
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, int numtel, String adresse,String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.adresse = adresse;
        this.mdp=mdp;
    }

    public Utilisateur(int id, int numtel, String nom, String prenom, String adresse) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    

    public Utilisateur(int numtel, String nom, String prenom, String adresse, String role,String mdp) {
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.role = role;
        this.mdp =mdp;
    }

    public Utilisateur(int id, int numtel, String nom, String prenom, String adresse, String role,String mdp) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.role = role;
        this.mdp =mdp;
    }
     public Utilisateur(int id, int numtel, String nom, String prenom, String adresse, String role) {
        this.id = id;
        this.numtel = numtel;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.role = role;
       
    }
    

    public Utilisateur(Utilisateur u) {
       this.id = u.id;
        this.numtel = u.numtel;
        this.nom = u.nom;
        this.prenom = u.prenom;
        this.adresse = u.adresse;
        this.role = u.role;
        this.mdp =u.mdp;
    }
    
    public Utilisateur(String nom, String prenom, String adresse, int numtel, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.id=id;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "numtel=" + numtel + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + '}';
    }
    

    
}