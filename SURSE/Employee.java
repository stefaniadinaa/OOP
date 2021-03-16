import java.util.ArrayList;

public class Employee extends Consumer {
    protected String company;
    protected String salary;
    protected int yearsWorking;

    public Employee() {

    }

    public Employee(ArrayList<Consumer> acquaintances, Resume resume, Application application) {
        super.acquaintances = acquaintances;
        super.resume = resume;
        super.application = application;
    }

    public Employee(ArrayList<Consumer> acquaintances, Resume resume, Application application,
                    String company, String salary) {
        super.acquaintances = acquaintances;
        super.resume = resume;
        super.application = application;
        this.company = company;
        this.salary = salary;
        yearsWorking = 0;
    }

    public String getCompanyName() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getYearsWorking() {
        return yearsWorking;
    }

    public void setYearsWorking(int yearsWorking) {
        this.yearsWorking = yearsWorking;
    }
}
