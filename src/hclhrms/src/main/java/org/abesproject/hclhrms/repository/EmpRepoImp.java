package org.abesproject.hclhrms.repository;

import org.abesproject.hclhrms.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmpRepoImp implements EmployeeRepo {
    List<Employee> emplist=new ArrayList<>();

    @Override
    public String addemp(Employee emp) {
        emplist.add(emp);
        return "Employoee Addedd successfully";
    }

    @Override
    public String addEmpbulk() {
        List <Employee> employeesList=List.of(
                new Employee(890,"Ram","Test Engineer","java","abc@gmail.com"),
                new Employee(891,"Amit","Test Engineer","java","amit@gmail.com"),
                new Employee(101, "Ashish", "Developer", "Java", "ashish@gmail.com"),
                new Employee(102, "Amitabh", "Tester", "java", "abhay@gmail.com")
        );
        emplist.addAll(employeesList);
        return "All employees added";
    }

    @Override
    public List<Employee> getEmp() {
        return emplist;
    }

    @Override
    public Employee getEmpById(long empid) {
        return  emplist.stream().filter(e->e.getEmp_id()==empid).findFirst().orElse(null);

    }

    @Override
    public String deleteById(long empid) {
        boolean status=false;
        if (getEmpById(empid)!=null){

            status=emplist.removeIf(e->e.getEmp_id()==empid);
        }
        if(status){
            return "Employee deleted successfully";
        }


        return "Employee is not registered with id:"+empid;
    }



    @Override
    public String updateById(long empid, Employee updatedEmp) {
        Employee existingEmp=getEmpById(empid);
        if(existingEmp!=null){
            existingEmp.setEmail("abc@gmail.com");
        }
        else{
            return "Employee does not exist";
        }


        return "Employee updated successfully";
    }
}
