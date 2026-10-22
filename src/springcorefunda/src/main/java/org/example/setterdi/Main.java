package org.example.setterdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(Appconfig.class);
        Employee emp=ctx.getBean(Employee.class);
        emp.displayAddress();
        ctx.close();
    }
}
