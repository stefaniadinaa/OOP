import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class IT extends Department {
    public IT() {

    }

    public IT(ArrayList<Employee> employees, ArrayList<Job>jobs, String departmentName) {
        super.employees = employees;
        super.jobs = jobs;
        super.departmentName = departmentName;
    }

    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += parseDouble(employee.getSalary());
        }
        budgetForSalaries += totalSalary;
        return totalSalary;
    }
}
