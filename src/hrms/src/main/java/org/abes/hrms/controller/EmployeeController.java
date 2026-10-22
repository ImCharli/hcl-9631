package org.abes.hrms.controller;

import org.abes.hrms.model.DemoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private DemoEmployee demoEmployee;
    @GetMapping("/")
    public String welcome(){
        return "<h2 style='color:blue'>Welcome to HRMS App</h2>";
    }
    @GetMapping("/add")
    public int sum(){
        System.out.println("Inside /add controller point");
        int result = demoEmployee.add();
        System.out.println("result from model ="+result);
        return result;
    }
    @GetMapping("/sqrt")
    public double getSquareRoot(){
        double result=demoEmployee.squareRoot();
        return result;
    }
    @GetMapping("/gtn")
    public int getGtnNunber(){
        int result=demoEmployee.greater();
        return result;
    }

}