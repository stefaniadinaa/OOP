import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Manager extends Employee {
    private ArrayList<Request<Job, User>> requests;

    public Manager(ArrayList<Consumer> acquaintances, Resume resume, Application application,
            ArrayList<Request<Job, User>> requests) {
        super(acquaintances, resume, application);
        this.requests = requests;
    }

    public Manager(ArrayList<Consumer> acquaintances, Resume resume, Application application, String company,
                     String salary, ArrayList<Request<Job, User>> requests) {
        super(acquaintances, resume, application, company, salary);
        this.requests = requests;
    }

    public void process(Job job) {
        // lista cu aplicanti pentru job
        ArrayList<Pair<User, Double>> applicants = new ArrayList<>();

        // adaug aplicantii care au depus dosar pentru job
        for (Request<Job, User> request : requests) {
            if (request.getKey().equals(job)) {
                applicants.add(new Pair<>(request.getValue1(), request.getScore()));
            }
        }

        // sortare descrescatoare a aplicantilor in functie de scor
        // folosesc o functie lambda ca si comparator
        applicants.sort((a, b) -> {
            if (a.second < b.second) {
                return 1;
            } else if (a.second > b.second) {
                return -1;
            }
            return 0;
        });

        int numEmployed = 0;
        for (Pair<User, Double> applicant : applicants) {
            if (job.getNoPositions() == numEmployed) {
                break;
            }
            if (application.getUsers().contains(applicant.first)) {
                // convertez userul la angajat
                Employee employee = applicant.first.convert();
                // adaug compania la care lucreaza
                employee.setCompany(job.getCompanyName());
                // adaug salariul
                employee.setSalary(job.getSalary());

                // sterg din lista de observeri a companiei utilizatorul nou angajat
                application.getCompany(job.getCompanyName()).removeObserver(applicant.first);

                // adaug noul angajat in lista de persoane angajate in firma
                application.getCompany(job.getCompanyName()).getDepartment(job.getDepartmentName()).add(employee);

                application.getUsers().remove(applicant.first);

                numEmployed++;
            }
        }

        // Sterg toate requesturile pentru acest job
        requests.removeIf(request -> request.getKey().equals(job));

        job.setOpen(false);

        application.getCompany(job.getCompanyName()).notifyAllObservers(1);
    }

    public ArrayList<Request<Job, User>> getRequests() {
        return requests;
    }

    public void addRequests(Request request) {
        requests.add(request);
    }
}
