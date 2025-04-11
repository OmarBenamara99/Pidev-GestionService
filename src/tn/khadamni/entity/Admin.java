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
public class Admin extends Utilisateur {
   

    public Admin(String nom, String prenom, int numtel, String adresse, String mdp) {
        super(nom, prenom, numtel, adresse,mdp);
       
    }
      public Admin (  int numtel, String nom, String prenom, String adresse, String role , String mdp){
          super( numtel, nom, prenom, adresse,role,mdp);
      }
    public Admin( int id, int numtel, String nom, String prenom, String adresse, String role , String mdp) {
        super(id, numtel, nom, prenom, adresse,role,mdp);
        
    }

   

   
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", numtel=" + getNumtel() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", adresse='" + getAdresse() + '\'' +
                
                '}';
    }
}
