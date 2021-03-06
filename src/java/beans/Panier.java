/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xontik
 */
@Entity
@Table(name = "Panier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Panier.findAll", query = "SELECT p FROM Panier p")
    , @NamedQuery(name = "Panier.findByIdPanier", query = "SELECT p FROM Panier p WHERE p.idPanier = :idPanier")
    , @NamedQuery(name = "Panier.findByTokenPanier", query = "SELECT p FROM Panier p WHERE p.tokenPanier = :tokenPanier")})
public class Panier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPanier")
    private Integer idPanier;
    @Size(max = 50)
    @Column(name = "tokenPanier")
    private String tokenPanier;
    private Client idClient;
    private int etat;
    private Date dateCommande;    
    private Date dateLivraison;
    private boolean livre;

    public Panier() {
    }

    public Panier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public void setLivre() {
        if(dateLivraison != null && dateCommande != null){
            if(dateLivraison.before(new Date())){
                this.livre = true;
            }else{
                        this.livre = false;
            }
        } else {
                    this.livre = false;
        }
        
    }
    public boolean getLivre(){
        return this.livre;
    }
    public int getEtat(){
        return this.etat;
    }
    
    public void setEtat(int etat){
        this.etat = etat;
    }
    public Date getDateLivraison(){
        return this.dateLivraison;
    }
    
    public void setDateLivraison(Date dateLivraison){
        this.dateLivraison = dateLivraison;
    }
    public Date getDateCommande(){
        return this.dateCommande;
    }
    
    public void setDateCommande(Date dateCommande){
        this.dateCommande = dateCommande;
    }

    public Integer getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public String getTokenPanier() {
        return tokenPanier;
    }

    public void setTokenPanier(String tokenPanier) {
        this.tokenPanier = tokenPanier;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPanier != null ? idPanier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Panier)) {
            return false;
        }
        Panier other = (Panier) object;
        if ((this.idPanier == null && other.idPanier != null) || (this.idPanier != null && !this.idPanier.equals(other.idPanier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Panier[ idPanier=" + idPanier + " ]";
    }
    
}
