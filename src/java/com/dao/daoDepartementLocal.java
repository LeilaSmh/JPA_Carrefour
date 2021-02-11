package com.dao;

import com.model.Departement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leila
 */
@Local
public interface daoDepartementLocal {
    public void add(Departement E) ;
    public void update(Departement E) ;
    public Departement FindByCode(String Code);
    public void DeleteByCode(String Code);
    public List<Departement> findAll();
}
