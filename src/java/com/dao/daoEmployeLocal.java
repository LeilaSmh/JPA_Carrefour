package com.dao;

import com.model.Employe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leila
 */
@Local
public interface daoEmployeLocal {
    public void addEmploye(Employe e);
    public void updateEmploye(Employe e);
    public void deleteEmploye(String code);
    public Employe findByCode(String code);
    public List<Employe> findAll();
    public List<Employe> EmpByDept(String refDept);
    public void DelByDept (String code);
    public int count (String refDept);
}
