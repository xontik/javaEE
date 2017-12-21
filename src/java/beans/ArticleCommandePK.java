/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author xontik
 */
@Embeddable
public class ArticleCommandePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idArticle")
    private int idArticle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCommande")
    private int idCommande;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTaille")
    private int idTaille;

    public ArticleCommandePK() {
    }

    public ArticleCommandePK(int idArticle, int idCommande, int idTaille) {
        this.idArticle = idArticle;
        this.idCommande = idCommande;
        this.idTaille = idTaille;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idArticle;
        hash += (int) idCommande;
        hash += (int) idTaille;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticleCommandePK)) {
            return false;
        }
        ArticleCommandePK other = (ArticleCommandePK) object;
        if (this.idArticle != other.idArticle) {
            return false;
        }
        if (this.idCommande != other.idCommande) {
            return false;
        }
        if (this.idTaille != other.idTaille) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ArticleCommandePK[ idArticle=" + idArticle + ", idCommande=" + idCommande + ", idTaille=" + idTaille + " ]";
    }
    
}
