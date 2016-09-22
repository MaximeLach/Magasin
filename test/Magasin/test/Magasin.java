/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magasin.test;

import Magasin.entity.Categorie;
import Magasin.entity.Client;
import Magasin.entity.Commande;
import Magasin.entity.Produit;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author admin
 */
public class Magasin {
    
    @Before
    public void avant(){
        //Vide toutes les tables
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Commande c").executeUpdate();
        em.createQuery("DELETE FROM Client c").executeUpdate();
        em.createQuery("DELETE FROM Produit p").executeUpdate();
        em.createQuery("DELETE FROM Categorie c").executeUpdate();
        
        //Ajoute des donnée en spécifiant les IDs que l'on va récup ds les tests unitaires
        Client riri = new Client();
        riri.setId(1L);
        riri.setNom("Riri");
        em.persist(riri);
        
        Client fifi = new Client();
        fifi.setId((long)2);
        fifi.setNom("Fifi");
        em.persist(fifi);
        
        Client loulou = new Client();
        loulou.setId((long)3);
        loulou.setNom("Loulou");
        em.persist(loulou);

        Commande cmd1 = new Commande();
        cmd1.setId((long)1);
        cmd1.setClient(riri);
        cmd1.setPrixTotal(1000);
        riri.getCommandes().add(cmd1);
        em.persist(cmd1);
        
        Commande cmd2 = new Commande();
        cmd2.setId((long)2);
        cmd2.setClient(loulou);
        cmd2.setPrixTotal(5);
        loulou.getCommandes().add(cmd2);
        em.persist(cmd2);
        
        Commande cmd3 = new Commande();
        cmd3.setId((long)3);
        cmd3.setClient(loulou);
        cmd3.setPrixTotal(2);
        loulou.getCommandes().add(cmd3);
        em.persist(cmd3);
        
        em.getTransaction().commit();
    }
    
    @Test 
    public void verifQueNbrCmdLoulou2(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Client loulou = em.find(Client.class, 3L);
        if(loulou.getCommandes().size() != 2)
            Assert.fail("Loulou n'a pas ;2 commandes");
        else System.out.println("Loulou a 2 commandes");
            
    }
    
    //@Test 
    public void verifQueCmd3PasseParLoulou(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Commande cmd = em.find(Commande.class, 3L);
        Assert.assertEquals("Loulou", cmd.getClient().getNom());
//        Methode 2
//        Client loulou = em.find(Client.class, 3L);
//        Commande cmd = em.find(Commande.class, 3L);
//        if(cmd.getClient() == loulou)
//            System.out.println("La commande 3 appartient à Loulou");
//        else fail("La commande 3 n'appartient pas à Loulou");
    }
    
    //@Test 
    public void verifQueCmd2PasseParRiri(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Commande cmd = em.find(Commande.class, 2L);
        Assert.assertNotEquals("Riri", cmd.getClient().getNom());
    }
    
    
    //@Test
    public void testListeCommandeClient(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Client c = em.find(Client.class, (long)2); 
        
        for(Commande cmd : c.getCommandes()){
            System.out.println(c.getNom() + " : " + cmd.getId());
        }
    }
    
    
    //@Test
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
