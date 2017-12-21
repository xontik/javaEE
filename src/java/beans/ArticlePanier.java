/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author xontik
 */
@Entity
@Table(name = "ArticlePanier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticlePanier.findAll", query = "SELECT a FROM ArticlePanier a")
    , @NamedQuery(name = "ArticlePanier.findByIdArticle", query = "SELECT a FROM ArticlePanier a WHERE a.articlePanierPK.idArticle = :idArticle")
    , @NamedQuery(name = "ArticlePanier.findByIdPanier", query = "SELECT a FROM ArticlePanier a WHERE a.articlePanierPK.idPanier = :idPanier")
    , @NamedQuery(name = "ArticlePanier.findByIdTaille", query = "SELECT a FROM ArticlePanier a WHERE a.articlePanierPK.idTaille = :idTaille")})
public class ArticlePanier implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArticlePanierPK articlePanierPK;
    @JoinColumn(name = "idTaille", referencedColumnName = "idTaille", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Taille taille;
    @JoinColumn(name = "idArticle", referencedColumnName = "idArticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "idPanier", referencedColumnName = "idPanier", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Panier panier;

    public ArticlePanier() {
    }

    public ArticlePanier(ArticlePanierPK articlePanierPK) {
        this.articlePanierPK = articlePanierPK;
    }

    public ArticlePanier(int idArticle, int idPanier, int idTaille) {
        this.articlePanierPK = new ArticlePanierPK(idArticle, idPanier, idTaille);
    }

    public ArticlePanierPK getArticlePanierPK() {
        return articlePanierPK;
    }

    public void setArticlePanierPK(ArticlePanierPK articlePanierPK) {
        this.articlePanierPK = articlePanierPK;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articlePanierPK != null ? articlePanierPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticlePanier)) {
            return false;
        }
        ArticlePanier other = (ArticlePanier) object;
        if ((this.articlePanierPK == null && other.articlePanierPK != null) || (this.articlePanierPK != null && !this.articlePanierPK.equals(other.articlePanierPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ArticlePanier[ articlePanierPK=" + articlePanierPK + " ]";
    }
    
}
