package org.example.constructordi;
import org.example.constructordi.Appconfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Appconfig.class);
        Employee emp = ctx.getBean(Employee.class);
        emp.getEmpAddress();
    }
}