import java.util.*;

public class Job {
    private String jobName;
    private String companyName;
    private boolean open;
    public Constraint graduationLimit;
    public Constraint experienceLimit;
    public Constraint gpaLimit;
    private int noPositions;
    private String salary;
    private String departmentName;
    private ArrayList<User> participants;
    private Application application;

    public Job(String jobName, String companyName, boolean open, Constraint graduationLimit, Constraint experienceLimit,
               Constraint gpaLimit, int noPositions, String department, ArrayList<User> participants,
               Application application) {
        this.jobName = jobName;
        this.companyName = companyName;
        this.open = open;
        this.graduationLimit = graduationLimit;
        this.experienceLimit = experienceLimit;
        this.gpaLimit = gpaLimit;
        this.noPositions = noPositions;
        this.departmentName = department;
        this.participants = participants;
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return open == job.open &&
                noPositions == job.noPositions &&
                Objects.equals(jobName, job.jobName) &&
                Objects.equals(companyName, job.companyName) &&
                Objects.equals(graduationLimit, job.graduationLimit) &&
                Objects.equals(experienceLimit, job.experienceLimit) &&
                Objects.equals(gpaLimit, job.gpaLimit) &&
                Objects.equals(salary, job.salary) &&
                Objects.equals(departmentName, job.departmentName) &&
                Objects.equals(participants, job.participants) &&
                Objects.equals(application, job.application);
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String department) {
        this.departmentName = department;
    }

    public Constraint getGraduationLimit() {
        return graduationLimit;
    }

    public void setGraduationLimit(Constraint graduationLimit) {
        this.graduationLimit = graduationLimit;
    }

    // din obiectul aplication extrag compania careia apartine jobul, apoi extrag recruiterul potrivit pentru
    // userul care aplica pentru job, iar in final apelez metoda evaluate
    public void apply(User user) {
        application.getCompany(companyName).getRecruiter(user).evaluate(this, user);
    }

    public boolean meetsRequirements(User user) {
        // daca anul de absolvire nu se incadreaza in limitele impuse
        if (user.getGraduationYear() < graduationLimit.lowerLimit ||
                user.getGraduationYear() > graduationLimit.upperLimit) {
            return false;
        }

        // daca anii de experienta nu corespund limitei impuse
        if (user.getExperienceYears() < experienceLimit.lowerLimit ||
                user.getExperienceYears() > experienceLimit.upperLimit) {
            return false;
        }

        // daca media pe invatamant nu corespunde limitei impuse
        if (user.getAverageGPA() < gpaLimit.lowerLimit ||
                user.getAverageGPA() > gpaLimit.upperLimit) {
            return false;
        }

        // se indeplinesc toate conditiile
        return  true;
    }

    public int getNoPositions() {
        return noPositions;
    }

    public void setNoPositions(int noPositions) {
        this.noPositions = noPositions;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
