import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class Finance extends Department {
    public Finance() {

    }

    public Finance(ArrayList<Employee> employees, ArrayList<Job>jobs, String departmentName) {
        super.employees = employees;
        super.jobs = jobs;
        super.departmentName = departmentName;
    }

    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            if (employee.getYearsWorking() < 1) {
                totalSalary += (1 - 0.1) - parseDouble(employee.getSalary());
            } else {
                totalSalary += (1 - 0.16) - parseDouble(employee.getSalary());
            }
        }
        budgetForSalaries += totalSalary;
        return totalSalary;
    }
}
