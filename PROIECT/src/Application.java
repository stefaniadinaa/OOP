import java.util.ArrayList;
import java.util.List;

public class Application {
    private ArrayList<Company> companies;
    private ArrayList<User> users;

    public Application() {
    }

    public Application(ArrayList<Company> companies, ArrayList<User> users) {
        this.companies = companies;
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    /*
        Returneaza o companie dupa nume.
     */
    public Company getCompany(String name) {
        // parcurg lista de companii si returnez compania cu numele specificat
        for (Company company : companies) {
            if (company.getName() == name) {
                return company;
            }
        }

        // returnez compania fara niciun atribut cand nu gasesc o companie cu numele specificat
        return null;
    }

    /*
        Adaugarea unei companii in lista de companii inscrise.
     */
    public void add(Company company) {
        companies.add(company);
    }

    /*
        Adaugarea unui user in lista de utilizatori inscrisi
     */
    public void add(User user) {
        users.add(user);
    }

    /*
        Stergerea unei companii din lista de companii inscrise.
        Returneaza false daca utilizatorul nu exista.

     */
    public boolean remove(Company company) {
        return companies.remove(company);
    }

    /*
        Sterge user din lista de useri
     */
    public boolean remove(User user) {
        return users.remove(user);
    }

    /*
        Returneaza joburile disponibile de la companiile pe care le prefera utilizatorul
     */
    public ArrayList<Job> getJobs(List<String> companies) {
        // lista ce va contine joburile disponibile
        ArrayList<Job> jobs = new ArrayList<>();

        // parcurg lista de nume ale companiilor dorite de utilizator si extrag joburile pe care acestea le au
        // adaug in jobs pozitiile care mai sunt disponibile
        for (String companyName : companies) {
            ArrayList<Job> companyJobs = getCompany(companyName).getJobs();

            for (Job job : companyJobs) {
                if (job.isOpen()) {
                    jobs.add(job);
                }
            }
        }
        return jobs;
    }
}
