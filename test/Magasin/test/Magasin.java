/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magasin.test;

import Magasin.entity.Categorie;
import Magasin.entity.Produit;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class Magasin {
    
    @Test
    public void testListeProdCategorie(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Categorie cat = em.find(Categorie.class, (long)2);
        for(Produit p : cat.getProduits()){
            System.out.println(p.getDescription());
        }
    }
   
    
    //@Test
    public void MagasinTest() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        em.getTransaction().begin();
        Categorie c1 =new Categorie();
        c1.setNom("Basket");
        em.persist(c1);
        
        Categorie c2 =new Categorie();
        c2.setNom("Lunettes solaires");
        em.persist(c2);
        
        Produit rayBan = new Produit();
        rayBan.setCategorie(c2);
        em.persist(rayBan);
        
        
        em.getTransaction().commit();
    }
    
}
