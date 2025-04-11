/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.tools;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author morta
 */
public class MaConnection {
 
     private Connection cnx;
        String url="jdbc:mysql://localhost:3306/khadamnidb";
        String user="root";
        String pwd="";
        public static MaConnection ct;
        
        private MaConnection()
        {
  
                  try {

        cnx=DriverManager.getConnection(url, user, pwd);
  }
        catch (Exception e) {
            
            System.out.println(e.getMessage());
        
        }
        }
        public static MaConnection getInstance ()
        {
            if(ct==null)
                ct=new MaConnection();
            
            return ct;
        }
        public  Connection getcnx()
        {
            return cnx;
        }
  
}
