package org.abes.hrms.model;
import org.abes.hrms.repository.EmpRepoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoEmployee {

     @Autowired
     private EmpRepoImp emprepo;
    public int add(){
        return emprepo.add(20,40);
    }
    public double squareRoot(){
        return emprepo.squareroot(2000);
    }
    public int greater(){
        return emprepo.gtn(34,67);
    }
}