package tn.khadamni.service;

import java.util.List;

public interface Interface <T>{
    
    public void ajouterOffre(T t);
    public List<T> getAllOffre();
    public void supprimerOffre(T t);
    public void modifierOffre(String nom,T t);
    //public List<T> findById(int id);
    
        
    
}
