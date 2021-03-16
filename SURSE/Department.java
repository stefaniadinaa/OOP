import java.util.ArrayList;

public abstract class Department {
    protected ArrayList<Employee> employees;
    protected ArrayList<Job> jobs;
    protected String departmentName;
    protected Application application;
    // camp care resprezinta bugetul alocat pentru salariile din toata compania
    // pentru a determina acest camp trebuie apelat getTotalSalaryBudget din toate clasele
    // care mostenesc clasa abstracta Departament
    protected double budgetForSalaries = 0;

    public String getDepartmentName() {
        return departmentName;
    }

    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs() {
        ArrayList<Job> openJobs = new ArrayList<>();
        for (Job job : jobs) {
            if (job.isOpen()) {
                openJobs.add(job);
            }
        }
        return openJobs;
    }

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void remove(Employee employee) {
        employees.remove(employee);
    }

    public void add(Job job) {
        jobs.add(job);
        // trimite mesaj cu codul 2 (ca s-a postat un job nou) celor care au aplicat
        application.getCompany(job.getCompanyName()).notifyAllObservers(2);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
