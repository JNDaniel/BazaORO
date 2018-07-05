/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Daniel
 */
@Embeddable
public class ZamowienieProduktPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produkt_id")
    private int produktId;
    @Basic(optional = false)
    @Column(name = "zamowienie_id")
    private int zamowienieId;

    public ZamowienieProduktPK() {
    }

    public ZamowienieProduktPK(int produktId, int zamowienieId) {
        this.produktId = produktId;
        this.zamowienieId = zamowienieId;
    }

    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    public int getZamowienieId() {
        return zamowienieId;
    }

    public void setZamowienieId(int zamowienieId) {
        this.zamowienieId = zamowienieId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produktId;
        hash += (int) zamowienieId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZamowienieProduktPK)) {
            return false;
        }
        ZamowienieProduktPK other = (ZamowienieProduktPK) object;
        if (this.produktId != other.produktId) {
            return false;
        }
        if (this.zamowienieId != other.zamowienieId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testybazy.ZamowienieProduktPK[ produktId=" + produktId + ", zamowienieId=" + zamowienieId + " ]";
    }
    
}
