/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "adres", catalog = "oro", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adres.findAll", query = "SELECT a FROM Adres a")
    , @NamedQuery(name = "Adres.findByAdresID", query = "SELECT a FROM Adres a WHERE a.adresID = :adresID")
    , @NamedQuery(name = "Adres.findByMiasto", query = "SELECT a FROM Adres a WHERE a.miasto = :miasto")
    , @NamedQuery(name = "Adres.findByNrDomu", query = "SELECT a FROM Adres a WHERE a.nrDomu = :nrDomu")
    , @NamedQuery(name = "Adres.findByUlica", query = "SELECT a FROM Adres a WHERE a.ulica = :ulica")
    , @NamedQuery(name = "Adres.findByZip", query = "SELECT a FROM Adres a WHERE a.zip = :zip")})
public class Adres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AdresID")
    private Integer adresID;
    @Basic(optional = false)
    @Column(name = "Miasto")
    private String miasto;
    @Basic(optional = false)
    @Column(name = "NrDomu")
    private String nrDomu;
    @Basic(optional = false)
    @Column(name = "Ulica")
    private String ulica;
    @Basic(optional = false)
    @Column(name = "Zip")
    private String zip;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "adres", fetch = FetchType.EAGER)
    private Klient klient;

    public Adres() {
    }

    public Adres(Integer adresID) {
        this.adresID = adresID;
    }

    public Adres(Integer adresID, String miasto, String nrDomu, String ulica, String zip) {
        this.adresID = adresID;
        this.miasto = miasto;
        this.nrDomu = nrDomu;
        this.ulica = ulica;
        this.zip = zip;
    }

    public Integer getAdresID() {
        return adresID;
    }

    public void setAdresID(Integer adresID) {
        this.adresID = adresID;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adresID != null ? adresID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adres)) {
            return false;
        }
        Adres other = (Adres) object;
        if ((this.adresID == null && other.adresID != null) || (this.adresID != null && !this.adresID.equals(other.adresID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "testybazy.Adres[ adresID=" + adresID + " ]";
    }
    
}
