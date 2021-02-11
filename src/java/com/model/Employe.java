package com.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leila
 */
@Entity
@Table(name = "EMPLOYE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e"),
    @NamedQuery(name = "Employe.findByIdEmp", query = "SELECT e FROM Employe e WHERE e.idEmp = :idEmp"),
    @NamedQuery(name = "Employe.findByNom", query = "SELECT e FROM Employe e WHERE e.nom = :nom"),
    @NamedQuery(name = "Employe.findBySalaire", query = "SELECT e FROM Employe e WHERE e.salaire = :salaire"),
    @NamedQuery(name = "Employe.count", query = "SELECT COUNT(e) FROM Employe e WHERE e.refDept = :refDept")})
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMP")
    private String idEmp;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "SALAIRE")
    private Double salaire;
    @JoinColumn(name = "RefDept", referencedColumnName = "ID_DEPT")
    @ManyToOne
    private Departement refDept;

    public Employe() {
    }

   public Employe(String idEmp,String Name, Double Salaire){
        this.salaire = Salaire;
        this.nom = Name;
        this.idEmp = idEmp;
    }
   
    public String getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Departement getRefDept() {
        return refDept;
    }

    public void setRefDept(Departement refDept) {
        this.refDept = refDept;
    }
}