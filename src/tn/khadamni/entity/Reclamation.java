/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.entity;

/**
 *
 * @author USER
 */
import java.sql.Date;


public class Reclamation {
    private int id ;
    private String title,subject ;
    private Date date ;
    private int iduser ;

    public Reclamation() {
    }

    public Reclamation(int id, String title, String subject, Date date, int iduser) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.date = date;
        this.iduser = iduser;
    }

    public Reclamation(String title, String subject, Date date) {
        this.title = title;
        this.subject = subject;
        this.date = date;
    }

    public Reclamation( String title, String subject, Date date, int iduser) {
        
        this.title = title;
        this.subject = subject;
        this.date = date;
        this.iduser = iduser;
    }

    public Reclamation(int id, String title, String subject, Date date) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.date = date;
    }

    public Reclamation(String title, String subject) {
        this.title = title;
        this.subject = subject;
    }

  

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", title=" + title + ", subject=" + subject + ", date=" + date + '}';
    }
    
    
    
    
}
