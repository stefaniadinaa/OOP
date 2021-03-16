import java.util.ArrayList;
import java.util.Objects;

public class Recruiter extends Employee {
    private int rating;

    public Recruiter(ArrayList<Consumer> acquaintances, Resume resume, Application application,
            ArrayList<Request<Job, User>> requests) {
        super(acquaintances, resume, application);
        rating = 5;
    }

    public Recruiter(ArrayList<Consumer> acquaintances, Resume resume, Application application, String company,
                     String salary) {
        super(acquaintances, resume, application, company, salary);
        rating = 5;
    }

    public int evaluate(Job job, User user) {
        if (!job.meetsRequirements(user)) {
            // este respins
            user.update(0);
            return -1;
        }

        double score = rating * user.getTotalScore();

        // creaza request-ul si trimite manger-ului companiei la care este angajat recruiter-ul
        Request<Job, Consumer> request = new Request<>(job, user, this, score);
        application.getCompany(company).getManger().addRequests(request);

        // cand utilizatorul aplica pentru o companie, el este aduagat in lista de observeri a acesteia
        application.getCompany(job.getCompanyName()).addObserver(user);

        rating += 0.1;

        // este potrivit
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recruiter recruiter = (Recruiter) o;

        if (!super.equals(recruiter)) {
            return false;
        }

        return rating == recruiter.rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rating);
    }

}
