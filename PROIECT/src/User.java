import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.exp;

// clasa user extinde Consumer si implementeaza interfata Observer
public class User extends Consumer implements Observer {
    User(ArrayList<Consumer> acquaintances, Resume resume, Application application) {
        super.acquaintances = acquaintances;
        super.resume = resume;
        super.application = application;
    }

    public Employee convert() {
        return new Employee(acquaintances, resume, application);
    }

    public Integer getExperienceYears() {
        int yearsOfExperience = 0;

        for (Experience experience : resume.experience) {
            int monthsWorked = 12 - experience.getStart().getMonthValue() + experience.getEnd().getMonthValue();
            int yearsWorked = experience.getEnd().getYear() - experience.getStart().getYear();
            if (monthsWorked >= 12) {
                yearsWorked++;
            }
            yearsOfExperience += yearsWorked;
        }

        return yearsOfExperience;
    }

    public Double getAverageGPA() {
        double averageGPA = 0;
        for (Education education : resume.education) {
            averageGPA += education.getGpa();
        }

        averageGPA /= resume.education.size();

        return averageGPA;
    }

    public Double getTotalScore() {
        return (double) getExperienceYears() * 1.5 + getAverageGPA();
    }

    // comanda pentru observer
    // primeste 0 daca este mesaj de respingere de la un job
    // primeste 1 daca s-a inchis un job
    // primeste 2 cand o companie adauga un job nou
    @Override
    public void update(int typeOfUpdate) {
        if (typeOfUpdate == 0) {
            System.out.println("A fost respins");
            return;
        }

        if (typeOfUpdate == 1) {
            System.out.println("S-a inchis un job");
            return;
        }

        if (typeOfUpdate == 2) {
            System.out.println("S-a deschis un job");
        }
    }
}
