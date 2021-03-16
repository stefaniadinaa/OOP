import java.util.*;

public class Company implements Subject {
    private String name;
    private Manager manger;
    private ArrayList<Department> departments;
    private ArrayList<Employee> recruiters;

    private ArrayList<Observer> observers;

    public Company(String name, Manager manger, ArrayList<Department> departments, ArrayList<Employee> recruiters) {
        this.name = name;
        this.manger = manger;
        this.departments = departments;
        this.recruiters = recruiters;

        observers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(manger, company.manger) &&
                Objects.equals(departments, company.departments) &&
                Objects.equals(recruiters, company.recruiters);
    }

    public Department getDepartment(String departmentName) {
        for (Department department : departments) {
            if (department.getDepartmentName() == departmentName) {
                return department;
            }
        }
        return null;
    }

    public void add(Department department) {
        departments.add(department);
    }

    public void add(Recruiter recruiter) {
        recruiters.add(recruiter);
    }

    public void add(Employee employee, Department department) {
        department.add(employee);
    }

    public void remove(Employee employee) {
        for (Department department : departments) {
            if (department.getEmployees().remove(employee)) {
                return;
            }
        }
        recruiters.remove(employee);
    }

    public void remove(Department department) {
        // elimin angajatii ce se afla in departament din lista de recruiteri
        for (Employee employee : department.getEmployees()) {
            recruiters.remove(employee);
        }

        // elimin angajatii din departament
        department.getEmployees().clear();

        // elimin departamentul
        departments.remove(department);
    }

    public void remove(Recruiter recruiter) {
        recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination) {
        destination.getEmployees().addAll(source.getEmployees());
    }

    public void move(Employee employee, Department newDepartment) {
        for (Department department : departments) {
            department.getEmployees().remove(employee);
        }

        newDepartment.getEmployees().add(employee);
    }

    public boolean contains(Department department) {
        return departments.contains(department);
    }

    public boolean contains(Employee employee) {
        for (Department department : departments) {
            if (department.getEmployees().contains(employee)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }

    public Recruiter getRecruiter(User user) {
        // bfs folosind HashMap pentru a tine cont de persoanele vizitate si distanta sociala
        // de la user la acestia
        Queue<Consumer> queue = new LinkedList<>();
        HashMap<Consumer, Integer> distance = new HashMap<>();

        queue.add(user);
        distance.put(user, 0);

        while (!queue.isEmpty()) {
            Consumer firstInQueue = queue.poll();

            for (Consumer _user : firstInQueue.getAcquaintances()) {
                // daca nu exista intrare in hashmap cu acest user,
                // il adaug in colectie cu distanta pana la parinte + 1
                if (distance.get(_user) == null) {
                    distance.put(_user, distance.get(firstInQueue) + 1);
                    queue.add(_user);
                }
                // daca exista intrare cu acest user, verific daca distanta prin
                // parinte este mai scurta decat cea calculata anterior,
                // caz in care sterg vechea inregistrare si o adaug pe
                // cea noua cu distanta updatata
                else if (distance.get(_user) > distance.get(firstInQueue) + 1 ){
                    distance.remove(_user);
                    distance.put(_user, distance.get(firstInQueue) + 1);
                    queue.add(_user);
                }
            }
        }

        // lista cu posibili recrutari
        ArrayList<Recruiter> possibleRecruiters = new ArrayList<>();

        int maxDepth = 0;

        //parcurg intregul hashmap
        for (Map.Entry<Consumer, Integer> entry : distance.entrySet()) {
            // daca prietenul sa afla in lista de recuiteri
            if (getRecruiters().contains(entry.getKey())) {
                // daca prietenul are grad mai indepartat
                if (entry.getValue() > maxDepth) {
                    // fac update la adancime
                    maxDepth = entry.getValue();
                    // sterg toti posibilii recuiteri de pana acum
                    possibleRecruiters.clear();
                }
                // adauga posibilul recruiter
                possibleRecruiters.add((Recruiter) entry.getKey());
            }
        }

        // caut recuiterul cu ratingul cel mai mare
        // in cazul in care exista mai multe optiuni
        // altfel aleg unicul recuiter din lista
        Recruiter recruiter = null;
        if (possibleRecruiters.size() != 1) {
            double maxRating = 0;
            for (Recruiter _recruiter : possibleRecruiters) {
                if (_recruiter.getRating() > maxRating) {
                    recruiter = _recruiter;
                    maxRating = _recruiter.getRating();
                }
            }
        } else {
            recruiter = possibleRecruiters.get(0);
        }

        return recruiter;
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> openedJobs = new ArrayList<>();
        // parcurg toate departamentele si adaug job-urile deschise din ele in lista pe care o returnez
        for (Department department : departments) {
            openedJobs.addAll(department.getJobs());
        }

        return openedJobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManger() {
        return manger;
    }

    public void setManger(Manager manger) {
        this.manger = manger;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Employee> getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(ArrayList<Employee> recruiters) {
        this.recruiters = recruiters;
    }


    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(User c) {
        observers.remove(c);
    }

    @Override
    public void notifyAllObservers(int typeOfUpdate) {
        for (Observer observer : observers) {
            observer.update(typeOfUpdate);
        }
    }
}
