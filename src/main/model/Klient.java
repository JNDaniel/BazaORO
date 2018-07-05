/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "klient", catalog = "oro", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klient.findAll", query = "SELECT k FROM Klient k")
    , @NamedQuery(name = "Klient.findByKlientID", query = "SELECT k FROM Klient k WHERE k.klientID = :klientID")
    , @NamedQuery(name = "Klient.findByImie", query = "SELECT k FROM Klient k WHERE k.imie = :imie")
    , @NamedQuery(name = "Klient.findByNazwisko", query = "SELECT k FROM Klient k WHERE k.nazwisko = :nazwisko")})
public class Klient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KlientID")
    private Integer klientID;
    @Basic(optional = false)
    @Column(name = "Imie")
    private String imie;
    @Basic(optional = false)
    @Column(name = "Nazwisko")
    private String nazwisko;
    @OneToMany(mappedBy = "klient", fetch = FetchType.EAGER)
    private List<Zamowienie> zamowienieList;
    @JoinColumn(name = "Adres", referencedColumnName = "AdresID")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Adres adres;

    public Klient() {
    }

    public Klient(Integer klientID) {
        this.klientID = klientID;
    }

    public Klient(Integer klientID, String imie, String nazwisko) {
        this.klientID = klientID;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Integer getKlientID() {
        return klientID;
    }

    public void setKlientID(Integer klientID) {
        this.klientID = klientID;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @XmlTransient
    public List<Zamowienie> getZamowienieList() {
        return zamowienieList;
    }

    public void setZamowienieList(List<Zamowienie> zamowienieList) {
        this.zamowienieList = zamowienieList;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (klientID != null ? klientID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Klient)) {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.klientID == null && other.klientID != null) || (this.klientID != null && !this.klientID.equals(other.klientID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testybazy.Klient[ klientID=" + klientID + " ]";
    }
    
}
