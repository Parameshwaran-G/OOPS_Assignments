
import java.util.Scanner;

public class Payroll {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            int employeeType = sc.nextInt();

            if (employeeType > 2 || employeeType < 1) {
                System.out.println("Invalid Choice");
                return;
            }

            Employee employee;

            if (employeeType == 2) {
                int employeeId = sc.nextInt();
                String employeeName = sc.next();
                int workingDays = sc.nextInt();
                double rate = sc.nextDouble();
                employee = new ContractEmployee(employeeId, employeeName, workingDays, rate);

            } else {
                int employeeId = sc.nextInt();
                String employeeName = sc.next();
                double monthlySalary = sc.nextDouble();

                employee = new FullTimeEmployee(employeeId, employeeName, monthlySalary);
            }
            System.out.println("Employee ID:" + employee.getEmployeeId());
            System.out.println("Employee Name:" + employee.getEmployeeName());
            System.out.println("Salary:" + employee.calculateSalary());
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

}

abstract class Employee {

    private int employeeId;
    private String employeeName;

    Employee(int employeeId, String employeeName) {

        if (employeeId < 1 || employeeId > 10000) {
            throw new IllegalArgumentException("Invalid Employee ID");
        }
        if (!employeeName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid Employee Name");
        }

        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    abstract double calculateSalary();
}

class FullTimeEmployee extends Employee {

    private double monthlySalary;

    public FullTimeEmployee(int employeeId, String employeeName, double monthlySalary) {
        super(employeeId, employeeName);

        if (!Double.isFinite(monthlySalary)
                || monthlySalary < 0
                || monthlySalary > 1000000) {
            throw new IllegalArgumentException("Invalid Monthly Salary");
        }
        this.monthlySalary = monthlySalary;
    }

    @Override 
    public double calculateSalary() {
        return monthlySalary;
    }
}

class ContractEmployee extends Employee {

    private int workingDays;
    private double rate;

    public ContractEmployee(int employeeId, String employeeName, int workingDays, double rate) {
        super(employeeId, employeeName);

        if (workingDays > 31 || workingDays < 1) {
            throw new IllegalArgumentException("Invalid Working Days");
        }

        if (!Double.isFinite(rate) || rate < 0 || rate > 100000) {
            throw new IllegalArgumentException("Invalid Rate");
        }

        this.workingDays = workingDays;
        this.rate = rate;
    }

    @Override 
    public double calculateSalary() {
        return workingDays * rate;
    }

}
