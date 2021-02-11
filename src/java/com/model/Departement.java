package com.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leila
 */
@Entity
@Table(name = "DEPARTEMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departement.findAll", query = "SELECT d FROM Departement d"),
    @NamedQuery(name = "Departement.findByIdDept", query = "SELECT d FROM Departement d WHERE d.idDept = :idDept"),
    @NamedQuery(name = "Departement.findByNom", query = "SELECT d FROM Departement d WHERE d.nom = :nom")})

public class Departement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DEPT")
    private String idDept;
    @Column(name = "NOM")
    private String nom;
    @OneToMany(mappedBy = "refDept")
    private Collection<Employe> employeCollection;

    public Departement() {
    }

    public Departement(String idDept) {
        this.idDept = idDept;
    }
    
    public Departement(String idDept, String nom) {
        this.idDept = idDept;
        this.nom = nom;
    }

    public String getIdDept() {
        return idDept;
    }

    public void setIdDept(String idDept) {
        this.idDept = idDept;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Employe> getEmployeCollection() {
        return employeCollection;
    }

    public void setEmployeCollection(Collection<Employe> employeCollection) {
        this.employeCollection = employeCollection;
    }
}
