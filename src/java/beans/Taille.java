/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author xontik
 */
@Entity
@Table(name = "Taille")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taille.findAll", query = "SELECT t FROM Taille t")
    , @NamedQuery(name = "Taille.findByIdTaille", query = "SELECT t FROM Taille t WHERE t.idTaille = :idTaille")
    , @NamedQuery(name = "Taille.findByNom", query = "SELECT t FROM Taille t WHERE t.code = :code")})
public class Taille implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTaille")
    private Integer idTaille;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "code")
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taille")
    private Collection<ArticleCommande> articleCommandeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taille")
    private Collection<ArticlePanier> articlePanierCollection;

    public Taille() {
    }

    public Taille(Integer idTaille) {
        this.idTaille = idTaille;
    }

    public Taille(Integer idTaille, String code) {
        this.idTaille = idTaille;
        this.code = code;
    }

    public Integer getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(Integer idTaille) {
        this.idTaille = idTaille;
    }

    public String getNom() {
        return code;
    }

    public void setNom(String code) {
        this.code = code;
    }

    @XmlTransient
    public Collection<ArticleCommande> getArticleCommandeCollection() {
        return articleCommandeCollection;
    }

    public void setArticleCommandeCollection(Collection<ArticleCommande> articleCommandeCollection) {
        this.articleCommandeCollection = articleCommandeCollection;
    }

    @XmlTransient
    public Collection<ArticlePanier> getArticlePanierCollection() {
        return articlePanierCollection;
    }

    public void setArticlePanierCollection(Collection<ArticlePanier> articlePanierCollection) {
        this.articlePanierCollection = articlePanierCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaille != null ? idTaille.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taille)) {
            return false;
        }
        Taille other = (Taille) object;
        if ((this.idTaille == null && other.idTaille != null) || (this.idTaille != null && !this.idTaille.equals(other.idTaille))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Taille[ idTaille=" + idTaille + " ]";
    }
    
}
