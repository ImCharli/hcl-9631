package assignment;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

abstract class Employee {
    private int empId;
    private String name;
    private double basicSalary;
    private double hra;
    private double ta;
    private double specialAllowance;

    public Employee(int empId, String name, double basicSalary,
                    double hra, double ta, double specialAllowance) {
        this.empId = empId;
        this.name = name;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.ta = ta;
        this.specialAllowance = specialAllowance;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getHra() {
        return hra;
    }

    public double getTa() {
        return ta;
    }

    public double getSpecialAllowance() {
        return specialAllowance;
    }

    public abstract double getBonusPercentage();

    public double calculateBonus() {
        return (basicSalary + hra) * getBonusPercentage() / 100;
    }

    public void displayDetails() {
        System.out.println("---------------------------------------------");
        System.out.println("Emp_id: " + empId);
        System.out.println("Name: " + name);
        System.out.printf("Basic Salary: Rs. %.2f%n", basicSalary);
        System.out.printf("HRA: Rs. %.2f%n", hra);
        System.out.printf("TA: Rs. %.2f%n", ta);
        System.out.printf("Special Allowance: Rs. %.2f%n",
                specialAllowance);
        System.out.printf("Bonus: Rs. %.2f%n", calculateBonus());
    }
}

class Manager extends Employee {
    public Manager(int id, String name, double basic, double hra, double ta, double allowance) {
        super(id, name, basic, hra, ta, allowance);
    }

    @Override
    public double getBonusPercentage() {
        return 12;
    }
}

class Admin extends Employee {
    public Admin(int id, String name, double basic, double hra, double ta, double allowance) {
        super(id, name, basic, hra, ta, allowance);
    }

    @Override
    public double getBonusPercentage() {
        return 10;
    }
}

class Engineer extends Employee {
    public Engineer(int id, String name, double basic, double hra,double ta, double allowance) {
        super(id, name, basic, hra, ta, allowance);
    }

    @Override
    public double getBonusPercentage() {
        return 12;
    }
}

class Accountant extends Employee {
    public Accountant(int id, String name, double basic, double hra,double ta, double allowance) {
        super(id, name, basic, hra, ta, allowance);
    }

    @Override
    public double getBonusPercentage() {
        return 8;
    }
}

public class ASSIGNMENT1 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Manager(100, "Shubham", 40000, 15000,
                3000, 5000));
        employees.add(new Admin(101, "Sumit", 35000, 12000,
                2500, 4000));
        employees.add(new Engineer(102, "Sameer", 45000, 18000,
                3500, 6000));
        employees.add(new Accountant(103, "Rahul", 30000, 10000,
                2000, 3000));

        // Sort employees by bonus in descending order
        employees.sort(Comparator.comparingDouble(Employee::calculateBonus).reversed());

        for (Employee employee : employees) {
            employee.displayDetails();
        }

        System.out.println("---------------------------------------------");
    }
}