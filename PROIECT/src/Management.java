import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class Management extends Department {
    public Management() {

    }

    public Management(ArrayList<Employee> employees, ArrayList<Job>jobs, String departmentName) {
        super.employees = employees;
        super.jobs = jobs;
        super.departmentName = departmentName;
    }

    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += (1 - 0.16) * parseDouble(jobs.get(0).getSalary());
        }
        budgetForSalaries += totalSalary;
        return totalSalary;
    }
}
