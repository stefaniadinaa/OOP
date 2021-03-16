import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class Marketing extends Department {
    public Marketing() {

    }

    public Marketing(ArrayList<Employee> employees, ArrayList<Job>jobs, String departmentName) {
        super.employees = employees;
        super.jobs = jobs;
        super.departmentName = departmentName;
    }

    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            double salary = parseDouble(employee.getSalary());

            if (salary < 3000) {
                totalSalary += salary;
            }

            if (salary > 5000) {
                totalSalary += (1 - 0.1) * salary;
            }

            totalSalary += (1 - 0.16) * salary;
        }
        budgetForSalaries += totalSalary;
        return totalSalary;
    }
}
