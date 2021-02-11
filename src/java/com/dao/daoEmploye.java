package com.dao;

import com.model.Employe;
import com.model.Departement;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author leila
 */
@Stateless
public class daoEmploye implements daoEmployeLocal {
    @PersistenceUnit
            EntityManagerFactory emf;
    @PersistenceContext
            private EntityManager em;
    
    public daoEmploye(){
        emf = Persistence.createEntityManagerFactory("CarrefourPU");
        em = emf.createEntityManager();
    }
    
 @Override
    public void addEmploye(Employe e){
        em.persist(e);
    }
    
 @Override
    public void updateEmploye(Employe e) {
        em.merge(e);
    }

 @Override
    public void deleteEmploye(String code) {
        Employe e = em.find(Employe.class,code);
        em.remove(e);  
    }
    
 @Override
    public Employe findByCode(String code){
        return em.find(Employe.class, code);
    }
 @Override
    public List<Employe> findAll() {
        return em.createNamedQuery("Employe.findAll").getResultList();
    }
    
 @Override
    public List<Employe> EmpByDept(String code) {
        Departement dept = em.find(Departement.class, code);
        Query req=null ;
        if(dept!=null) 
        {
           req = em.createQuery(" select d from Employe d where d.refDept=:dept");
            req.setParameter("dept", dept);

           return req.getResultList();
        }
        return null;
    }
    
    @Override
    public void DelByDept (String code){
        List<Employe> list = EmpByDept(code);
        for (int i = 0; i<list.size(); i++){
            deleteEmploye(list.get(i).getIdEmp());
        }
    }
    
    @Override
    public int count (String refDept){
        Query query = em.createNamedQuery("Employe.count");
        query.setParameter("refDept", refDept);
        int count = ((Number)query.getSingleResult()).intValue();
        System.out.println(count);
        return count;
    }
}
