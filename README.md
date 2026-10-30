# Java 8 Streams API – Employee Data Operations

A practical guide and reference implementation for mastering Java 8 Streams using an Employee dataset.

## Table of Contents
- [Overview](#overview)
- [Employee Model & Dataset](#employee-model--dataset)
- [Core Stream Operations](#core-stream-operations)
  - [1. Filtering](#1-filtering--filter)
  - [2. Sorting](#2-sorting--sorted)
  - [3. Grouping](#3-grouping--groupingby)
  - [4. Transforming](#4-transforming--map)
  - [5. Aggregating](#5-aggregating-data)
  - [6. Chaining Multiple Operations](#6-chaining-multiple-operations)
- [Practice Exercises & Solutions](#practice-exercises--solutions)
- [Streams API Quick Reference](#streams-api-quick-reference)
- [How to Run](#how-to-run)

---

## Overview

The Java 8 Streams API enables functional-style operations on collections of elements, allowing clean, declarative, and expressive data processing pipelines. This project covers five fundamental operations:
1. **Filter**: Select elements matching criteria.
2. **Sort**: Order elements by single or composite fields.
3. **Group**: Categorize elements using `Collectors.groupingBy`.
4. **Transform**: Map elements from one type to another.
5. **Aggregate**: Compute summary statistics such as sum, average, min, and max.

---

## Employee Model & Dataset

### Employee Class
```java
class Employee {
    private int id;
    private String name;
    private String department;
    private int age;
    private double salary;

    // Constructor, Getters & toString()
}
```

### Sample Data
```java
List<Employee> employees = Arrays.asList(
    new Employee(1, "Siva", "IT", 22, 50000),
    new Employee(2, "Ravi", "HR", 25, 40000),
    new Employee(3, "Kiran", "IT", 28, 70000),
    new Employee(4, "Arjun", "Finance", 30, 60000),
    new Employee(5, "Priya", "HR", 24, 45000),
    new Employee(6, "Ram", "Finance", 32, 80000),
    new Employee(7, "Anil", "IT", 26, 65000)
);
```

---

## Core Stream Operations

### 1. Filtering – `filter()`
Filters employees with salary > ₹50,000:
```java
List<Employee> result = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .collect(Collectors.toList());
```

### 2. Sorting – `sorted()`
- **Ascending by salary:**
  ```java
  employees.stream()
      .sorted(Comparator.comparingDouble(Employee::getSalary))
      .forEach(System.out::println);
  ```
- **Descending by salary:**
  ```java
  employees.stream()
      .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
      .forEach(System.out::println);
  ```

### 3. Grouping – `groupingBy()`
Group employees by department:
```java
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

### 4. Transforming – `map()`
Extract names of all employees:
```java
List<String> names = employees.stream()
    .map(Employee::getName)
    .collect(Collectors.toList());
```

### 5. Aggregating Data
- **Total Salary:**
  ```java
  double totalSalary = employees.stream().mapToDouble(Employee::getSalary).sum();
  ```
- **Average Salary:**
  ```java
  double avgSalary = employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);
  ```
- **Highest Paid Employee:**
  ```java
  Optional<Employee> highest = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
  ```

### 6. Chaining Multiple Operations
Find IT employees with salary > ₹50,000, sort by salary descending, and extract names:
```java
List<String> result = employees.stream()
    .filter(e -> "IT".equals(e.getDepartment()))
    .filter(e -> e.getSalary() > 50000)
    .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
    .map(Employee::getName)
    .collect(Collectors.toList());
```

---

## Practice Exercises & Solutions

| # | Exercise | Solution |
|---|---|---|
| **1** | Employees with salary > 60,000 | `employees.stream().filter(e -> e.getSalary() > 60000).collect(Collectors.toList())` |
| **2** | Names of employees in HR | `employees.stream().filter(e -> "HR".equals(e.getDepartment())).map(Employee::getName).collect(Collectors.toList())` |
| **3** | Employee with lowest salary | `employees.stream().min(Comparator.comparingDouble(Employee::getSalary))` |
| **4** | Average salary of IT employees | `employees.stream().filter(e -> "IT".equals(e.getDepartment())).mapToDouble(Employee::getSalary).average().orElse(0.0)` |
| **5** | Employee count per department | `employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))` |
| **6** | Top 3 highest-paid employees | `employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).collect(Collectors.toList())` |

---

## Streams API Quick Reference

| Method | Type | Description |
|---|---|---|
| `filter(Predicate)` | Intermediate | Filters elements satisfying condition |
| `map(Function)` | Intermediate | Transforms elements into another type |
| `sorted(Comparator)` | Intermediate | Sorts elements according to comparator |
| `distinct()` | Intermediate | Removes duplicate elements |
| `limit(long n)` | Intermediate | Truncates stream to first *n* elements |
| `skip(long n)` | Intermediate | Skips first *n* elements |
| `collect(Collector)` | Terminal | Accumulates stream into collections (List, Set, Map) |
| `forEach(Consumer)` | Terminal | Iterates over each element |
| `count()` | Terminal | Returns element count |
| `min(Comparator)` / `max(Comparator)` | Terminal | Finds minimum / maximum element |
| `reduce(BinaryOperator)` | Terminal | Aggregates elements into single value |

---

## How to Run

1. Ensure **Java 8+** is installed on your machine (`java -version`).
2. Run the program directly from the project root:
   ```bash
   java Practise.java
   ```
