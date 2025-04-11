package tn.khadamni.entity;

public class Engagment {

    private int idF, idE;
    private String nom, jour, date, heure;

    public Engagment() {
    }

    public Engagment(int idE, String nom, String jour, String date, String heure, int idF) {
        this.idE = idE;
        this.heure = heure;
        this.nom = nom;
        this.jour = jour;
        this.date = date;
        this.idF = idF;

    }

    public Engagment(String nom, String jour, String date, String heure, int idF) {

        this.heure = heure;
        this.nom = nom;
        this.jour = jour;
        this.date = date;
        this.idF = idF;

    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Engagment{" + "idF=" + idF + ", idE=" + idE + ", nom=" + nom + ", jour=" + jour + ", date=" + date + ", heure=" + heure + '}';
    }

    public int getService_id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
