package com.dao;

import com.model.Departement;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author leila
 */
@Stateless
public class daoDepartement implements daoDepartementLocal{
    @PersistenceUnit
            EntityManagerFactory emf;
    @PersistenceContext
            private EntityManager em;
    
    public daoDepartement() {
        emf = Persistence.createEntityManagerFactory("CarrefourPU");
        em = emf.createEntityManager();
    }
     
   @Override
     public void add(Departement E) {
            em.persist(E);
    }
    
   @Override
    public void update(Departement E) {
        em.merge(E);
    }
    
   @Override
    public void DeleteByCode(String Code){
        Departement dept = em.find(Departement.class,Code);
        em.remove(dept);  
    }
    
    @Override
    public Departement FindByCode(String Code){
        return em.find(Departement.class, Code);
    }
    
    @Override
    public List<Departement> findAll() {
        return em.createNamedQuery("Departement.findAll").getResultList();
    }
    
}
