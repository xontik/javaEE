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
@Table(name = "ArticleCommande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArticleCommande.findAll", query = "SELECT a FROM ArticleCommande a")
    , @NamedQuery(name = "ArticleCommande.findByIdArticle", query = "SELECT a FROM ArticleCommande a WHERE a.articleCommandePK.idArticle = :idArticle")
    , @NamedQuery(name = "ArticleCommande.findByIdCommande", query = "SELECT a FROM ArticleCommande a WHERE a.articleCommandePK.idCommande = :idCommande")
    , @NamedQuery(name = "ArticleCommande.findByIdTaille", query = "SELECT a FROM ArticleCommande a WHERE a.articleCommandePK.idTaille = :idTaille")})
public class ArticleCommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArticleCommandePK articleCommandePK;
    @JoinColumn(name = "idTaille", referencedColumnName = "idTaille", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Taille taille;
    @JoinColumn(name = "idArticle", referencedColumnName = "idArticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "idCommande", referencedColumnName = "idCommande", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commande commande;

    public ArticleCommande() {
    }

    public ArticleCommande(ArticleCommandePK articleCommandePK) {
        this.articleCommandePK = articleCommandePK;
    }

    public ArticleCommande(int idArticle, int idCommande, int idTaille) {
        this.articleCommandePK = new ArticleCommandePK(idArticle, idCommande, idTaille);
    }

    public ArticleCommandePK getArticleCommandePK() {
        return articleCommandePK;
    }

    public void setArticleCommandePK(ArticleCommandePK articleCommandePK) {
        this.articleCommandePK = articleCommandePK;
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

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleCommandePK != null ? articleCommandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticleCommande)) {
            return false;
        }
        ArticleCommande other = (ArticleCommande) object;
        if ((this.articleCommandePK == null && other.articleCommandePK != null) || (this.articleCommandePK != null && !this.articleCommandePK.equals(other.articleCommandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ArticleCommande[ articleCommandePK=" + articleCommandePK + " ]";
    }
    
}
