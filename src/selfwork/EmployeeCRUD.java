package selfwork;

import java.util.*;

public class EmployeeCRUD {

    public static void main(String[] args) {

        ArrayList<employee> employees = new ArrayList<>();

        // CREATE
        employees.add(new employee(103, "Rahul", 45000));
        employees.add(new employee(101, "Amit", 35000));
        employees.add(new employee(105, "Priya", 55000));
        employees.add(new employee(102, "Neha", 40000));
        employees.add(new employee(104, "Vikas", 50000));

        // READ ALL
        System.out.println("===== ALL EMPLOYEES =====");
        displayEmployees(employees);

        // SEARCH
        System.out.println("\n===== SEARCH EMPLOYEE =====");

        employee found = findEmployee(employees, 102);

        if (found != null) {
            System.out.println("Employee Found: " + found);
        } else {
            System.out.println("Employee not found.");
        }

        // UPDATE
        System.out.println("\n===== UPDATE EMPLOYEE =====");

        employee empToUpdate = findEmployee(employees, 103);

        if (empToUpdate != null) {
            empToUpdate.setName("Rahul Sharma");
            empToUpdate.setSalary(60000);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }

        displayEmployees(employees);

        // DELETE
        System.out.println("\n===== DELETE EMPLOYEE =====");

        boolean deleted = deleteEmployee(employees, 105);

        if (deleted) {
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found.");
        }

        displayEmployees(employees);

        // ITERATOR: FORWARD
        System.out.println("\n===== ITERATOR FORWARD =====");

        Iterator<employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // LISTITERATOR: FORWARD
        System.out.println("\n===== LISTITERATOR FORWARD =====");

        ListIterator<employee> listIterator =
                employees.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        // LISTITERATOR: BACKWARD
        System.out.println("\n===== LISTITERATOR BACKWARD =====");

        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        // SORT: ASCENDING
        System.out.println("\n===== ID ASCENDING =====");

        employees.sort(Comparator.comparingInt(employee::getId));
        displayEmployees(employees);

        // SORT: DESCENDING
        System.out.println("\n===== ID DESCENDING =====");

        employees.sort(
                Comparator.comparingInt(employee::getId).reversed()
        );

        displayEmployees(employees);
    }

    public static void displayEmployees(
            ArrayList<employee> employees) {

        for (employee emp : employees) {
            System.out.println(emp);
        }
    }

    public static employee findEmployee(
            ArrayList<employee> employees, int id) {

        for (employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }

        return null;
    }

    public static boolean deleteEmployee(
            ArrayList<employee> employees, int id) {

        Iterator<employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            employee emp = iterator.next();

            if (emp.getId() == id) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }
}