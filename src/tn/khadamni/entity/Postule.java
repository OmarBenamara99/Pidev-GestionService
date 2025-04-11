package tn.khadamni.entity;

import java.sql.Date;

public class Postule {
    private int Postulation_id,Offre_id,Freelancer_id;
    private Date Postulation_date;
    private Utilisateur user;

    public Postule(int Offre_id, int Freelancer_id, Date Postulation_date) {
        this.Offre_id = Offre_id;
        this.Freelancer_id = Freelancer_id;
        this.Postulation_date = Postulation_date;
    }
    
    public Postule(int Postulation_id, int Offre_id, Utilisateur user, Date Postulation_date) {
        this.Postulation_id=Postulation_id;
        this.Offre_id = Offre_id;
        this.user = user;
        this.Postulation_date = Postulation_date;
    }

    @Override
    public String toString() {
        return "Postule{" + "Postulation_id=" + Postulation_id + ", Offre_id=" + Offre_id + ", Freelancer_id=" + user + ", Postulation_date=" + Postulation_date + '}';
    }

    public Postule(int Postulation_id, int Offre_id, int Freelancer_id, Date Postulation_date) {
        this.Postulation_id = Postulation_id;
        this.Offre_id = Offre_id;
        this.Freelancer_id = Freelancer_id;
        this.Postulation_date = Postulation_date;
    }

    public int getPostulation_id() {
        return Postulation_id;
    }

    public void setPostulation_id(int Postulation_id) {
        this.Postulation_id = Postulation_id;
    }

    public int getOffre_id() {
        return Offre_id;
    }

    public void setOffre_id(int Offre_id) {
        this.Offre_id = Offre_id;
    }

    public int getFreelancer_id() {
        return Freelancer_id;
    }

    public void setFreelancer_id(int Freelancer_id) {
        this.Freelancer_id = Freelancer_id;
    }

    public Date getPostulation_date() {
        return Postulation_date;
    }

    public void setPostulation_date(Date Postulation_date) {
        this.Postulation_date = Postulation_date;
    }
    
    public Utilisateur getUser() {
        return user;
    }
    
    
}
