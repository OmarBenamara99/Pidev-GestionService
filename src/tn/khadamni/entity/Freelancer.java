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
public class Freelancer extends Utilisateur {
    
    private String photo;
    private String description;
    private int rate;
    private String profession;
    private boolean verified;
    

   

    public Freelancer(String adresse, String photo, String description, int rate, String profession, boolean verified, int id, int numtel, String nom, String prenom) {
        super(id, numtel, nom, prenom,adresse);
       
        this.photo = photo;
        this.description = description;
        this.rate = rate;
        this.profession = profession;
        this.verified = verified;
    }
    

    public Freelancer(String nom, String prenom, int numtel, String adresse, String photo, String description, int rate, String profession, boolean verified,String mdp) {
        super(nom, prenom, numtel, adresse,mdp);
       
        this.photo = photo;
        this.description = description;
        this.rate = rate;
        this.profession = profession;
        this.verified = verified;
    }
    public Freelancer(int id, int numtel, String nom, String prenom, String adresse, String role, String photo, String d, int r, String p, boolean v,String mdp) {
    super(id, numtel, nom, prenom, adresse, role,mdp);

    this.photo = photo;
    this.description = d;
    this.rate = r;
    this.profession = p;
    this.verified = v;
}
    
    
    @Override
    public int getId(){
        return super.getId();
    }
    
   

    
    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getRate() {
        return rate;
    }
    
    public void setRate(int rate) {
        this.rate = rate;
    }
    
    public String getProfession() {
        return profession;
    }
    
    public void setProfession(String profession) {
        this.profession = profession;
    }
    
    public boolean isVerified() {
        return verified;
    }
    
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "Freelancer{" + "photo=" + photo + ", description=" + description + ", rate=" + rate + ", profession=" + profession + ", verified=" + verified + '}';
    }
     
    
}