import java.util.*;
import java.util.stream.*;

class Employee {
    private int id;
    private String name;
    private String department;
    private int age;
    private double salary;

    public Employee(int id, String name, String department, int age, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " - " + department + " - " + salary;
    }
}

public class Practise {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Siva", "IT", 22, 50000),
            new Employee(2, "Ravi", "HR", 25, 40000),
            new Employee(3, "Kiran", "IT", 28, 70000),
            new Employee(4, "Arjun", "Finance", 30, 60000),
            new Employee(5, "Priya", "HR", 24, 45000),
            new Employee(6, "Ram", "Finance", 32, 80000),
            new Employee(7, "Anil", "IT", 26, 65000)
        );

        System.out.println("=== 1. FILTERING (Salary > 50,000) ===");
        employees.stream()
                .filter(e -> e.getSalary() > 50000)
                .forEach(System.out::println);

        System.out.println("\n=== 2. SORTING (Ascending by Salary) ===");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .forEach(System.out::println);

        System.out.println("\n=== 3. GROUPING BY DEPARTMENT ===");
        Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        byDept.forEach((dept, empList) -> {
            System.out.println(dept + ": " + empList);
        });

        System.out.println("\n=== 4. TRANSFORM NAMES ===");
        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(names);

        System.out.println("\n=== 5. AGGREGATION (Average Salary) ===");
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println("Average Salary: " + avgSalary);

        // ==========================================
        // PRACTICE EXERCISES SOLUTIONS
        // ==========================================
        System.out.println("\n==========================================");
        System.out.println("PRACTICE EXERCISES SOLUTIONS");
        System.out.println("==========================================");

        // Exercise 1: Find employees whose salary is greater than 60,000
        System.out.println("\n1. Employees with salary > 60,000:");
        List<Employee> salaryAbove60k = employees.stream()
                .filter(e -> e.getSalary() > 60000)
                .collect(Collectors.toList());
        salaryAbove60k.forEach(System.out::println);

        // Exercise 2: Find the names of all employees in the HR department
        System.out.println("\n2. Names of all employees in HR department:");
        List<String> hrEmployeeNames = employees.stream()
                .filter(e -> "HR".equals(e.getDepartment()))
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(hrEmployeeNames);

        // Exercise 3: Find the employee with the lowest salary
        System.out.println("\n3. Employee with the lowest salary:");
        Optional<Employee> lowestSalaryEmp = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
        lowestSalaryEmp.ifPresent(System.out::println);

        // Exercise 4: Calculate the average salary of IT employees
        System.out.println("\n4. Average salary of IT employees:");
        double avgItSalary = employees.stream()
                .filter(e -> "IT".equals(e.getDepartment()))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.println("Average IT Salary: " + avgItSalary);

        // Exercise 5: Count employees in each department
        System.out.println("\n5. Count of employees in each department:");
        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
        countByDept.forEach((dept, count) -> System.out.println(dept + " = " + count));

        // Exercise 6: Find the top 3 highest-paid employees
        System.out.println("\n6. Top 3 highest-paid employees:");
        List<Employee> top3HighestPaid = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .collect(Collectors.toList());
        top3HighestPaid.forEach(System.out::println);
    }
}
