/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Magasin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author admin
 */
@Entity
public class Commande implements Serializable {

    public enum Statut{
        EN_COURS,
        TERMINEE,
        PAYEE,
        LIVREE
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Embedded
    private Adresse adresse;
    private String moyenDePaiement;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Column(precision = 2)
    private float prixTotal;
    @Column(precision = 2)
    private float fraisLivraison;
    @Column(precision = 2)
    private float fraisPort;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEtHeureCommande;
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    
    @ManyToOne//definit type de jointure
    @JoinColumn(name = "client_id")//Crée une clé étrangère
    private Client client;
    
    @ManyToMany
    @JoinTable(name = "commande_produit")
    private List<Produit> produits = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Magasin.entity.Commande[ id=" + id + " ]";
    }
    
}
