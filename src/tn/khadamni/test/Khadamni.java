package tn.khadamni.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.service.ServicesService;
import tn.khadamni.service.Sous_servicesService;
import tn.khadamni.tools.MaConnection;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tn.khadamni.entity.Admin;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.entity.Reclamation;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.service.AdminService;
import tn.khadamni.service.FreelancerService;
import tn.khadamni.service.ReclamationService;
import tn.khadamni.service.UtilisateurService;

public class Khadamni {

    public static void main(String[] args) {

   ServicesService ps = new ServicesService();
//Services p1 = new Services("test", "chabchoub", "karim",1);
//ps.ajouter(p1); // ajoute le service à la base de données
//int id = p1.getService_id(); // récupère l'ID attribué par la base de données
//p1.setService_nom("nouveau_nom"); // modifie le nom du service
//ps.modifier("mortadha", p1); // modifie le service dans la base de données
//System.out.println(ps.afficher());
//ps.supprimer(p1);
 AdminService as = new AdminService();
 Admin a = new Admin("gaddour", "ghiloufi", 52199977, "lord.gaddour.99@gmail.com", "12345678");
 //as.ajouter(a);
    }
}
