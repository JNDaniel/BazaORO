/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "zamowienie", catalog = "oro", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zamowienie.findAll", query = "SELECT z FROM Zamowienie z")
    , @NamedQuery(name = "Zamowienie.findByZamowienieId", query = "SELECT z FROM Zamowienie z WHERE z.zamowienieId = :zamowienieId")})
public class Zamowienie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zamowienie_id")
    private Integer zamowienieId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zamowienie", fetch = FetchType.EAGER)
    private List<ZamowienieProdukt> zamowienieProduktList = new ArrayList<>();
    @JoinColumn(name = "Klient", referencedColumnName = "KlientID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Klient klient;
    @JoinColumn(name = "Sklep", referencedColumnName = "SklepID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Sklep sklep;

    public Zamowienie() {
    }

    public Zamowienie(Integer zamowienieId) {
        this.zamowienieId = zamowienieId;
    }

    public Integer getZamowienieId() {
        return zamowienieId;
    }

    public void setZamowienieId(Integer zamowienieId) {
        this.zamowienieId = zamowienieId;
    }

    @XmlTransient
    public List<ZamowienieProdukt> getZamowienieProduktList() {
        return zamowienieProduktList;
    }

    public void setZamowienieProduktList(List<ZamowienieProdukt> zamowienieProduktList) {
        this.zamowienieProduktList = zamowienieProduktList;
    }
    
    public void addProdukt(Produkt p,int ilosc)
    {
        ZamowienieProdukt zp = new ZamowienieProdukt();
        zp.setProdukt(p);
        zp.setZamowienie(this);
        zp.setIlosc(ilosc);
        ZamowienieProduktPK zpk = new ZamowienieProduktPK(p.getProduktId(),this.getZamowienieId());
        zp.setZamowienieProduktPK(zpk);
        getZamowienieProduktList().add(zp);
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Sklep getSklep() {
        return sklep;
    }

    public void setSklep(Sklep sklep) {
        this.sklep = sklep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zamowienieId != null ? zamowienieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowienie)) {
            return false;
        }
        Zamowienie other = (Zamowienie) object;
        if ((this.zamowienieId == null && other.zamowienieId != null) || (this.zamowienieId != null && !this.zamowienieId.equals(other.zamowienieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testybazy.Zamowienie[ zamowienieId=" + zamowienieId + " ]";
    }
    
}
