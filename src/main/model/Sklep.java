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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "sklep", catalog = "oro", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sklep.findAll", query = "SELECT s FROM Sklep s")
    , @NamedQuery(name = "Sklep.findBySklepID", query = "SELECT s FROM Sklep s WHERE s.sklepID = :sklepID")
    , @NamedQuery(name = "Sklep.findByNazwa", query = "SELECT s FROM Sklep s WHERE s.nazwa = :nazwa")})
public class Sklep implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SklepID")
    private Integer sklepID;
    @Basic(optional = false)
    @Column(name = "Nazwa",unique = true)
    private String nazwa;
    @ManyToMany(mappedBy = "sklepList", fetch = FetchType.EAGER)
    private List<Produkt> produktList = new ArrayList<>();
    @OneToMany(mappedBy = "sklep", fetch = FetchType.EAGER)
    private List<Zamowienie> zamowienieList;

    public Sklep() {
    }

    public Sklep(Integer sklepID) {
        this.sklepID = sklepID;
    }

    public Sklep(Integer sklepID, String nazwa) {
        this.sklepID = sklepID;
        this.nazwa = nazwa;
    }

    public Integer getSklepID() {
        return sklepID;
    }

    public void setSklepID(Integer sklepID) {
        this.sklepID = sklepID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public List<Produkt> getProduktList() {
        return produktList;
    }

    public void setProduktList(List<Produkt> produktList) {
        this.produktList = produktList;
    }

    @XmlTransient
    public List<Zamowienie> getZamowienieList() {
        return zamowienieList;
    }

    public void setZamowienieList(List<Zamowienie> zamowienieList) {
        this.zamowienieList = zamowienieList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sklepID != null ? sklepID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sklep)) {
            return false;
        }
        Sklep other = (Sklep) object;
        if ((this.sklepID == null && other.sklepID != null) || (this.sklepID != null && !this.sklepID.equals(other.sklepID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testybazy.Sklep[ sklepID=" + sklepID + " ]";
    }
    
}
