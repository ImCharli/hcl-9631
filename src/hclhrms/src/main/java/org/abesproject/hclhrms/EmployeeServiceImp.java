package org.abesproject.hclhrms;

import org.abesproject.hclhrms.entity.Employee;
import org.abesproject.hclhrms.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp {
    @Autowired
    private EmployeeRepo emprepo;
    Employee emp1;

    public String addEmp(){
         emp1 = new Employee(2001, "Abhay", "FSD", "Java","abhay123@gmail.com");
        return emprepo.addemp(emp1);
    }


    public List<Employee> getEmp(){
        return emprepo.getEmp();
    }
    public Employee getEmpId(){
        return emprepo.getEmpById(200);
    }
    public String addEmpBulk(){
        return emprepo.addEmpbulk();
    }
    public String deletebyId(){
        long emp_id=891; // ID AS PER YOUR LIST OF EMPLOYEES
             return emprepo.deleteById(emp_id);
    }
    public String updatebyId(){

           return emprepo.updateById(2001,emp1);
    }

}