/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author p1603867
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
    @ManyToMany(mappedBy = "panierCollection")
    private Collection<Article> articleCollection;
    @JoinColumn(name = "idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client idClient;

    public Panier() {
    }

    public Panier(Integer idPanier) {
        this.idPanier = idPanier;
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

    @XmlTransient
    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
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
        return "JavaBean.Panier[ idPanier=" + idPanier + " ]";
    }
    
}
