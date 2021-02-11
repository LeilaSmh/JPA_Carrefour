package com.controller;

import com.model.Departement;
import com.dao.daoDepartementLocal;
import com.dao.daoEmployeLocal;
import com.model.Employe;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leila
 */
public class Controller extends HttpServlet {

     @EJB private daoDepartementLocal daoDept;
     @EJB private daoEmployeLocal daoEmp; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        //System.out.println(action);
        if(action.equalsIgnoreCase("Departements")){
            this.Departements(request, response);
        }else if(action.equalsIgnoreCase("addEmp")){
            this.addEmploye(request, response);
        }else if(action.equalsIgnoreCase("saveEmp")){
            this.saveEmploye(request,response);
        }else if(action.equalsIgnoreCase("employes")){
            this.Employes(request,response);
        }else if(action.equalsIgnoreCase("empsByDept")) {
            this.EmpsByDept(request, response);
        }else if(action.equalsIgnoreCase("addDept")){
            this.AddDepartement(request,response);
        }else if(action.equalsIgnoreCase("saveDept")){
            this.SaveDepartement(request,response);
        }else if(action.equalsIgnoreCase("editDept")){
            this.EditDepartement(request,response);
        }else if(action.equalsIgnoreCase("saveDeptEdit")){
            this.SaveEditDepartement(request,response);
        }else if(action.equalsIgnoreCase("deleteDept")){
            this.DeleteDepartement(request,response);
        }else if(action.equalsIgnoreCase("editEmp")){
            this.EditEmploye(request,response);
        }else if(action.equalsIgnoreCase("saveEmpEdit")){
            this.SaveEditEmploye(request,response);
        }else if(action.equalsIgnoreCase("deleteEmp")){
            this.DeleteEmploye(request,response);
        }
        else{
            request.getRequestDispatcher("departements.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void Departements(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Departement> list = daoDept.findAll();
        Departement dept = new Departement();
        for(int i = 0 ; i<list.size() ; i++){
            
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("departements.jsp").forward(request, response);
    }
    
    private void Employes(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Employe> list = daoEmp.findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("employes.jsp").forward(request, response);
    }
    
    private void addEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idDept = request.getParameter("idDept");
        Departement dept = new Departement();
        if(idDept != null){
            dept = daoDept.FindByCode(idDept);
        }
        request.setAttribute("dept", dept);
        request.getRequestDispatcher("add_emp.jsp").forward(request, response);
    }

    private void saveEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idDept = request.getParameter("idDept");
        String idEmp = request.getParameter("code");
        String nom = request.getParameter("nom");
        Double salaire = Double.parseDouble(request.getParameter("salaire"));
        
        Departement dept = daoDept.FindByCode(idDept);
        Employe emp = new Employe(idEmp,nom,salaire);
        emp.setRefDept(dept);
        daoEmp.addEmploye(emp);
        this.Employes(request, response);
    }
    
    private void EmpsByDept(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String refDept = request.getParameter("idDept");
        List<Employe> list = daoEmp.EmpByDept(refDept);
        Departement dept = daoDept.FindByCode(refDept);
        request.setAttribute("list", list);
        request.setAttribute("NomDept", dept.getNom());
        request.getRequestDispatcher("empsByDept.jsp").forward(request, response);
    }

    private void AddDepartement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("add_dept.jsp").forward(request, response);
    }
    
    private void SaveDepartement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idDept = request.getParameter("idDept");
        String nom = request.getParameter("nom");
        
        Departement dept = new Departement();
        dept.setIdDept(idDept);
        dept.setNom(nom);
        daoDept.add(dept);
        this.Departements(request, response);
    }
    
    private void EditDepartement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idDept = request.getParameter("idDept");
        Departement dept = daoDept.FindByCode(idDept);
        request.setAttribute("dept", dept);
        request.getRequestDispatcher("edit_dept.jsp").forward(request, response);
    }
    
    private void SaveEditDepartement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idDept = request.getParameter("idDept");
        String nom = request.getParameter("nom");
        
        Departement dept = new Departement();
        dept.setIdDept(idDept);
        dept.setNom(nom);
        
        daoDept.update(dept);
        this.Departements(request, response);
    }
    
    private void DeleteDepartement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idDept = request.getParameter("idDept");
        daoEmp.DelByDept(idDept);
        daoDept.DeleteByCode(idDept);
        this.Departements(request, response);
    }
    
    private void EditEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idEmp = request.getParameter("idEmp");
        Employe emp = daoEmp.findByCode(idEmp);
        request.setAttribute("emp", emp);
        request.getRequestDispatcher("edit_emp.jsp").forward(request, response);
    }
    
    private void SaveEditEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idEmp = request.getParameter("idEmp");
        String nom = request.getParameter("nom");
        Double salaire = Double.parseDouble(request.getParameter("salaire"));
        
        Employe emp = new Employe();
        emp.setIdEmp(idEmp);
        emp.setNom(nom);
        emp.setSalaire(salaire);
        daoEmp.updateEmploye(emp);
        this.Employes(request, response);
    }
    
    private void DeleteEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idEmp = request.getParameter("idEmp");
        
        Employe emp = daoEmp.findByCode(idEmp);
        daoEmp.deleteEmploye(idEmp);
        this.Employes(request, response);
    }
}
