package tn.khadamni.entity;

import java.sql.Date;

public class Offre {

    private int id_offre, cat, Scat;
    private String adr, desc, img, service, Sservice;
    private Date date_offre;
    private int idC;
    private Utilisateur user;
    

    public Offre() {

    }
    
    public Offre(int id_offre) {
        this.id_offre=id_offre;
    }
    
    public Offre(int cat, int Scat, String adr, Date date_offre, String desc, String img) {
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.date_offre = date_offre;
        this.desc = desc;
        this.img = img;
        
    }
    
    public Offre(int cat, int Scat, String adr, Date date_offre, String desc, String img, String service, String Sservice) {
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.date_offre = date_offre;
        this.desc = desc;
        this.img = img;
        this.service=service;
        this.Sservice=Sservice;
    }
    
    public Offre(int cat, int Scat, String adr, Date date_offre, String desc, String img, String service, String Sservice, int idC) {
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.date_offre = date_offre;
        this.desc = desc;
        this.img = img;
        this.service=service;
        this.Sservice=Sservice;
        this.idC = idC;
    }
    
    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }
    
    public Offre(int cat, int Scat, String adr, String desc, String img) {
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.desc = desc;
        this.img = img;
        
    }

    public Offre(int id_offre, int cat, int Scat, String adr, Date date_offre, String desc, String img, String service, String Sservice) {
        this.id_offre = id_offre;
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.date_offre = date_offre;
        this.desc = desc;
        this.img = img;
        this.service=service;
        this.Sservice=Sservice;
    }
    
    public Offre(int id_offre, int cat, int Scat, String adr, Date date_offre, String desc, String img, String service, String Sservice, Utilisateur user) {
        this.id_offre = id_offre;
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.date_offre = date_offre;
        this.desc = desc;
        this.img = img;
        this.service=service;
        this.Sservice=Sservice;
        this.user=user;
    }
    
    public Offre(int id_offre, int cat, int Scat, String adr, Date date_offre, String desc, String img, String service, String Sservice, int idC, Utilisateur user) {
        this.id_offre = id_offre;
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.date_offre = date_offre;
        this.desc = desc;
        this.img = img;
        this.service=service;
        this.Sservice=Sservice;
        this.idC=idC;
        this.user=user;
    }
    
    public Offre(int id_offre, int cat, int Scat, String adr, String desc, String img) {
        this.id_offre = id_offre;
        this.cat = cat;
        this.Scat = Scat;
        this.adr = adr;
        this.desc = desc;
        this.img = img;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public int getScat() {
        return Scat;
    }

    public void setScat(int Scat) {
        this.Scat = Scat;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public Date getDate_offre() {
        return date_offre;
    }

    public void setDate_offre(Date date_offre) {
        this.date_offre = date_offre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
    public String getSservice() {
        return Sservice;
    }

    public void setSservice(String Sservice) {
        this.Sservice = Sservice;
    }
    /*
    @Override
    public String toString() {
        return "Offre{   " + "catégorie=   " + cat +"\t"+"  |  "+ " Sous_Catégorie=   " + Scat+"\t" +"  |  "+ " Adresse=   " + adr+"\t" +"  |  "+ " Date_Offre=   " + date_offre+"\t"  +"  |  "+" Description=   " + desc+"\t" +"  |  "+ " Image=   " + img+"\t" + '}';
    }
    */
    public Utilisateur getUser(){
        return user;
    }

    

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", cat=" + cat + ", Scat=" + Scat + ", adr=" + adr + ", desc=" + desc + ", img=" + img + ", service=" + service + ", Sservice=" + Sservice + ", date_offre=" + date_offre + user+'}';
    }
}
    