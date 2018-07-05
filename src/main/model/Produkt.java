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
import javax.persistence.JoinTable;
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
@Table(name = "produkt", catalog = "oro", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p")
    , @NamedQuery(name = "Produkt.findByProduktId", query = "SELECT p FROM Produkt p WHERE p.produktId = :produktId")
    , @NamedQuery(name = "Produkt.findByMarka", query = "SELECT p FROM Produkt p WHERE p.marka = :marka")
    , @NamedQuery(name = "Produkt.findByModel", query = "SELECT p FROM Produkt p WHERE p.model = :model")
    , @NamedQuery(name = "Produkt.findByNazwa", query = "SELECT p FROM Produkt p WHERE p.nazwa = :nazwa")})
public class Produkt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "produkt_id")
    private Integer produktId;
    @Column(name = "Marka")
    private String marka;
    @Column(name = "Model")
    private String model;
    @Basic(optional = false)
    @Column(name = "Nazwa")
    private String nazwa;
    @JoinTable(name = "produkt_sklep", joinColumns = {
        @JoinColumn(name = "ProduktID", referencedColumnName = "produkt_id")}, inverseJoinColumns = {
        @JoinColumn(name = "SklepID", referencedColumnName = "SklepID")})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Sklep> sklepList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkt", fetch = FetchType.EAGER)
    private List<ZamowienieProdukt> zamowienieProduktList = new ArrayList<>();

    public Produkt() {
    }

    public Produkt(Integer produktId) {
        this.produktId = produktId;
    }

    public Produkt(Integer produktId, String nazwa) {
        this.produktId = produktId;
        this.nazwa = nazwa;
    }

    public Integer getProduktId() {
        return produktId;
    }

    public void setProduktId(Integer produktId) {
        this.produktId = produktId;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public List<Sklep> getSklepList() {
        return sklepList;
    }

    public void setSklepList(List<Sklep> sklepList) {
        this.sklepList = sklepList;
    }

    public void addSklep(Sklep s)
    {
        s.getProduktList().add(this);
        this.getSklepList().add(s);
    }
    @XmlTransient
    public List<ZamowienieProdukt> getZamowienieProduktList() {
        return zamowienieProduktList;
    }

    public void setZamowienieProduktList(List<ZamowienieProdukt> zamowienieProduktList) {
        this.zamowienieProduktList = zamowienieProduktList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (produktId != null ? produktId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produkt)) {
            return false;
        }
        Produkt other = (Produkt) object;
        if ((this.produktId == null && other.produktId != null) || (this.produktId != null && !this.produktId.equals(other.produktId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testybazy.Produkt[ produktId=" + produktId + " ]";
    }
    
}
