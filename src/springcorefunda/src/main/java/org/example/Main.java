package org.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx= new AnnotationConfigApplicationContext(Appconfig.class);
        Employee emp1=ctx.getBean(Employee.class);
        Employee emp2=ctx.getBean(Employee.class);

        emp1.display();
        emp2.display();
        System.out.println(emp1.equals(emp2));
        System.out.println(emp1.hashCode());
        System.out.println(emp2.hashCode());

        }
}
