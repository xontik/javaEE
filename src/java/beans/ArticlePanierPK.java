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
public class ArticlePanierPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idArticle")
    private int idArticle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPanier")
    private int idPanier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTaille")
    private int idTaille;

    public ArticlePanierPK() {
    }

    public ArticlePanierPK(int idArticle, int idPanier, int idTaille) {
        this.idArticle = idArticle;
        this.idPanier = idPanier;
        this.idTaille = idTaille;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
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
        hash += (int) idPanier;
        hash += (int) idTaille;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticlePanierPK)) {
            return false;
        }
        ArticlePanierPK other = (ArticlePanierPK) object;
        if (this.idArticle != other.idArticle) {
            return false;
        }
        if (this.idPanier != other.idPanier) {
            return false;
        }
        if (this.idTaille != other.idTaille) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.ArticlePanierPK[ idArticle=" + idArticle + ", idPanier=" + idPanier + ", idTaille=" + idTaille + " ]";
    }
    
}
