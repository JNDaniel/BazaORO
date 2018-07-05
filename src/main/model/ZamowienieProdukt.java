/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "zamowienie_produkt", catalog = "oro", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZamowienieProdukt.findAll", query = "SELECT z FROM ZamowienieProdukt z")
    , @NamedQuery(name = "ZamowienieProdukt.findByProduktId", query = "SELECT z FROM ZamowienieProdukt z WHERE z.zamowienieProduktPK.produktId = :produktId")
    , @NamedQuery(name = "ZamowienieProdukt.findByZamowienieId", query = "SELECT z FROM ZamowienieProdukt z WHERE z.zamowienieProduktPK.zamowienieId = :zamowienieId")
    , @NamedQuery(name = "ZamowienieProdukt.findByIlosc", query = "SELECT z FROM ZamowienieProdukt z WHERE z.ilosc = :ilosc")})
public class ZamowienieProdukt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZamowienieProduktPK zamowienieProduktPK;
    @Column(name = "ilosc")
    private Integer ilosc;
    @JoinColumn(name = "zamowienie_id", referencedColumnName = "zamowienie_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Zamowienie zamowienie;
    @JoinColumn(name = "produkt_id", referencedColumnName = "produkt_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Produkt produkt;

    public ZamowienieProdukt() {
    }

    public ZamowienieProdukt(ZamowienieProduktPK zamowienieProduktPK) {
        this.zamowienieProduktPK = zamowienieProduktPK;
    }

    public ZamowienieProdukt(int produktId, int zamowienieId) {
        this.zamowienieProduktPK = new ZamowienieProduktPK(produktId, zamowienieId);
    }

    public ZamowienieProduktPK getZamowienieProduktPK() {
        return zamowienieProduktPK;
    }

    public void setZamowienieProduktPK(ZamowienieProduktPK zamowienieProduktPK) {
        this.zamowienieProduktPK = zamowienieProduktPK;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zamowienieProduktPK != null ? zamowienieProduktPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZamowienieProdukt)) {
            return false;
        }
        ZamowienieProdukt other = (ZamowienieProdukt) object;
        if ((this.zamowienieProduktPK == null && other.zamowienieProduktPK != null) || (this.zamowienieProduktPK != null && !this.zamowienieProduktPK.equals(other.zamowienieProduktPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testybazy.ZamowienieProdukt[ zamowienieProduktPK=" + zamowienieProduktPK + " ]";
    }
    
}
