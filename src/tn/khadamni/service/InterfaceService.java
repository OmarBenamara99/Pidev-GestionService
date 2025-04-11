
package tn.khadamni.service;
import java.util.List;
public interface InterfaceService<T> {

public void ajouter(T t);
public List<T> afficher();
public void supprimer(T t);
public void modifier(String nom,T t);
public   T findById(int id);
    
}
